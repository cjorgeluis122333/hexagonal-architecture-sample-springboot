package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.infrastructure.output.persisten.adapter;


import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.exception.ResourceNotFoundException;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.model.loan.Loan;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.out.LoanRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor  //Solo los campos con final
public class RedisLoanAdapter implements LoanRepositoryPort {

    private final JpaLoanAdapter jpaLoanAdapter; // El adaptador real de DB
    private final RedisTemplate<String, Object> redisTemplate;
    private static final String CACHE_PREFIX = "LOAN:";

    @Override
    public Optional<Loan> findById(Long id) {
        String key = CACHE_PREFIX + id;

        // 1. Intentar buscar en Redis
        try {
            Loan cached = (Loan) redisTemplate.opsForValue().get(key);
            if (cached != null) {
                log.info("CACHE HIT: Préstamo {} recuperado de Redis", id);
                return Optional.of(cached);
            }
        } catch (Exception e) {
            log.error("Error leyendo de Redis", e);
        }

        // 2. Si no está, ir a la DB (vía JpaLoanAdapter)
        Optional<Loan> loan = jpaLoanAdapter.findById(id);

        // 3. Guardar en Redis para futuras consultas (TTL 10 min)
        loan.ifPresent(l -> redisTemplate.opsForValue().set(key, l, Duration.ofMinutes(10)));

        return loan;
    }

    @Override
    public Loan save(Loan loan) {
        // Primero persistimos en DB para obtener el ID real
        Loan savedLoan = jpaLoanAdapter.save(loan);
        // Actualizamos caché
        redisTemplate.opsForValue().set(CACHE_PREFIX + savedLoan.getId(), savedLoan, Duration.ofMinutes(10));
        return savedLoan;
    }

    @Override
    public Loan update(Loan loan) throws ResourceNotFoundException {
        // Primero persistimos en DB para obtener el ID real
        Loan updated = jpaLoanAdapter.update(loan);
        // Al actualizar, refrescamos la entrada en Redis
        redisTemplate.opsForValue().set(CACHE_PREFIX + updated.getId(), updated, Duration.ofMinutes(10));
        return updated;
    }

    @Override
    public boolean deleteById(Long id) {
        // Primero persistimos en DB para obtener el ID real eliminarlo
        boolean deleted = jpaLoanAdapter.deleteById(id);
        if (deleted) {
            // Si se borra de la DB, hay que sacarlo de la caché inmediatamente
            redisTemplate.delete(CACHE_PREFIX + id);
        }
        return deleted;
    }

    @Override
    public List<Loan> findAll() {
        // No solemos cachear listas dinámicas grandes en Redis de esta forma, delegamos a JPA
        return jpaLoanAdapter.findAll();
    }
}
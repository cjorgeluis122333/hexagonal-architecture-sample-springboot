package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * Infrastructure configuration for Redis.
 * This class acts as a configuration adapter within the Hexagonal Architecture,
 * providing the necessary beans to satisfy the persistence requirements of the application.
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        // Keys are consistently serialized as Strings for readability and compatibility
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());

        // Constructing the modern Generic serializer using the 4.0 Builder pattern.
        // This solves the "missing type id" issue by allowing precise control over
        // polymorphic typing, especially for Java Records and final domain classes.
        RedisSerializer<Object> serializer = GenericJacksonJsonRedisSerializer.builder()
                .typePropertyName("_class") // Customizing the type hint property name
                .enableUnsafeDefaultTyping() // Enables typing for Records and final classes
                .build();

        template.setValueSerializer(serializer);
        template.setHashValueSerializer(serializer);

        // Required initialization to finalize properties
        template.afterPropertiesSet();
        return template;
    }
}
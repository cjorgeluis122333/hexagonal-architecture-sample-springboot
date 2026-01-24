# How use REDIS
## Step 1: Observe
Para observar todos los datos guardados de redis se utiliza el comando
```shell
docker exec -it redis_container redis-cli
```

## Step 2: Query
Para interactuar con el contenido de la base de datos en ***redis*** debes utilizar una serie de comandos

### Command 1: Show all keys
```shell
KEYS *
```
This command return something like:
1) "LOAN:3"
2) "LOAN:2"
3) "LOAN:null"

### Command 2: Select specific key
```shell
GET LOAN:2
```
This command return something like:
```json

"{\"_class\":\"com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.model.loan.Loan\",\"id\":2,\"amount\":{\"_class\":\"com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.model.loan.Money\",\"amount\":[\"java.math.BigDecimal\",10088881.00],\"currency\":\"Usd\"},\"borrower\":\"Jorge Luis\",\"approved\":false}"
```



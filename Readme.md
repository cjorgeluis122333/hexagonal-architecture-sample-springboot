# Hexagonal Architecture
 This project has the basic concept for apply hexagonal architecture
## Folder Structure
 The basic structure of that kind of project is: 
```
com.tuempresa.loan
│
├── domain/                <-- El Corazón (Lógica Pura)
│   ├── model/             <-- Objetos de negocio (POJOs)
│   ├── service/           <-- Servicios de dominio (Reglas de negocio)
│   └── ports/             <-- Las interfaces (Enchufes)
│       ├── in/            <-- Casos de Uso (Lo que el mundo pide al dominio)
│       └── out/           <-- Requerimientos (Lo que el dominio pide al mundo)
│
├── application/           <-- Orquestación (Opcional, pero recomendado)
│
└── infrastructure/        <-- Los Adaptadores (Tecnología)
├── input/             <-- Controladores REST, Consumidores de Kafka
│   └── rest/
└── output/            <-- Base de datos, Clientes de API externa
├── persistence/
└── external_api/
```

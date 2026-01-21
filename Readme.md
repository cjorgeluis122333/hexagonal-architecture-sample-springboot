# Hexagonal Architecture

Este proyecto implementa un sistema de gestión de tareas utilizando Arquitectura Hexagonal (Puertos y Adaptadores). El
objetivo principal es mantener el núcleo del negocio (dominio) aislado de dependencias externas como frameworks o bases
de datos.

## 🏗️ Estructura del Proyecto

El código está organizado en las siguientes capas:

1. Domain (Núcleo)
   Contiene las entidades de negocio y los Puertos (interfaces). Es totalmente independiente de Spring y librerías
   externas.

Models: Task, AdditionalTaskInfo.

Ports In (Input): Interfaces que definen los casos de uso (ej. CreateTaskUseCase).

Ports Out (Output): Interfaces para la comunicación con el exterior (ej. TaskRepositoryPort).

### Folder Structure

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

## Application (Servicios)

Implementa la lógica de los casos de uso.

Use Cases: Implementaciones de las interfaces de entrada (ej. GetAdditionalTaskInfoUseCaseImpl).

Services: TaskService actúa como una fachada para agrupar las funcionalidades relacionadas con las tareas.

## Infrastructure (Adaptadores y Configuración)

Contiene los detalles técnicos y la implementación de los puertos de salida.

Adapters: Implementaciones de persistencia (JpaTaskRepositoryAdapter) y servicios externos (ExternalServiceAdapter).

Config: Clase ApplicationConfig donde se realiza la inyección de dependencias manual mediante @Bean para mantener el
dominio limpio de anotaciones de Spring.

Rest Controllers: Adaptadores de entrada para la API REST.

## 🛠️ Tecnologías Utilizadas

1. > Java 17+
2. > Spring Boot 4.0.0
3. > Spring Data JPA
4. > Postgres Sql

## 🚀 Configuración y Ejecución

### Clonar el repositorio:

```Bash
git clone https://github.com/cjorgeluis122333/hexagonal-architecture-sample-springboot.git
```
### Construir el proyecto:

```Bash
./mvnw clean install
``` 

### Ejecutar la aplicación:

```Bash
./mvnw spring-boot:run
```

## 📝 Ejemplo de Uso de la API

### Crear una Tarea

> POST /api/tasks
```JSON
{
  "title": "Aprender Arquitectura Hexagonal",
  "description": "Estudiar la separación de capas y puertos"
}
```

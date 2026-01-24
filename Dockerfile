# ETAPA 1: Build (Compilación)
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copiamos solo el pom primero para cachear las dependencias del m2
COPY pom.xml .
# Descargamos dependencias (si no cambias el pom, este paso se salta en futuros builds)
RUN mvn dependency:go-offline -B

# Copiamos el código y empaquetamos
COPY src ./src
RUN mvn clean package -DskipTests

# ETAPA 2: Runtime (Ejecución ligera)
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copiamos solo el JAR compilado
COPY --from=build /app/target/*.jar app.jar

# Variables de entorno por defecto (se sobreescriben en el compose)
ENV SERVER_PORT=8037
ENV SPRING_PROFILES_ACTIVE=prod

EXPOSE 8037
ENTRYPOINT ["java", "-jar", "app.jar"]
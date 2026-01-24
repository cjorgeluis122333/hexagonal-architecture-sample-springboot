# ==========================================
# Etapa 1: Construcción (Build)
# ==========================================
FROM maven:3.9.5-eclipse-temurin-17 AS build
WORKDIR /app

# 1. Copiamos solo el pom.xml primero para cachear las dependencias
COPY pom.xml .
# 2. Descargamos dependencias (esto no se volverá a ejecutar si no cambias el pom)
RUN mvn dependency:go-offline -B

# 3. Copiamos el código fuente y compilamos
COPY src ./src
RUN mvn clean package -DskipTests

# ==========================================
# Etapa 2: Ejecución (Runtime)
# ==========================================
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copiamos el JAR generado en la etapa anterior
# El wildcard *.jar toma el único jar generado.
COPY --from=build /app/target/*.jar app.jar

# Variables de entorno por defecto (pueden sobreescribirse desde docker-compose)
ENV SERVER_PORT=8037

# Exponer el puerto
EXPOSE 8037

# Comando de inicio
ENTRYPOINT ["java", "-jar", "app.jar"]
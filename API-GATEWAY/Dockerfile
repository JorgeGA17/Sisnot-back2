# Etapa 1: Builder (Constructor)
FROM eclipse-temurin:21-jdk AS builder
LABEL authors="jgarciaale"

ENV LANG=C.UTF-8
ENV LC_ALL=C.UTF-8cls

# Instala Maven
RUN apt-get update && apt-get install -y maven && rm -rf /var/lib/apt/lists/*

# Crea un directorio para la app
WORKDIR /app

# Copiar el archivo pom.xml primero
COPY pom.xml .

# Descargar las dependencias de Maven
RUN mvn dependency:resolve # 'mvn dependency:go-offline' también funcionaría, pero 'resolve' es más explícito en un build inicial

# Copiar el resto de la aplicación
COPY src ./src

# Construir la aplicación
RUN mvn clean package -DskipTests -X # <-- Aquí es donde falla

# Etapa 2: Runner (Ejecutor)
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copiar el archivo JAR generado
COPY --from=builder /app/target/*.jar app.jar

# Exponer el puerto de la aplicación
EXPOSE 8080

# Comando para ejecutar la app
ENTRYPOINT ["java", "-jar", "app.jar"]
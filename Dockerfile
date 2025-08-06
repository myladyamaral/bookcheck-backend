FROM maven:3.9.6-eclipse-temurin-17-alpine AS build

WORKDIR /app

# Copia o projeto e compila
COPY . .
RUN mvn clean package -DskipTests

# Imagem final somente com JRE
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copia o JAR da imagem anterior
COPY --from=build /app/target/bookcheck-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta do Spring Boot
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

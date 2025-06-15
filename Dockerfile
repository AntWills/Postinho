# Estágio 1: Compilar e gerar o JAR com dependências
FROM maven:3.8.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copia o pom.xml e as dependências primeiro (cache de build)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o restante do código e compila
COPY ./src ./src
RUN mvn clean package

# Estágio 2: Imagem leve só com o JAR final e JRE
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho
WORKDIR /app

# Copia apenas o fat JAR
COPY --from=build /app/target/Postinho-jar-with-dependencies.jar /app/Postinho.jar

# Copia o banco SQLite, se ele existir no projeto
COPY ./data ./data

# Executa a aplicação
CMD ["java", "-jar", "/app/Postinho.jar"]

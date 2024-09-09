# Usar a imagem base
FROM openjdk:17-jdk-alpine

# Definindo o diretório de trabalh
WORKDIR /app

# Copiando o JAR da aplicação para o container
COPY target/api-transferencia.jar /app/api-transferencia.jar

# Expor a porta 8080
EXPOSE 8080

# Definindo o comando de inicialização
CMD ["java", "-jar", "api-transferencia.jar"]
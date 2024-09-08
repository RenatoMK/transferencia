# Usar a imagem base do WireMock
FROM rodolpheche/wiremock:2.30.1

# Criar o diretório de mapeamentos (caso não exista)
RUN mkdir -p /home/wiremock/mappings

# Copiar os mapeamentos locais para o container
COPY ./wiremock/mappings /home/wiremock/mappings

# Expor a porta 8080 (padrão do WireMock)
EXPOSE 8080

# Comando de inicialização
CMD ["--verbose"]
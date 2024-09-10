# Execução do App Transferência  
  
Executar através de uma IDE.  
Colocar as variáveis de ambiente no projeto, pois elas são necessárias para a execução do app.

DATABASE_URL=jdbc:h2:mem:testdb  
DATABASE_DRIVER=org.h2.Driver  
DATABASE_USERNAME=sa  
DATABASE_PASSWORD=123  
DATABASE_CONSOLE_ENABLED=true  
DATABASE_PATH=/h2-console  
SWAGGER_ATIVAR=true  
CLIENTE_API=http://localhost:9090/clientes/  
CONTA_API=http://localhost:9090/contas/  
NOTIFICACAO_API=http://localhost:9090/notificacoes  
  

# Execução do App Transferência via Docker  

Na raiz do projeto executar o comando abaixo para iniciar o container do app Transferência e do Wiremock. 

`docker-compose up --build -d`

Importante: Caso necessário alterar o valor das variáveis CLIENTE_API, CONTA_API, NOTIFICACAO_API conforme a sua configuração do DockerTools.

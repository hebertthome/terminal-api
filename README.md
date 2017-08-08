# # terminal-api

----------

## O que seria?

Trata-se de uma API Rest para escrita e leitura de um Terminal em um banco de memória(H2).
Escrita em Java8 e utilizando-se da praticidade do SpringBoot e seu alto nível de customização, tem como propósito a avaliação do conhecimento e capacidade técnica do participante.

### Suas dependências...

Antes de executar a aplicação certifique-se de que tenha instalado em sua máquina:
		 
- Java 1.8
- Maven 2.0+

Com o ambiente uma vez configurado vamos ao projeto ...

###  ... "Quick Start"
... clone o projeto

    git clone git@github.com:hebertthome/terminal-api.git
... entre na pasta do projeto

    cd terminal-api
... agora execute a aplicação através do plugin maven do Spring Boot

    mvn spring-boot:run
Para testar o funcionamento da aplicação, cheque o healthcheck da mesma (http://localhost:8080/healthcheck)

### Documentação
A documentação completa da API pode ser encontrada em http://hebert-thome.herokuapp.com/swagger-ui.html, bem como as versões disponíveis pra consumo.
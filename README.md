# Desafio Hotmart - Web Chat
Desafio Hotmart para avaliação de conhecimentos. Essa aplicação deverá possibilitar a usuários distintos se comunicarem através de um chat.

## Configuração inicial do projeto

### Banco de dados - Mysql
Para atender esse desafio foi utilizado o banco de dados Mysql. Na pasta bd existe um script nomeado scripts-estrutura-base-dados.sql que é responsável por criar toda a estrutura necessária para execução desse aplicativo.

```
desafio-hotmart-back-end/db/scripts-estrutura-base-dados.sql
```

### Detalhes da aplicação - Back End
* Java 8 
* Spring Boot
* WebSocket (STOMP)
* Junit
* Swagger
* Hibernate (JPA)
* SpringData
* Spring Security
* Spring Security Messaging
* DevTools (Para auxiliar o desenvolvimento)
* Hikari para melhor perfomance do pool de conexões
* Utilitários da Apache (Commons Lang, Commons Code, etc)
* Maven

### Detalhes da aplicação - Front End
* Angular 5 
* Typescript
* HTML 5
* CSS 3
* SASS

#### Arquivos de configuração
* As configurações gerais da aplicação (JPA, LOG, ETC) devem ser encontradas no arquivo desafio-hotmart-back-end/scr/main/resources/application.properties

Pequeno trecho

```
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.format_sql=false
spring.jpa.properties.hibernate.hbm2ddl.auto=validate
spring.jpa.properties.hibernate.use-new-id-generator-mappings=auto
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
server.contextPath=/
```

* As configurações da base de dados devem ser encontradas no arquivo desafio-hotmart-back-end/src/main/java/br/com/hotmart/desafiohotmart/config/DataSourceConfiguration.java

Pequeno trecho

```
HikariDataSource hikariDataSource = new HikariDataSource();

hikariDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/desafio-hotmart?autoReconnect=true&useSSL=false");
hikariDataSource.setUsername("desafio-hotmart");
hikariDataSource.setPassword("desafio-hotmart");
hikariDataSource.setPoolName("DesafioHotmartPoolConnection");
hikariDataSource.setMaximumPoolSize(MAX_POOL_SIZE);
hikariDataSource.setMaxLifetime(MAX_LIFE_TIME);
hikariDataSource.setConnectionTimeout(CONNETION_TIME_OUT);
hikariDataSource.setIdleTimeout(IDLE_TIMEOUT);

return hikariDataSource;
```

* As configurações do Spring Security devem ser encontradas no arquivo desafio-hotmart-back-end/src/main/java/br/com/hotmart/desafiohotmart/config/SpringSecurityConfiguration.java

Pequeno trecho

```
@Bean
public DaoAuthenticationProvider authenticationProvider() {
	
	DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
	
	authenticationProvider.setUserDetailsService(usuarioService);
	authenticationProvider.setPasswordEncoder(passwordEncoder());
	authenticationProvider.setHideUserNotFoundExceptions(false);
	
	return authenticationProvider;
}
```

* As configurações do WebSocket devem ser encontradas no arquivo desafio-hotmart-back-end/src/main/java/br/com/hotmart/desafiohotmart/config/WebSocketConfig.java

Pequeno trecho

```
@Override
public void configureMessageBroker(MessageBrokerRegistry config) {

	config.enableSimpleBroker("/chatHotmart");
	config.setApplicationDestinationPrefixes("/app");
	
}
```

* As configurações do Angular (Front-end) devem ser encontradas no arquivo desafio-hotmart-front-end/package.json

Pequeno trecho

```
{
  "name": "desafio-hotmart",
  "version": "2.0.0",
  "license": "MIT",
  "scripts": {
    "ng": "ng",
    "start": "ng serve",
    "build": "ng build",
    "test": "ng test",
    "lint": "ng lint",
    "e2e": "ng e2e"
},
```

## Como realizar a execução da aplicação

### Excecução aplicação Front-End

* Instalicação do node
```
https://nodejs.org/en/download/
```

* Instalicação do Angular 5
```
npm install @angular/cli -g
```

* Instalicação das dependências do projeto
```
npm install
```
Esse comando deve ser executado no local do projeto. E isso pode demorar um pouco :-(. Melhor pegar um café :-)!!

* Execução da aplicação de fato
```
ng serve
```
Esse comando deve ser executado no local do projeto. Por padrão a aplicação subirá na porta 4200, caso seja necessário a alteração da mesma, isso poderá ser realizado com o simples comando
```
ng serve --port {número da porta}
```
A alteração da porta não afetará o funcionamento da aplicação. O importante e subir em alguma porta :-).

## Autor
* **Tiago Guimarães da Silva**
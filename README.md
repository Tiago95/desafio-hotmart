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
Esse comando deve ser executado no local do projeto. E isso pode demorar um pouco :disappointed_relieved:. Melhor pegar um café :grin:!!

* Execução da aplicação de fato
```
ng serve
```
Esse comando deve ser executado no local do projeto. Por padrão a aplicação subirá na porta 4200, caso seja necessário a alteração da mesma, isso poderá ser realizado com o simples comando
```
ng serve --port {número da porta}
```
A alteração da porta não afetará o funcionamento da aplicação. O importante e subir em alguma porta :grin:.

### Excecução aplicação Back-End

* Instalação do Java 8

```
http://www.oracle.com/technetwork/pt/java/javase/downloads/index.html
```

* Instalação Maven

```
https://maven.apache.org/download.cgi
```

* Instalação das dependências do projeto

```
mvn clean install
```
Esse comando deve ser executado no local do projeto.

* Execução da aplicação de fato

```
Você poderá executar a class desafio-hotmart-back-end/src/main/java/br/com/hotmart/desafiohotmart/DesafioHotmartApplication.java como jar comum (java -jar)
```

Por padrão a aplicação subirá na porta 8080, caso seja necessário a alteração da mesma, isso poderá ser realizado com um alteração simples no arquivo desafio-hotmart-back-end/scr/main/resources/application.properties.

```
server.port = {numero da porta}
``` 

Isso poderá ocasionar em um mal funcionamento da aplicação tendo em vista que a aplicação do front-end está apontando para esse porta. Calma não entre em desespero :dizzy_face:, após a alteração da porta com a configuração acima, você poderá alterar o arquivo que está desafio-hotmart-front-end/src/app/app.component.ts nesse pequeno trecho.

```
static API_URL: string = "http://localhost:8080";
``` 

Pronto a aplicação já estará em perfeito funcionamento novamente. :stuck_out_tongue_winking_eye:

## Detalhamento do Aplicação

### A aplicação possuí os seguintes recuros

* Uma tela de login onde os usuários cadastrados na aplicação poderam entrar na aplicação.

* Uma tela de cadastro onde novos usuários poderam ser cadastrar na aplicação.

* Uma tela de dashboard onde o usuário terá informações como seu nick, seu status, foto, contatos, mensagens não lidas, conversas, chat e logout.

* Uma tela de contatos onde o usuário poderá ver todos os outros usuários que estão cadastrados na aplicação. Nessa tela ele também poderá ver o status dos usuários (online e offline), bloquear um usuário, desbloquear um usuário, solicitar uma amizade, aceitar uma amizade, recusar uma amizade e entrar na tela de chat com algum usuário.

* Uma tela de chat/conversas onde o usuário poderá ver o seu histórico de conversas, conversar com outros usuários, ver status dos usuários e outros dados do usuário.

* Um menu lateral onde o usuário poderá navegar em todos os recursos citados acima.

## Detalhamento do Aplicação X Desafio Proposto

* 1. Tela de Login - É a primeira página do aplicação.

* 1.1 Tela de cadastro - Há um boptão de cadastrar no rodapé do login. Esse botão levará o usário a tela em questão.

* 2. Tela de Contatos - Após o usuário efetuar o login ou se cadastrar na aplicação ele será direcionado para a página de dashboard onde haverá um card de nome contatos que o levará a uma tela onde ele verá todos os usuários cadastrados na aplicação, o seu status e funcções como bloquear, desbloquear, solicitar amizade, chat, etc...
 
* 2.1 Tela Inicial - Assim que o usuário entrar na aplicação ou receber uma nova mensagem ele será notificados de duas formas diferentes. A primeira é uma mensagem que aparecerá no topo central da aplicação e ficará disponivel por um tempo determinado. Já a segunda é um icone (em forma de caixa de correio) no topo direito que mostrará quantidade de mensagens não lidas pelo usuário e quais as suas últimas mensagens. As duas formas mencionadas são atualizadas sempre que o usuário receber uma mensagem, ler uma mensagem ou entrar na aplicação.

* 3. Envio de mensagens - Após o usuário efetuar o login ou se cadastrar na aplicação ele será direcionado para a página de dashboard onde haverá um card de nome conversas que o levará a uma tela onde ele verá todas as suas conversas até o momente e também poderá realizar nova troca de mensagens. Caso o usuário esteja online o mesmo receberá a mensagem de forma instantânea, em caso contrário ele receberá a mensagem na primeira oportunidade. O usuário tem outras formas de acessa essa tela, ele poderá acessar pela telade contatos (onde há um link direto), pelas notificações de mensagens não lidas e pelo menu.

* 4. Suporte a 100 usuários simultâneos - Infelizmente não consegui realizar o teste de carga com o JMETER devido a falta de tempo, pois essa semana foi muito pesada em relação a volume de trabalho no meu local de trabalho atual, o que me gerou algumas horas extras. :worried: Mas pela minha experiência e pelas tecnologias utilizadas no projeto, eu quase posso dar certeza que ela aguenta!! :grinning:

* 5. Lista de contatos - Esse item foi realizado conforme mencionado no item 2.

* 6. Tela de hitórico de mensagens - Esse item foi implementado junto a tela de envio de mensagens (detalhado no item 3.). Nessa tela em questão você poderá consultar suas mensagens e enviar outras.

* 7. Solicitação de amizade - Esse item foi desenvolvimento e pode ser manipulado na tela de contatos (conforme mencionado no item 2.). Como a especificação não estava muito clara em relação ao que o usuário ganharia com essa amizade eu a implementei da seguinte forma: No cadastro de usário o mesmo poderá informar se permiti ou não o recebimento de mensagens anonimas, ou seja, de pessoas que não são amigas. Em caso positivo ela receberá notificações de mensagens sem o pedido de mensagem ser aceito, já em caso negativo ela somente receberá notificações de pessoas que são amigas dela!! :couple::ok_woman::raising_hand::no_good:

* 8.  Bloqueio/Desbloqueio de usuário - Esse item foi desenvolvimento e pode ser manipulado na tela de contatos (conforme mencionado no item 2.). Caso o usuário esteja bloqueado ele até enviará a mensagem a outro usuário, porém o outro usuário não receberá a mesma (estilo Whatsapp). :see_no_evil:

* 9. Edição de mensagens enviadas - Esse item não foi desenvolvido devido ao mesmo problema relatado no item 4. :grinning::grinning:

* 10. Envio de mensagens de audio/video - Esse item não foi desenvolvido devido ao mesmo problema relatado no item 4 e 9. :grinning::grinning::grinning:

## Autor

* **Tiago Guimarães da Silva**
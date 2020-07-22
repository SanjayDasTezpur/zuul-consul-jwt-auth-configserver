# zuul-consul-jwt-auth-configserver-hystrix-feign-gradle

This is a project based on java, spring, spring boot, spring cloud, spring security, Netflix OSS etc, to demonstarate a pure microservice architecture (Distributed Architecture).
Project Contains 
 - Consul
 - ApiGateway
 - AuthService
 - ConfigServer
 - Some Dummy Service.   

## Usage
 - zuul is for proxy/ApiGateway
 - consul is for service registry
 - hystrix if for circuit breaking (fallback mechanism)
 - feign is for inter service communication using rest client.
 - Sleuth for distributed tracing (tracing of request thought out the production)
 - gradle - a build & automation tool
 
## Installation

Use any Java IDE, import each project in IDE and run (see command bellow)  for each project, sequnce will Run Consul agent , and then ConfigurationService -> AuthService -> ApiGateWay -> OtherServices .

```bash
./gradlew bootrun
```

## Brief Explanation
In this project Consul is used as a Service discovery, a vital part of microservice , Where all the microservices area registered by themselves. A config-Server (Configuration service)
which will hold the configuration. All the microservices will have their config/application.yml in ConfigServer, which is also called a cloud based configuration. ApiGateWay a front facing service 
which will filter and route REST call to their respctive services. A auth service which will validate( authorization and authentication)a rest call for a particular user, and if found then it will create a jwt for that user
and return the jwt. And Other services will call each other using a fieng client.

In this era of microservies , this above architecture (simillar) is used in production by many companies, with a little bit of twist and tweeks. e.g.
Config Server can use github repositories for storing configs rather than storing it in local shared location, AuthServer can be integrated with OAuth2 Resource Server(Google, Facebook,
GitHub) 

## Flow
Here , the flow is, first you have to take your token from ApiGateway by calling api [POST http://localhost:8080/login body={"username:"admin", "password":"admin"}](http://localhost:8080/login)
with /login ApiGateWay will redirect request to AuthService , authservice will take the body user and password and will check in its repository if user is found and password is matched ,
then it will find its ROLE (ADMIN or USER) , will attach all information, create a jwt by using HS256 algorithm, and return it. And if not user is not found it will simply retun 
the call with 401 status. 
Once you get the token, now you can use the token in Authorization header "Authorisation: Bearer <TOKEN>" and you can hit the other services which is resided behind API gateway.
e.g. if you have a token of user admin (has ROLE admin) and you can hit api [GET http://localhost:8080/NotificationService/admin/hello](http://localhost:8080/NotificationService/admin/hello)
you will get resposne withh 200 status, if your token is wrong then you get 401 unauthorized. Suppose you get token of user [POST http://localhost:8080/login body={"username:"sanjay", "password":"sanjay"}](http://localhost:8080/login)
then you can hit [GET http://localhost:8080/NotificationService/api/hello](http://localhost:8080/NotificationService/api/hello), it will return response with 200 status. But with User token
if you hit  [GET http://localhost:8080/NotificationService/admin/hello](http://localhost:8080/NotificationService/admin/hello) you will recieve no response with 403 forbidden status.
And suppose you want a api which should be open, no auth is require the you can use [GET http://localhost:8080/NotificationService/open/hello](http://localhost:8080/NotificationService/open/hello)

So if you have this architecture , then you just need to write rest controller of a microservice which should have a REST path convention (e.g. start with /api and /admin ), which will automatically 
require authentication , for authorization , starts with /api can be for user and /admin for admin of a microservice.
    
 
    

# Micro Services


### Mircoservice Architecture

*	Rest templates to communicate with other microservices
*	Netflix Eureka Server / Discovery Client 
*	Hystrix Circuit Breaker

### Spring Cloud
*	Spring Cloud Config Configuration (Spring cloud server to provide configuration file data from git repository)
*	Spring Cloud Config Server (Symmetric Encryption) sensitive property values (using Java Cryptography Extension (JCE))
*	Asymmetric Encryption Spring Cloud Config Server (key-pair file .jks)

### Feign Client / Zuul API Gateway
*	Netflix Feign Client ( Call to other microservice )
*	Zuul Api Gateway

### Microservice Monitoring
*	Spring Boot Admin ( Monitoring tool for all microservices )
*	Spring Boot Admin Email Notification for instance status UP / OFFLINE
*	Distributed Log Tracing using Sleuth and Zipkin

### Spring Cloud API Gateway
*	Spring Cloud Gateway using property based config
*	Spring Cloud Gateway using Java based config
*	Spring Cloud Gateway Filters (Pre Filter / Post Filter) property based config
*	Spring Cloud Gateway Filters (Pre Filter / Post Filter) Java based config
*	Spring Cloud Gateway Custom Filters / Global Filters
*	Spring Cloud Gateway with Netflix Eureka 
*	Spring Cloud Gateway with Netflix Eureka and Netflix Hystrix



## URLs

### Eureka Server
> http://localhost:8761/


### Spring Boot Admin
> http://localhost:8093/

#### Email Notification Setting:

Open application.properties and change following gmail settings:


spring.mail.username: `your_email`

spring.mail.password: `your_password`

spring.boot.admin.notify.mail.to: `to_email_address`


### Spring Cloud API Gateway 
> http://localhost:8080/client1

Response
```shell
{
  "message": "Hello World (header: first-request-header ) - Hello from git configuration file",
  "message-2": {
    "message": "Hello World 2",
    "message-3": {
      "message": "Hello World 3 from port: 8003"
    },
    "message-7": {
      "message": "Hello World 7 from port: 8008"
    }
  }
}
```


### Start Microservice on different port
```shell
mvn clean install
java -jar <path-to-jar> --server.port=<port>
```

### Hystix Dashboard
> http://localhost:8060/hystrix

Hystrix Stream URL:
> http://localhost:8001/actuator/hystrix.stream

### Zipkin Tracing

Start Zipkin Server

```shell
java -jar zipkin/zipkin-server-2.12.9-exec.jar
```

Zipkin URL
> http://localhost:9411/


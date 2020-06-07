# transactions-demo

### Overview
Sandbox project to play with global transactions.

### Atomikos
Spring transactions abstraction configured with [Atomikos JTA](https://www.atomikos.com/Documentation/SpringBootIntegration).
Global transactions include:
 * ActiveMQ (embedded) transactions
 * JPA (embedded H2) transactions

### Build, deploy, run locally
 * Springboot BAU

## Local environment 
  
| Component | Endpoint | 
|--------------------------|----------------------------------------------------------------|
| Embedded broker console      | http://localhost:8080/actuator/hawtio                                |
| Embedded DB console          | http://localhost:8080/h2        |
| Swagger UI                    | http://localhost:8080        |




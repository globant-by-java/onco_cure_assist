# Install

### Java 1.8
### PostgreSQL 10.6
### Docker


## Flyway - override application.yml settings
```yaml
spring:
  datasource:
    url: jdbc:postgresql://{your_host}:{your_port}/{your_db_name}
    username: <your_username>
    password: <your_password>
```    

## Swagger
To see all endpoints of the application use swagger: {application_path}/swagger-ui.html#/
# Noticias
CRUD simples de Notícias em uma aplicação utilizando:
 - Spring Boot
 - Spring Data JPA
 - Hibernate
 - MySQL
 - Docker

# docker-compose file
```
version: '2.4'
x-database-veriables: &database-variables
  SPRING_DATASOURCE_URL: jdbc:mysql://mysql:3306/noticias?useSSL=false&createDatabaseIfNotExist=true&allowPublicKeyRetrieval=true
  SPRING_DATASOURCE_USERNAME: root
  SPRING_DATASOURCE_PASSWORD: root
services:
  db:
    image: mysql
    container_name: mysql
    environment:
      MYSQL_ROOT_PASSWORD: root
    ports:
      - "3306:3306"
    volumes:
      - noticias:/var/lib/mysql
    mem_limit: 512mb

  spring-boot-noticias:
    image: gabrielgua/spring-boot-noticias:0.0.1-SNAPSHOT
    ports:
      - "8080:8080"
    environment:
      <<: *database-variables
    mem_limit: 512mb

volumes:
  noticias:
```
> @gabrielgua


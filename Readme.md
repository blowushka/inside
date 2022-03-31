
# How to start:
1. Server using port 8081, so make sure it's free.
2. Also, application using PostgreSQL, with liquibase script, which one allows you to create mandatory tables and fill them with columns.
3. Just run ***bootRun*** in gradle sidebar.
4. Application using swagger-ui, so you can go to [**Swagger**](http://localhost:8081/swagger-ui/index.html) after server start up and check what you can do.
5. You should use authorize endpoint first, so you get JWT token which one you need to use for other endpoints. Note that before the token in the bearerToken field you should add "Bearer_" prefix(after "Bearer" there is underscore).

In case of using docker just run **docker-compose up** command


# Endpoints FAQ:
## User

Put **"/users"** - create new user and add him to DB

## Message

Post **"/message"** - Add new message according to username

Get **"/message/history/{amount}"** - Get {amount} number of messages from DB, according to username

## Authorize

Post **"/authorize"** - Authenticate user and generate JWT token


# Stack
1. Java 17
2. Spring Boot
3. Spring Security
4. Spring OpenAPI
5. Swagger
6. JUnit, Mockito
7. PostgreSQL, Liquibase
8. Lombok
9. Mapstruct
10. HikariCP
11. JJWT

# CURL requests
1. Create new user with unique name:\
   $ curl -X 'PUT'   
   'http://localhost:8081/users'   
   -H 'accept: \*/*'  
   -H 'Content-Type: application/json'   
   -d '{\
   "name": "**your name**",\
   "password": "**your password**"\
}'

2. Authorize user:\
   $ curl -X 'POST'   
   'http://localhost:8081/authorize'   
   -H 'accept: \*/*'   
   -H 'Content-Type: application/json'   
   -d '{\
   "name": "**your name**",\
   "password": "**your password**"\
}'

3. Save message to DB:\
   $ curl -X 'POST'   
   'http://localhost:8081/message'   
   -H 'accept: \*/*'   
   -H 'bearerToken: **past bearerToken from Authorize endpoints response here**'   
   -H 'Content-Type: application/json'   
   -d '{\
   "name": "**your name**",\
   "message": "**your message**"\
}'

4. Get messages from DB:\
   $ curl -X 'GET' 
   'http://localhost:8081/message/history/{amount}?userName=userName&amount=2'   
   -H 'accept: \*/*'   
   -H 'bearerToken: **past bearerToken from Authorize endpoints response here**'


# javaservice1

A production-ready Java 17 microservice built with Spring Boot and Maven.

## Project Structure

```
javaservice1/
├── pom.xml
└── src/
    ├── main/
    │   ├── java/com/example/javaservice1/
    │   │   ├── JavaService1Application.java   # Application entry point
    │   │   ├── controller/
    │   │   │   ├── HealthController.java       # GET /health
    │   │   │   └── HelloController.java        # GET /hello
    │   │   └── service/
    │   │       ├── HealthService.java
    │   │       └── HelloService.java
    │   └── resources/
    │       └── application.yml                 # Configuration
    └── test/
        └── java/com/example/javaservice1/
            ├── controller/
            │   ├── HealthControllerTest.java
            │   └── HelloControllerTest.java
            └── service/
                ├── HealthServiceTest.java
                └── HelloServiceTest.java
```

## Prerequisites

- Java 17+
- Maven 3.8+

## Running Locally

```bash
# Build the project
mvn clean package

# Run the application
mvn spring-boot:run
```

The service starts on **http://localhost:8080** by default.

## API Endpoints

| Method | Path      | Description                         | Response                     |
|--------|-----------|-------------------------------------|------------------------------|
| GET    | `/health` | Returns service health status       | `{"status": "OK"}`           |
| GET    | `/hello`  | Returns a greeting message          | `{"message": "Hello"}`       |

### Example requests

```bash
curl http://localhost:8080/health
# {"status":"OK"}

curl http://localhost:8080/hello
# {"message":"Hello"}
```

## Running Tests

```bash
mvn test
```

## Configuration

All configuration is in `src/main/resources/application.yml`. Key settings:

| Property               | Default | Description              |
|------------------------|---------|--------------------------|
| `server.port`          | 8080    | HTTP server port         |
| `spring.application.name` | javaservice1 | Application name  |
| `logging.level.root`   | INFO    | Root log level           |
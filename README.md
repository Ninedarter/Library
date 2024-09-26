# Spring Boot REST API for Book Information

This project is a Spring Boot REST API that provides information about books. The application stores data in an H2 in-memory database and allows clients to filter books by various criteria and rate them.

## Features

- **Filter books** by:
  - Title
  - Year
  - Author
  - Rating
- **Rate books** with a score from 1 to 5 stars.
- **Documentation** available via Swagger UI for easy testing of endpoints.

## Technologies Used

- Java 11
- Spring Boot
- H2 Database
- Swagger
- Docker

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven (for building the project)
- Docker (optional, for running in a container)

### Running the Application

#### Running Locally

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd <repository-name>
2.Build the application using Maven:
   ```bash
      mvn clean install
      mvn spring-boot:run
3. Access and test endpoints via Swagger ui :
   ```bash
   http://localhost:8080/swagger-ui/index.html


You can find docker image on this repository
  ```bash
  https://hub.docker.com/repository/docker/ninedarter/app/general

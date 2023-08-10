# Spring Boot MongoDB Integration for a Credit Card Management System

This is the first project task demonstrating how to integrate Spring Boot with MongoDB for building RESTful APIs as per the problem statement.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java 8 or higher installed
- Maven installed (depending on your preference)

## Getting Started

1. Clone this repository:

    ```bash
    git clone https://github.com/DikshaNegi13/Muskaan1_Hackathon.git
    ```

2. Navigate to the project directory:

    ```bash
    cd your-spring-boot-mongodb-project
    ```

3. Build the project:

    For Maven:
    ```bash
    mvn clean install
    ```

4. Run the application:

    For Maven:
    ```bash
    mvn spring-boot:run
    ```

5. Access the API:

    The API should be available at `http://localhost:8080/api/users`.

## Endpoints

- `GET /api/users`: Get a list of all users.
- `GET /api/users/{id}`: Get user details by ID.
- `POST /api/users`: Create a new user.
- `PUT /api/users/{id}`: Update an existing user by ID.
- `DELETE /api/users/{id}`: Delete a user by ID.

## Configuration

- MongoDB connection details can be configured in `src/main/resources/application.properties`.

## Contributing

Contributions are welcome! Please create a pull request if you'd like to contribute to this project.

## License

This project is not licensed.

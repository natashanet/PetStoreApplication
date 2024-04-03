Pet Store Application

The Pet Store Application is a Java Spring Boot project designed to manage a virtual pet store. It allows users to buy pets, manage their budgets, and view purchase history.
Features

    Buy Pets: Users can buy pets from the store using their available budget.
    Pet Types: Supports both cats and dogs as pet types, each with different pricing algorithms.

Technologies Used

    Java
    Spring Boot
    Hibernate
    PostgreSQL
    Gradle
    jUnit
    Mockito

Usage

    Access locally the application at http://localhost:8080.
    If 8080 is already used, the server port can be configured in application.properties.
    Use the provided REST endpoints to interact with the application.

Testing

    Furthermore, unit tests are located in the directory.
    Run tests using ./gradlew test.

GitHub Steps - Guide

    Command for cloning the repository: git clone https://github.com/natashanet/PetStoreApplication.git
    Fork the repository.
    Create a new branch.
    Commit your changes.
    Push to the branch.
    Submit a pull request.

Database Connection

    The database that is used is PostgreSQL.
    So despite Postgre, it is used pgAdmin.
    The database connection can be configured in application.properties file.
    For db connection, please change the follwing lines of code in application.properties file:
    spring.datasource.username=postgres
    spring.datasource.password=yourPassword

How to start the project?

    To start the application run the MainApplication.main() in the following file MainApplication.java.


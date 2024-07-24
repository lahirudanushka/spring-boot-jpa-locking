

---

# Spring Boot JPA Locking Mechanisms

This repository contains a Spring Boot application demonstrating the usage of various JPA locking mechanisms, including `PESSIMISTIC_READ`, `PESSIMISTIC_WRITE`, `PESSIMISTIC_FORCE_INCREMENT`, `OPTIMISTIC`, and `OPTIMISTIC_FORCE_INCREMENT`. This sample is intended to provide hands-on experience with different locking strategies and their behaviors in concurrent scenarios.


## Table of Contents
- [Overview](#overview)
- [Technologies Used](#technologies-used)
- [Prerequisites](#prerequisites)
- [Setup Instructions](#setup-instructions)
- [Usage](#usage)
- [Endpoints](#endpoints)
- [Testing](#testing)
- [Contributing](#contributing)
- [License](#license)

## Overview
This application provides a set of RESTful APIs to demonstrate how different JPA locking mechanisms work in a concurrent environment. By running these APIs in parallel, you can observe how each lock type behaves and affects transaction isolation and data consistency.

## Technologies Used
- Java 17
- Spring Boot 3.2.x
- Spring Data JPA
- Postgres Database 
- Maven

## Prerequisites
- Java 17 or higher
- Maven 3.6.3 or higher

## Setup Instructions
1. **Clone the repository:**
   ```sh
   git clone [https://github.com/yourusername/spring-boot-jpa-locking-sample.git](https://github.com/lahirudanushka/spring-boot-jpa-locking.git)
   cd jpa-locking
   ```

2. **Build the project:**
   ```sh
   mvn clean install
   ```

3. **Run the application:**
   ```sh
   mvn spring-boot:run
   ```

   The application will start on `http://localhost:1010`.

## Usage
The application exposes several endpoints to demonstrate different JPA locking mechanisms. You can use tools like Postman or curl to interact with these endpoints.

### Endpoints
The endpoints and the curls can be found in the swagger document `http://localhost:1010/swagger-ui.html`

![image](https://github.com/user-attachments/assets/063821c3-d962-426e-9cc1-4e3d9f6f1676)


## Testing
You can test the behavior of these endpoints by making concurrent requests and observing the outcomes. For example, using two different terminal windows or Postman tabs, you can simultaneously send `POST` requests to deduct the balance and see how each locking mechanism handles the concurrency.

### Example Tests
1. **PESSIMISTIC_WRITE Test:**
    - Open two terminal windows.
    - In the first terminal, run:
      ```sh
      curl -X POST -H "Content-Type: application/json" -d '' http://localhost:1010/pessimistic-write/deduct-balance/1/5
      ```
    - In the second terminal, quickly run the same command before the first one completes.

2. **OPTIMISTIC_FORCE_INCREMENT Test:**
    - Repeat the steps above but use the `optimistic-force-increment` endpoint.

## Contributing
Contributions are welcome! Please fork the repository and create a pull request with your changes.

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

This README provides a comprehensive guide for setting up, using, and testing the application, allowing users to explore the different JPA locking mechanisms in a hands-on manner.

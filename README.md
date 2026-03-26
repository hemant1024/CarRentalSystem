# Car Rental System

## Overview

This is a full-stack **Car Rental System** that allows customers to browse and book cars, and agencies to manage vehicle inventory.
The backend is built using **Java Spring Boot**, and the frontend uses **HTML, CSS, JavaScript, and Bootstrap**.
Authentication is handled using **JWT (JSON Web Token)**.

---

## Tech Stack

* Java
* Spring Boot
* Spring Security (JWT)
* Spring Data JPA
* MySQL
* HTML / CSS / JavaScript
* Bootstrap

---

## Prerequisites

Make sure the following are installed:

* Java 17 or later
* Maven
* MySQL
* Git

---

## Database Setup

Create a database in MySQL:

```sql
CREATE DATABASE car_rental;
```

Import the provided SQL file:

```text
car_rental.sql
```

You can import using:

* MySQL Workbench
  OR

```bash
mysql -u root -p car_rental < car_rental.sql
```

---

## Backend Configuration

Open:

```text
src/main/resources/application.properties
```

Update database credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/car_rental
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

server.port=8080
```

---

## Run Backend Locally

From the backend root directory, run:

```bash
mvn clean install
mvn spring-boot:run
```

OR

```bash
java -jar target/*.jar
```

Backend will start at:

```text
http://localhost:8080
```

Test API:

```text
http://localhost:8080/api/cars
```

---

## Run Frontend Locally

Open the frontend entry file:

```text
index.html
```

You can use:

* Live Server (VS Code)
  OR
* Any browser

Frontend will run at:

```text
http://127.0.0.1:5500
```

---

## Default Application Flow

### Customer

1. Register as CUSTOMER
2. Login
3. View available cars
4. Book a car
5. View bookings
6. Cancel booking

### Agency

1. Register as AGENCY
2. Login
3. Add car
4. Update car
5. Delete car
6. View bookings

---


## API Endpoints

### Authentication

```text
POST /api/auth/register
POST /api/auth/login
```

### Cars

```text
GET    /api/cars
POST   /api/cars
PUT    /api/cars/{id}
DELETE /api/cars/{id}
```

### Bookings

```text
POST   /api/bookings
GET    /api/bookings
DELETE /api/bookings/{id}
```

### Agency

```text
GET /api/agency/bookings
```

---

## Security

* JWT authentication implemented
* Passwords encrypted using BCrypt
* Role-based access control (CUSTOMER / AGENCY)

---

## Author

Hemant Wandhare
Full Stack Developer
Java | Spring Boot | Web Development
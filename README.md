# Car Rental System

## Project Overview

The **Car Rental System** is a full-stack web application that allows customers to browse and book rental cars, while agencies can manage their vehicle inventory. The system implements role-based authentication using JWT and supports complete booking management.

This project was developed as part of a guided software development walkthrough and demonstrates the integration of a Java Spring Boot backend with a simple HTML, CSS, and JavaScript frontend.

---

## Tech Stack

### Backend

* Java
* Spring Boot
* Spring Security (JWT Authentication)
* Spring Data JPA
* MySQL
* Maven

### Frontend

* HTML
* CSS
* JavaScript
* Bootstrap

### Tools

* Postman (API Testing)
* MySQL Workbench
* VS Code / IntelliJ IDEA

---

## Features

### Authentication

* User registration (Customer / Agency)
* Secure login using JWT
* Role-based access control
* Logout functionality

### Customer Features

* View available cars
* Book a car
* View personal bookings
* Cancel booking

### Agency Features

* Add new car
* Update car details
* Delete car
* View bookings for their cars

---

## Database Tables

The system uses the following tables:

```
users
cars
bookings
```

---

## How to Run the Project

### Step 1 — Start MySQL

Make sure MySQL server is running.

Create a database:

```
CREATE DATABASE car_rental;
```

Update database configuration in:

```
application.properties
```

Example:

```
spring.datasource.url=jdbc:mysql://localhost:3306/car_rental
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

---

### Step 2 — Run Backend

Open the backend project and run:

```
mvn spring-boot:run
```

Or run the main class:

```
CarRentalApplication.java
```

Backend will start at:

```
http://localhost:8080
```

---

### Step 3 — Run Frontend

Open:

```
frontend/login.html
```

Frontend will run at:

```
http://127.0.0.1:5500
```

---

## API Endpoints

### Authentication

```
POST /api/auth/register
POST /api/auth/login
```

### Cars

```
GET    /api/cars
POST   /api/cars
PUT    /api/cars/{id}
DELETE /api/cars/{id}
```

### Bookings

```
POST   /api/bookings
GET    /api/bookings
DELETE /api/bookings/{id}
```

### Agency

```
GET /api/agency/bookings
```

---

## Test Users (Example)

### Customer

```
Email: customer@test.com
Password: 123456
Role: CUSTOMER
```

### Agency

```
Email: agency@test.com
Password: 123456
Role: AGENCY
```

---

## Notes

* JWT authentication is used for secure API access.
* Role-based redirection is implemented after login.
* CORS is configured to allow frontend-backend communication.
* Passwords are securely encrypted using BCrypt.

---

## Author

**Name:** Hemant Wandhare
**Role:** Full Stack Developer
**Technology:** Java Spring Boot + Angular/HTML/JS

---

## Conclusion

This project demonstrates a complete end-to-end implementation of a role-based car rental system with authentication, booking management, and inventory control. It fulfills the functional requirements of the walkthrough and provides a working full-stack solution suitable for academic submission.

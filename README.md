# Employee Management System

Spring Boot REST API for managing employee records with JWT authentication and role-based access control.

## Tech Stack
- Java 17
- Spring Boot, Spring Data JPA, Spring Security
- MySQL
- JWT (JSON Web Tokens)
- Maven

## How to Run

1. Make sure MySQL is running and create the database:
   ```sql
   CREATE DATABASE employee_db;
   ```

2. Update `src/main/resources/application.properties` with your DB credentials.

3. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

4. The API will be available at `http://localhost:8080`

## API Endpoints

### Authentication (Public)
| Method | URL | Description |
|--------|-----|-------------|
| POST | `/api/auth/register` | Register a new user |
| POST | `/api/auth/login` | Login and get JWT token |

### Employee Management (Protected)
| Method | URL | Access | Description |
|--------|-----|--------|-------------|
| GET | `/api/employees` | USER, ADMIN | Get all employees (paginated) |
| GET | `/api/employees/{id}` | USER, ADMIN | Get employee by ID |
| POST | `/api/employees` | ADMIN only | Create new employee |
| PUT | `/api/employees/{id}` | ADMIN only | Update employee |
| DELETE | `/api/employees/{id}` | ADMIN only | Delete employee |

### Pagination & Sorting
```
GET /api/employees?page=0&size=10&sort=name,asc
```

## Swagger UI
Visit `http://localhost:8080/swagger-ui.html` for interactive API docs.

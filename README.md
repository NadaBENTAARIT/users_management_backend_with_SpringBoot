
# Project Documentation
## Versions Used
- **Java**: 17
- **Spring Boot**: 3.3.4
- **Maven**: 3.3.4

## Instructions to Run the Back-End Project

1. Extract the zip file or clone the Git repository by running:  
   `git clone https://github.com/NadaBENTAARIT/users_management_backend_with_SpringBoot.git`
2. Navigate to the project directory.
3. Build the project using Maven by running:  
   `mvn clean install`
4. Start the Spring Boot application by running:  
   `mvn spring-boot:run`
5. The API will be available at:  
   `http://localhost:8080/`

### Access Swagger Documentation:
- Swagger UI is available at:  
  `http://localhost:8080/swagger-ui/index.html#/user-controller`

### Access H2 Database Console:
- The H2 database console is available at:  
  `http://localhost:8080/h2-console/`

  **Database Connection Details**:
    - **JDBC URL**: `jdbc:h2:mem:dcbapp`
    - **Username**: `nada`
    - **Password**: `password`

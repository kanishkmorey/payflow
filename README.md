# PayFlow

A simplified UPI-like payments backend built with Spring Boot.

## How to Run

1. Make sure Java 17 is installed
2. Open terminal in the project folder
3. Run the following command:

```bash
./mvnw spring-boot:run
```

The app starts on port 8080 automatically.

## H2 Database Console

To view the database, open your browser and go to:
http://localhost:8080/h2-console
Use these credentials:
- JDBC URL: `jdbc:h2:mem:payflowdb`
- Username: `sa`
- Password: *(leave empty)*

## Project Structure

The project is divided into four packages:

**Entity Layer**
Contains plain Java classes that map directly to database tables. In PayFlow, the User and Transaction classes define the structure of our data. I had to annotate the User class with @Table(name = "users") because "user" is a reserved keyword in H2 SQL — this is a common real-world edge case that also applies to production databases like PostgreSQL and MySQL.

**Repository Layer**
This layer talks to the database without writing complex SQL. By extending JpaRepository, Spring Data automatically provides built-in methods to save and find records.

**Service Layer**
Holds the business logic of PayFlow. UserService and TransactionService sit between the controller and repository, handling things like registering users and processing money transfers.

**Controller Layer**
Exposes the API endpoints to the outside world. UserController and TransactionController listen for incoming HTTP requests like POST /users and route them to the correct service methods.

## Spring Boot Features in PayFlow

**Embedded Server**
Spring Boot automatically bundles and starts a Tomcat server on port 8080. I did not install or configure any external server software — it just works out of the box.

**Auto-configuration**
Spring Boot detected the H2 and JPA dependencies in pom.xml and automatically wired up the database console, JPA settings, and DataSource. No XML configuration was needed.

**Production-ready Defaults**
Spring Boot set up HikariCP as the database connection pool automatically. This is a professional-grade tool used in real backends and required zero setup on my end.

## JPA Query for findByUpiId

When findByUpiId is called, JPA reads the method name and generates this SQL automatically:

```sql
select u1_0.user_id, u1_0.balance, u1_0.name, u1_0.phone_number, u1_0.upi_id 
from users u1_0 
where u1_0.upi_id=?
```

JPA breaks down the method name — "find" means SELECT, "By" means WHERE, and "UpiId" maps to the upi_id column. The ? is a placeholder that gets replaced with the actual value at runtime, which also protects against SQL injection attacks.

## Query Approaches Comparison

**Derived Method Names** (e.g. findByUpiId)
Spring writes the query entirely from the method name. Fast and easy for simple lookups.

**@Query with JPQL** (e.g. findByBalanceGreaterThan)
Uses Java class and field names instead of database table names, making it flexible and database-independent.

**Native SQL**
Uses raw database-specific SQL. This is the least preferred approach because it ties your code to one specific database — if you switch from H2 to MySQL, native queries might break.
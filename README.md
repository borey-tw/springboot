# Introduction

- Java platform:
    - Standard Edition(SE)
      - [https://docs.oracle.com/en/java/javase/17/docs/api/index.html](https://docs.oracle.com/en/java/javase/17/docs/api/index.html)
    - Enterprise Edition(EE)
      - [https://docs.oracle.com/javaee/7/api/toc.htm](https://docs.oracle.com/javaee/7/api/toc.htm)
    - Micro Edition(ME)
- JDK - JRE -
  JVM [https://www.guru99.com/difference-between-jdk-jre-jvm.html](https://www.guru99.com/difference-between-jdk-jre-jvm.html)
- POJO (normal class without extend, implement or annotation) vs JavaBean(class with private property and use
  setter/getter/is for access to property)
- Spring main feature: DI & IoC - (That makes the application to be the loose coupling application.)
- Spring Boot main feature: Auto configuration

# Spring Boot (REST API)

- CRUD
- Error Handler
- Validation
- Unit Test
- Security(Authentication, Authorization, CORS)
- Logging
- DB Migration

## CRUD

#### Dependencies

- Spring Web
- Spring Data JPA
- H2 Database
- Mysql

#### Structure

```
course
 ├── CourseApplication.java [Starter]
 ├── CourseController.java [Request Handler]
 ├── CourseRepository.java [Repository]
 ├── Course.java [Entity]
 └── LoadDatabase.java [sample data]
```

#### Instruction

#### REST API (without data)

1. Create starter class with `@SpringBootApplication`
   This annotation will create `ApplicationContext` and includes 3 annotations inside:
    - `@Configuration`
    - `@EnableAutoConfiguration` - to add bean to context and dependency inject to bean
    - `@ComponentScan` - load all class and other component that have annotation

   Add in the main method to launch the spring ```SpringApplication.run(StarterApplication.class, args);```
2. Create REST Controller class with `@RestController` (Spring Web) to handle request. The part that convert Json to
   object or object to Json is handled by the Spring Web already, in controller we only work with java object only.
    - To define prefix, use `@RequestMapping("/api")` on class
    - To handle different method, use `@GetMapping("/courses")`, `@PostMapping("/courses/{id}")`
      , `@PutMapping("/courses/{id}")`, `@DeleteMapping("/courses/{id}")` on method
    - To get param, use `@RequestParam(value = "id", defaultValue = 1) Long id`
    - To get param variable, use `@PathVariable Long id`

#### Access to Database (with data)

3. Create Entity using `@Entity` (JPA) - the definition of data
4. Create Repository Interface extend from `Spring Data JPA` - when extend Spring Data JPA we connect the entity to
   Spring Data JPA
5. Then add that interface to be the property of the controller, that need to inject when app start. Finally, in
   controller can call the method of Spring Data JPA (ORM) to save, update, delete with entity that is direct interact
   with table in database.

#### Configuration database

- Use in-memory database
    - Add dependency in pom.xml
    - No need to create table
    - No config properties(if no datasource property is provided, Spring Data JPA will use h2.)

```xml

<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
```

- Use MySQL
    - Create tables in MySQL separately
    - Add dependency in pom.xml
    - Add config properties

```xml

<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <scope>runtime</scope>
</dependency>
```

```yml
spring.datasource.url=jdbc:mysql://localhost:3306/<database_name>?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=root
```

Other
reference [https://howtodoinjava.com/spring-boot2/datasource-configuration/](https://howtodoinjava.com/spring-boot2/datasource-configuration/)

## Error Handler

Use a global error handler - all throw errors in application (not only the controller
- [Ref](https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc#:~:text=Using%20%40ControllerAdvice%20Classes,as%20an%20annotation%20driven%20interceptor.))
will be handled in one place.

Need to use 3 to things:

- **Class** for handle all exceptions (use `@ControllerAdvice` on that class)
  with the **methods** for catching each error type (use `@ExceptionHandler({Err1,Err2})` on that method)
- Create custom exception e.g. `Err1` that extends the `RuntimeException`, so in controller we can use
  example `throw new Err1()`
- Create custom response, so in the catching method we can return that response object, and don't forget to use
  `@ReponseBody` and `@ResponseStatus(HttpStatus.NOT_FOUND)` on top the method to tell spring convert our custom object
  http response , or can return `ResposneEntity` instead it includes http headers, body, status already.

E.g.

```java
@ControllerAdvice
public class CourseNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(CourseNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorResponseBody courseNotFoundHandler(CourseNotFoundException e) {
        return new ErrorResponseBody(e.getMessage());
    }
}
```  

Reference

- [https://medium.com/@jovannypcg/understanding-springs-controlleradvice-cd96a364033f](https://medium.com/@jovannypcg/understanding-springs-controlleradvice-cd96a364033f)
- [https://www.toptal.com/java/spring-boot-rest-api-error-handling](https://www.toptal.com/java/spring-boot-rest-api-error-handling)
- [https://spring.io/guides/tutorials/rest/](https://spring.io/guides/tutorials/rest/)
- [https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc](https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc)

## Validation

Reference

- [https://reflectoring.io/bean-validation-with-spring-boot/](https://reflectoring.io/bean-validation-with-spring-boot/)
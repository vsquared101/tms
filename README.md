# Trustee Management System

A Spring Boot application deployed on Pivotal Cloud Foundry which acts as a back-end to the Angular front-end(separate app).

## Using Spring Starter to setup the application

- Navigate to [Spring Starter](https://start.spring.io/).
- Select `Gradle Project`, `Java` and version `2.0.5`.
- Add `Web`, `JPA`, `H2` and `DevTools` as dependencies and click on `Generate Project`.
- Import the project to Eclipse/STS as a Gradle project.

## Create and setup entity class

- Create `Trustee.java` as the entity class for our REST API.
- Add the `@Entity` annotation at the class level and provide the name property as `trustees` for the corresponding database table.
- With the above annotation added a `trustees` table will automatically be created in the H2 database on app startup.
- We can optionally add a `data.sql` script under `src\main\resources` with some insert statements for initial load.
- Make sure to add an underscore for colum names in the above db script for all properties in Trustee.java file which are in camelCase. For e.g. firstName becomes first_name in the insert query.
- Add `@Id` and `@GeneratedValue` annotations to the `id` property of the Trustee class.
- Add the properties as per below code along with getters/setters, toString\(\) and a default no-args constructor.

```java

    @Entity(name="trustees")
    public class Trustee {
    	@Id
    	@GeneratedValue
    	private Long id;
    	
    	private String prefix;
    	private String firstName;
    	private String middleName;
    	private String lastName;
    	private String shortName;
    	private String ssn;
    	private String gender;
    	private String maritalStatus;
    	private String countryOfResidence;
    	private String passport;
    	private String countryOfIssuance;
    	private String issuanceDate;
    	private String expirationDate;
    	private int noOfDependents;
    	
    	public Trustee() {
    	 // default no-args constructor
    	}
    	
    	// getters and setters
    	...
    	
    	// toString method
    	...
    }

```

## Create the repository to allow save/retrieve of data from H2 database

- Create below interface which extends JpaRepository:

```java

    package com.demo.tms.repository;
    
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;
    
    import com.demo.tms.model.Trustee;
    
    @Repository
    public interface TrusteeRepository extends JpaRepository<Trustee, Long>{
    
    }


```

- The `TrusteeRepository` interface extends `JpaRepository` which in turn extends `CrudRepository<T,ID>` and `PagingAndSortingRepository<T,ID>`.
- We can make use of methods such as save(entity), findById, deleteById, etc. available in the above interfaces directly in our rest controller.


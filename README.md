# Trustee Management System

A Spring Boot application deployed on Pivotal Cloud Foundry which acts as a back-end to the Angular front-end(separate app).

## Using Spring Starter to setup the application

- Navigate to [Spring Starter](https://start.spring.io/).
- Select `Gradle Project`, `Java` and version `2.0.5`.
- Add `Web`, `JPA`, `H2` and `DevTools` as dependencies and click on `Generate Project`.
- Import the project to Eclipse/STS as a Gradle project.

## Create and setup entity class

- Create Trustee.java as the entity class for our REST API.
- Add the `@Entity` annotation at the class level and provide the name property as `trustees` for the corresponding database table.
- Add `@Id` and `@GeneratedValue` annotations to the `Id` property of the Trustee class.
- Add the properties as per below code along with getters\\setters, toString\(\) and a default no-args constructor.

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


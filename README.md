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

- Create `TrusteeRepository` interface which extends JpaRepository:

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
- We can make use of methods such as save(entity), findById(id), deleteById(id), etc. available in the above interfaces directly in our rest controller.

## Create the REST controller/resource class with all the service endpoints

- Create `TrusteeResource` rest controller class with `TrusteeRepository` autowired to provide basic CRUD operations.

```java

    package com.demo.tms.resource;

    import java.net.URI;
    import java.util.List;
    import java.util.Optional;
    
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.CrossOrigin;
    import org.springframework.web.bind.annotation.DeleteMapping;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PathVariable;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.PutMapping;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.RestController;
    import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
    
    import com.demo.tms.exception.TrusteeNotFoundException;
    import com.demo.tms.model.Trustee;
    import com.demo.tms.repository.TrusteeRepository;
    
    @CrossOrigin(origins = "*")
    @RestController
    public class TrusteeResource {
    	
    	@Autowired
    	private TrusteeRepository trusteeRepository;
    	
    	@GetMapping("/trustees")
    	public List<Trustee> getAllTrustees() {
    		return trusteeRepository.findAll();
    	}
    
    	@GetMapping("/trustees/{id}")
    	public Trustee getTrusteeById(@PathVariable long id) {
    		Optional<Trustee> trustee = trusteeRepository.findById(id);
    
    		if (!trustee.isPresent()){
    			throw new TrusteeNotFoundException("id-" + id);
    		}
    		
    		return trustee.get();
    	}
    
    	@DeleteMapping("/trustees/{id}")
    	public void deleteTrusteeById(@PathVariable long id) {
    		trusteeRepository.deleteById(id);
    	}
    
    	@PostMapping("/trustees")
    	public ResponseEntity<Object> createTrustee(@RequestBody Trustee trustee) {
    		Trustee savedTrustee = trusteeRepository.save(trustee);
    
    		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
    				.buildAndExpand(savedTrustee.getId()).toUri();
    
    		return ResponseEntity.created(location).build();
    
    	}
    	
    	@PutMapping("/trustees/{id}")
    	public ResponseEntity<Object> updateTrusteeById(@RequestBody Trustee trustee, @PathVariable long id) {
    
    		Optional<Trustee> trusteeOptional = trusteeRepository.findById(id);
    
    		if (!trusteeOptional.isPresent()){
    			return ResponseEntity.notFound().build();
    		}
    
    		trustee.setId(id);
    		
    		trusteeRepository.save(trustee);
    
    		return ResponseEntity.noContent().build();
    	}
    
    }


```

- The `@CrossOrigin` annotation with the wildcard `*` is added to allow the Angular front-end app to access the endpoints exposed by the REST service.(`Note`: Avoid using the `*` wildcard and provide multiple comma-separated URLs instead, if needed)
- For exception cases such as when we search for a trustee by providing an invalid ID value `TrusteeNotFoundException` is thrown(This class extends RuntimeException).

## Pushing code to Pivotal Cloud Foundry

- Create a new file named `manifest.yml` in the project root directory.

```yml

    ---
    applications:
    - name: trustee-management-service
      instances: 1
      path: build/libs/tms-0.0.1-SNAPSHOT.jar


```

- The `name` property specifies the name of the application to be pushed to cloud foundry.
- The no. of instances are provided using the `instances` property.
- `path` has the path of the self-contained jar that is created by executing the `gradle clean build` command in the root directory.
- We can also specify properties such as `memory` to limit the memory allocated to the app in PCF.
- For more details on the manifest file refer to [this](https://docs.cloudfoundry.org/devguide/deploy-apps/manifest.html) page.
- Install Cloud Foundry CLI on the workstation using this [reference](https://docs.cloudfoundry.org/cf-cli/install-go-cli.html) page.
- We first need to login to the CLI before pushing our application code to PCF using the below command:

    > cf login -a api.run.pivotal.io

- Provide the Email and Password and select the target 'org' and 'space'(in case we have multiple orgs and spaces mapped to our pivotal web services account)
- Run the `gradle clean build` command to generate the jar file referred to in the manifest.yml file above:

    > gradle clean build

- Run `cf push` to push the application code to PCF. The CLI will use java-buildpack to build the application code:

    > cf push

- To check the name(s) of the applications deployed in PCF along with their respective routes run the below command:

    > cf apps

- We can append `/trustees` at the end of the route-url to get our REST endpoints.
- The above endpoints can then be used in the front-end Angular/React application code to perform CRUD operations.
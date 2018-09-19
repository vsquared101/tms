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

@CrossOrigin(origins = "http://localhost:4200")
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


package com.example.taller2.restcontroller.implementation;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.taller2.model.Institution;
import com.example.taller2.services.interfaces.InstitutionService;

@RestController
@RequestMapping("/institutionsRest")
public class InstitutionRest {

	private final InstitutionService instservice;

	public InstitutionRest(InstitutionService instservice) {
		this.instservice = instservice;

	}

	@GetMapping("/")
	public List<Institution> getAll() {
		return instservice.findAll();
		// ResponseEntity<List<>>();
	}

	@GetMapping("/{id}")
	public Institution findById(@PathVariable Long id) {
		return instservice.findById(id);
	}

	@PostMapping
	public Institution addInstitution(@RequestBody Institution institution) {
		// System.out.print("voy a guardar a: " + institution.getInstName());
		return instservice.saveInstitution(institution);
	}

	@PutMapping
	public Institution updateInstitution(@RequestBody Institution institution) {
		Institution inst = instservice.updateInstitution(institution);
		return inst;
	}
}
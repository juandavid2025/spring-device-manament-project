
package com.example.taller2.restcontroller.implementation;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
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
}
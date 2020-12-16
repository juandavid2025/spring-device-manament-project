package com.example.taller2.restcontroller.implementation;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.taller2.model.Nexusquestion;
import com.example.taller2.services.interfaces.NexusquestionService;

@RestController
@RequestMapping("/nexusquestionRest")
public class NexusquestionRest {

	private NexusquestionService nexusService;

	public NexusquestionRest(NexusquestionService nexusService) {
		this.nexusService = nexusService;
	}

	@GetMapping
	public List<Nexusquestion> getAll() {
		return nexusService.findAll();
	}

	@GetMapping("/{id}")
	public Nexusquestion findById(@PathVariable Long id) {
		return nexusService.findById(id);
	}

	@PostMapping
	public Nexusquestion addNexuspoll(@RequestBody Nexusquestion nexus) {
		return nexusService.save(nexus);
	}

	@PutMapping
	public Nexusquestion updateNexuspoll(@RequestBody Nexusquestion nexus) {
		return nexusService.update(nexus);
	}

	@DeleteMapping("/{id}")
	public void deleteNexuspoll(@PathVariable("id") Long id) {
		nexusService.delete(id);
	}
}

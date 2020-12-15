package com.example.taller2.restcontroller.implementation;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.taller2.model.Nexuspoll;
import com.example.taller2.services.implementations.NexuspollServiceImp;
import com.example.taller2.services.interfaces.NexuspollService;

@RestController
@RequestMapping("/nexuspollsRest")
public class NexuspollRest {

	private NexuspollService nexuspollService;

	public NexuspollRest(NexuspollServiceImp nexuspollService) {
		this.nexuspollService = nexuspollService;
	}

	@GetMapping
	public List<Nexuspoll> getAll() {
		return nexuspollService.findAll();
	}

	@GetMapping("/{id}")
	public Nexuspoll findById(@PathVariable Long id) {
		return nexuspollService.findById(id);
	}

}

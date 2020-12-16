package com.example.taller2.restcontroller.implementation;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.taller2.model.Devicetype;
import com.example.taller2.services.implementations.DevicetypeServiceImp;
import com.example.taller2.services.interfaces.DevicetypeService;

@RestController
@RequestMapping("/devicetypeRest")
public class DevicetypeRest {

	private final DevicetypeService devtypeService;

	public DevicetypeRest(DevicetypeServiceImp devtypeService) {
		this.devtypeService = devtypeService;
	}

	@GetMapping("/")
	public List<Devicetype> getAll() {
		List<Devicetype> list = devtypeService.findAll();
		return list;
	}

	@GetMapping("/{id}")
	public Devicetype findById(@PathVariable Long id) {
		return devtypeService.findById(id);
	}

	@PostMapping
	public Devicetype addDevicetype(@RequestBody Devicetype devicetype) {
		return devtypeService.saveDevicetype(devicetype);
	}

	@PutMapping
	public Devicetype updateDevicetype(@RequestBody Devicetype devicetype) {
		return devtypeService.updateDevicetype(devicetype);
	}
}

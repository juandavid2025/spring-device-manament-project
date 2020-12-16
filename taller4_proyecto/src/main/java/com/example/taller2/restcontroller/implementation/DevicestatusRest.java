package com.example.taller2.restcontroller.implementation;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.taller2.model.Devicestatus;
import com.example.taller2.services.implementations.DevicestatusServiceImp;
import com.example.taller2.services.interfaces.DevicestatusService;

@RestController
@RequestMapping("/devicestatusRest")
public class DevicestatusRest {

	private final DevicestatusService devStatService;

	public DevicestatusRest(DevicestatusServiceImp devStatService) {
		this.devStatService = devStatService;
	}

	@GetMapping("/")
	public List<Devicestatus> getAll() {
		List<Devicestatus> list = devStatService.findAll();
		return list;
	}

	@GetMapping("/{id}")
	public Devicestatus findById(@PathVariable Long id) {
		return devStatService.findById(id);
	}

	@PostMapping
	public Devicestatus addDevicestatus(@RequestBody Devicestatus devicestatus) {
		return devStatService.saveDevicestatus(devicestatus);
	}

	@PutMapping
	public Devicestatus updateDevicestatus(@RequestBody Devicestatus devicestatus) {
		return devStatService.updateDevicestatus(devicestatus);
	}

	@GetMapping("/find-by-name/{instName}")
	public Devicestatus searchByInstName(@PathVariable String instName) {
		return devStatService.searchByInstName(instName);
	}

	@GetMapping("/find-by-permiId/{mac}")
	public Devicestatus searchByPermiId(@PathVariable Long mac) {
		return devStatService.searchByPermiId(mac);
	}
}

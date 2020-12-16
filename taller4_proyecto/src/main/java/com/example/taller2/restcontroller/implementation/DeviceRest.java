package com.example.taller2.restcontroller.implementation;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.taller2.model.Device;
import com.example.taller2.services.interfaces.DeviceService;

@RestController
@RequestMapping("/devicesRest")
public class DeviceRest {

	private final DeviceService devService;

	public DeviceRest(DeviceService devService) {
		this.devService = devService;
	}

	@GetMapping("/")
	public List<Device> getAll() {
		List<Device> list = devService.findAll();
		//system.out.print("ID====" + list.get(0).getInstitution().getInstId());
		return list;
	}

	@GetMapping("/{id}")
	public Device findById(@PathVariable Long id) {
		return devService.findById(id);
	}

	@PostMapping
	public Device addDevicetype(@RequestBody Device device) {
		return devService.saveDevice(device);
	}

	@PutMapping
	public Device updateDevicetype(@RequestBody Device device) {
		return devService.updateDevice(device);
	}
}

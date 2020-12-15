package com.example.taller2.controller.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.taller2.grouping.interfaces.DeviceGroup;
import com.example.taller2.model.Device;
import com.example.taller2.services.implementations.DeviceServiceImp;
import com.example.taller2.services.implementations.DevicestatusServiceImp;
import com.example.taller2.services.implementations.DevicetypeServiceImp;
import com.example.taller2.services.interfaces.DeviceService;
import com.example.taller2.services.interfaces.DevicestatusService;
import com.example.taller2.services.interfaces.DevicetypeService;

@Controller
public class DeviceControllerImp {

	private DeviceService devService;
	private DevicetypeService devtypeService;
	private DevicestatusService devstatusService;

	@Autowired
	public DeviceControllerImp(DeviceServiceImp devService, DevicetypeServiceImp devtypeService,
			DevicestatusServiceImp devstatusService) {
		this.devService = devService;
		this.devstatusService = devstatusService;
		this.devtypeService = devtypeService;
	}

	@GetMapping("/devices")
	public String indexDevices(Model model) {
		model.addAttribute("devices", devService.findAll());
		return "devices/index";
	}

	@GetMapping("devices/add")
	public String addDevice(Model model) {

		model.addAttribute("device", new Device());
		model.addAttribute("devicetypes", devtypeService.findAll());
		model.addAttribute("devicestatuses", devstatusService.findAll());
		return "devices/add-device";
	}

	@GetMapping("/devices/edit/{id}")
	public String showUpdate(@PathVariable("id") long id, Model model) {
		final Device device = devService.findById(id);

		if (device == null) {
			throw new RuntimeException();
		}

		model.addAttribute("device", device);
		model.addAttribute("devicestatuses", devstatusService.findAll());
		model.addAttribute("devicetypes", devtypeService.findAll());

		return "devices/update-device";
	}

	@GetMapping("/devices/info/{id}")
	public String showInfo(@PathVariable("id") long id, Model model) {
		final Device device = devService.findById(id);

		if (device == null) {
			throw new RuntimeException();
		}

		model.addAttribute("device", device);

		return "devices/info-device";
	}

	// ---------------------Post------------------------

	@PostMapping("devices/add")
	public String saveDevice(@RequestParam(value = "action", required = true) String action,
			@Validated(DeviceGroup.class) @ModelAttribute Device device, BindingResult bindingResult, Model model) {

		if (!action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				return "devices/add-device";
			}
			devService.saveDevice(device);
		}
		return "redirect:/devices";
	}

	@PostMapping("/devices/edit/{id}")
	public String updateInstitution(@Validated(DeviceGroup.class) @ModelAttribute Device device,
			BindingResult bindingResult, @PathVariable("id") long id,
			@RequestParam(value = "action", required = true) String action, Model model) {
		if (action != null && !action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("device", device);
				model.addAttribute("devicestatuses", devstatusService.findAll());
				model.addAttribute("devicetypes", devtypeService.findAll());
				return "devices/update-device";
			}
			device.setDevId(id);
			devService.saveDevice(device);
		}
		return "redirect:/devices";
	}
}

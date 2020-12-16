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

import com.example.taller2.delegate.implementation.DevicestatusDelegateImp;
import com.example.taller2.delegate.interfaces.DevicestatusDelegate;
import com.example.taller2.grouping.interfaces.DevicestatusGroup;
import com.example.taller2.model.Devicestatus;
import com.example.taller2.services.interfaces.DevicestatusService;
import com.example.taller2.services.interfaces.InstitutionService;
import com.example.taller2.services.interfaces.PermissionnService;

@Controller
public class DevicestatusControllerImp {

	// private DevicestatusService devStatusService;
	private DevicestatusDelegate devStatusDele;
	private InstitutionService instService;
	private PermissionnService permService;

	@Autowired
	public DevicestatusControllerImp(DevicestatusService devStatusService, InstitutionService instService,
			PermissionnService permService, DevicestatusDelegateImp devStatusDele) {
		// this.devStatusService = devStatusService;
		this.instService = instService;
		this.permService = permService;
		this.devStatusDele = devStatusDele;
	}

	@GetMapping("/devicestatuses")
	public String indexDevicestatus(Model model) {
		model.addAttribute("devicestatuses", devStatusDele.findAll());
		return "devicestatuses/index";
	}

	@GetMapping("devicestatuses/add")
	public String addDevicestatus(Model model) {
		model.addAttribute("devicestatus", new Devicestatus());
		model.addAttribute("institutions", instService.findAll());
		model.addAttribute("permissions", permService.findAll());
		return "devicestatuses/add-devicestatus";
	}

	@GetMapping("/devicestatuses/edit/{id}")
	public String showUpdate(@PathVariable("id") long id, Model model) {
		final Devicestatus devstatus = devStatusDele.findById(id);

		if (devstatus == null) {
			throw new RuntimeException();
		}

		model.addAttribute("devicestatus", devstatus);
		model.addAttribute("institutions", instService.findAll());
		model.addAttribute("permissionns", permService.findAll());

		return "devicestatuses/update-devicestatus";
	}

	@GetMapping("/devicestatuses/info/{id}")
	public String showInfo(@PathVariable("id") long id, Model model) {
		final Devicestatus devicestatus = devStatusDele.findById(id);

		if (devicestatus == null) {
			throw new RuntimeException();
		}

		model.addAttribute("devicestatus", devicestatus);

		return "devicestatuses/info-devicestatus";
	}

	// --------------------Post----------------------

	@PostMapping("devicestatuses/add")
	public String saveDevicestatus(@RequestParam(value = "action", required = true) String action,
			@Validated(DevicestatusGroup.class) @ModelAttribute Devicestatus devicestatus, BindingResult bindingResult,
			Model model) {

		if (!action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				return "devicestatuses/add-devicestatus";
			}
			devStatusDele.saveDevicestatus(devicestatus);
		}
		return "redirect:/devicestatuses";
	}

	@PostMapping("/devicestatuses/edit/{id}")
	public String updateInstitution(@Validated(DevicestatusGroup.class) @ModelAttribute Devicestatus devicestatus,
			BindingResult bindingResult, @PathVariable("id") long id,
			@RequestParam(value = "action", required = true) String action, Model model) {
		if (action != null && !action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("devicestatus", devicestatus);
				model.addAttribute("institutions", instService.findAll());
				model.addAttribute("permissionns", permService.findAll());
				return "devicestatuses/update-devicestatus";
			}
			devicestatus.setDevstatId(id);
			devStatusDele.updateDevicestatus(devicestatus);
		}
		return "redirect:/devicestatuses";
	}
}

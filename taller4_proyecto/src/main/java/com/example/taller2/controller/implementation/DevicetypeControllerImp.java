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

import com.example.taller2.delegate.implementation.DevicetypeDelegateImp;
import com.example.taller2.delegate.implementation.InstitutionDelegateImp;
import com.example.taller2.delegate.interfaces.DevicetypeDelegate;
import com.example.taller2.delegate.interfaces.InstitutionDelegate;
import com.example.taller2.grouping.interfaces.DevicetypeGroup;
import com.example.taller2.model.Devicetype;

@Controller
public class DevicetypeControllerImp {

	private DevicetypeDelegate devtypeDelegate;
	private InstitutionDelegate instDelegate;

	@Autowired
	public DevicetypeControllerImp(DevicetypeDelegateImp devtypeDelegate, InstitutionDelegateImp instDelegate) {
		this.devtypeDelegate = devtypeDelegate;
		this.instDelegate = instDelegate;
	}

	@GetMapping("/devicetypes")
	public String indexDevicetypes(Model model) {
		model.addAttribute("devicestatuses", devtypeDelegate.findAll());
		return "devicetypes/index";
	}

	@GetMapping("devicetypes/add")
	public String addDevicetype(Model model) {
		model.addAttribute("devicetype", new Devicetype());
		model.addAttribute("institutions", instDelegate.findAll());
		return "devicetypes/add-devicetype";
	}

	@GetMapping("/devicetypes/edit/{id}")
	public String showUpdate(@PathVariable("id") long id, Model model) {
		final Devicetype devtype = devtypeDelegate.findById(id);

		if (devtype == null) {
			throw new RuntimeException();
		}

		model.addAttribute("devicetype", devtype);
		model.addAttribute("institutions", instDelegate.findAll());

		return "devicetypes/update-devicetype";
	}

	@GetMapping("/devicetypes/info/{id}")
	public String showInfo(@PathVariable("id") long id, Model model) {
		final Devicetype devicetype = devtypeDelegate.findById(id);

		if (devicetype == null) {
			throw new RuntimeException();
		}

		model.addAttribute("devicetype", devicetype);
		// model.addAttribute("institution", devicetype.getInstitution());

		return "devicetypes/info-devicetype";
	}

	// ----------------------Post------------------------

	@PostMapping("devicetypes/add")
	public String saveDevicetype(@RequestParam(value = "action", required = true) String action,
			@Validated(DevicetypeGroup.class) @ModelAttribute Devicetype devicetype, BindingResult bindingResult,
			Model model) {

		if (!action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				return "devicetypes/add-devicetype";
			}
			devtypeDelegate.save(devicetype);
		}
		return "redirect:/devicetypes";
	}

	@PostMapping("/devicetypes/edit/{id}")
	public String updateInstitution(@Validated(DevicetypeGroup.class) @ModelAttribute Devicetype devicetype,
			BindingResult bindingResult, @PathVariable("id") long id,
			@RequestParam(value = "action", required = true) String action, Model model) {
		if (action != null && !action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("devicetype", devicetype);
				model.addAttribute("institutions", instDelegate.findAll());
				return "devicetypes/update-devicetype";
			}
			devicetype.setDevtypeId(id);
			devtypeDelegate.save(devicetype);
		}
		return "redirect:/devicetypes";
	}
}

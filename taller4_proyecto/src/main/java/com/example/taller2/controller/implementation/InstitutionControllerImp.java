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

import com.example.taller2.controller.interfaces.InstitutionController;
import com.example.taller2.delegate.implementation.InstitutionDelegateImp;
import com.example.taller2.delegate.interfaces.InstitutionDelegate;
import com.example.taller2.grouping.interfaces.InstitutionGroup;
import com.example.taller2.model.Institution;
import com.example.taller2.services.implementations.InstitutionServiceImp;

@Controller
public class InstitutionControllerImp implements InstitutionController {

	// private InstitutionService instService;
	private InstitutionDelegate instDele;

	@Autowired
	public InstitutionControllerImp(InstitutionServiceImp instService, InstitutionDelegateImp instDele) {
		// this.instService = instService;
		this.instDele = instDele;
	}

	@GetMapping("/institutions/add")
	public String addInstitution(Model model) {
		model.addAttribute("institution", new Institution());
		return "institutions/add-institution";
	}

	@GetMapping("/institutions")
	public String indexInstitution(Model model) {
		// model.addAttribute("institutions", instService.findAll());
		model.addAttribute("institutions", instDele.findAll());
		return "institutions/index";
	}

	// WTF
	@PostMapping("/institutions/info/{id}")
	public String redirectUpdate() {
		return "redirect:/institutions";
	}

	@PostMapping("/institutions/add")
	public String saveInstitution(@RequestParam(value = "action", required = true) String action,
			@Validated(InstitutionGroup.class) @ModelAttribute Institution institution, BindingResult bindingResult,
			Model model) {
		if (!action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				System.out.print("\n hubo un error");
				return "institutions/add-institution";
			}
			// instService.saveInstitution(institution);
			instDele.save(institution);
		}

		return "redirect:/institutions";
	}

	@GetMapping("/institutions/info/{id}")
	public String showInfo(@PathVariable("id") long id, Model model) {
		// final Institution institution = instService.findById(id);
		final Institution institution = instDele.findById(id);

		if (institution == null) {
			throw new RuntimeException();
		}

		model.addAttribute("institution", institution);

		return "institutions/info-institution";
	}

	@GetMapping("/institutions/edit/{id}")
	public String showUpdate(@PathVariable("id") long id, Model model) {
		// final Institution institution = instService.findById(id);
		final Institution institution = instDele.findById(id);

		if (institution == null) {
			throw new RuntimeException();
		}

		model.addAttribute("institution", institution);

		return "institutions/update-institution";
	}

	@PostMapping("/institutions/edit/{id}")
	public String updateInstitution(@Validated(InstitutionGroup.class) @ModelAttribute Institution institution,
			BindingResult bindingResult, @PathVariable("id") long id,
			@RequestParam(value = "action", required = true) String action, Model model) {
		if (action != null && !action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("institution", institution);
				return "institutions/update-institution";
			}
			institution.setInstId(id);
			// instService.saveInstitution(institution);
			instDele.update(institution);
		}
		return "redirect:/institutions";
	}
}

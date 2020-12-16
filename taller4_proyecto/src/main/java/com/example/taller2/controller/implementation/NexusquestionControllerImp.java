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

import com.example.taller2.delegate.interfaces.NexuspollDelegate;
import com.example.taller2.delegate.interfaces.NexusquestionDelegate;
import com.example.taller2.model.Nexusquestion;

@Controller
public class NexusquestionControllerImp {

	private NexusquestionDelegate nexusDelegate;
	private NexuspollDelegate pollDelegate;

	@Autowired
	public NexusquestionControllerImp(NexusquestionDelegate nexusDelegate,NexuspollDelegate pollDelegate) {
		this.nexusDelegate = nexusDelegate;
		this.pollDelegate= pollDelegate;
	}

	@GetMapping("/nexusquestions/add")
	public String addNexuspoll(Model model) {
		model.addAttribute("nexusquestion", new Nexusquestion());
		model.addAttribute("nexuspolls", pollDelegate.findAll());
		return "nexusquestions/add-nexusquestion" ;
	}

	@GetMapping("/nexusquestions")
	public String indexListNexuspoll(Model model) {
		model.addAttribute("nexusquestions", nexusDelegate.findAll());
		return "nexusquestions/index";
	}

	@GetMapping("/nexusquestions/info/{id}")
	public String showInformation(@PathVariable("id") long id, Model model) {
		final Nexusquestion nexusquestion = nexusDelegate.findById(id);

		if (nexusquestion == null) {
			throw new RuntimeException();
		}

		model.addAttribute("nexusquestion", nexusquestion);

		return "/nexusquestions/info-nexusquestion";
	}

	@GetMapping("/nexusquestions/edit/{id}")
	public String showUpdate(@PathVariable("id") long id, Model model) {
		final Nexusquestion nexusquestion = nexusDelegate.findById(id);

		if (nexusquestion == null) {
			throw new RuntimeException();
		}

		model.addAttribute("nexusquestion", nexusquestion);
		model.addAttribute("nexuspolls", pollDelegate.findAll());
		return "/nexusquestions/update-nexusquestion";
	}

	@GetMapping("/nexusquestions/delete/{id}")
	public String deleteNexuspoll(@PathVariable("id") long id, Model model) {
		final Nexusquestion nexusquestion = nexusDelegate.findById(id);

		if (nexusquestion == null) {
			throw new RuntimeException();
		}

		nexusDelegate.delete(id);

		return "redirect:/nexusquestions";
	}

	// ----post----

	@PostMapping("/nexusquestions/add")
	public String saveNexuspoll(@RequestParam(value = "action", required = true) String action,
			@Validated @ModelAttribute Nexusquestion nexusquestion, BindingResult bindingResult, Model model) {

		if (!action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				System.out.println("\nerror"+bindingResult.getAllErrors().toString());
				return "nexusquestions/add-nexusquestion";
			}
			nexusDelegate.save(nexusquestion);
		}

		return "redirect:/nexusquestions";
	}

	@PostMapping("/nexusquestions/edit/{id}")
	public String updateNexuspoll(@Validated @ModelAttribute Nexusquestion nexusquestion, BindingResult bindingResult,
			@PathVariable("id") long id, @RequestParam(value = "action", required = true) String action, Model model) {

		if (action != null && !action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("nexusquestion", nexusquestion);
				model.addAttribute("nexuspolls", pollDelegate.findAll());
				return "nexusquestions/update-nexusquestion";
			}
			nexusquestion.setNexquesId(id);
			nexusDelegate.update(nexusquestion);
		}
		return "redirect:/nexusquestions";

	}
}

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

import com.example.taller2.delegate.implementation.NexuspollDelegateImp;
import com.example.taller2.delegate.interfaces.NexuspollDelegate;
import com.example.taller2.model.Nexuspoll;

@Controller
public class NexuspollControllerImp {

	private NexuspollDelegate nexusDelegate;

	@Autowired
	public NexuspollControllerImp(NexuspollDelegateImp nexusDelegate) {
		this.nexusDelegate = nexusDelegate;
	}

	@GetMapping("/nexuspolls/add")
	public String addNexuspoll(Model model) {
		// System.out.print("quiero add");
		model.addAttribute("nexuspoll", new Nexuspoll());
		return "nexuspolls/add-nexuspoll";
	}

	@GetMapping("/nexuspolls")
	public String indexListNexuspoll(Model model) {
		model.addAttribute("nexuspolls", nexusDelegate.findAll());
		return "nexuspolls/index";
	}

	@GetMapping("/nexuspolls/info/{id}")
	public String showInformation(@PathVariable("id") long id, Model model) {
		final Nexuspoll nexuspoll = nexusDelegate.findById(id);

		if (nexuspoll == null) {
			throw new RuntimeException();
		}

		model.addAttribute("nexuspoll", nexuspoll);

		return "/nexuspolls/info-nexuspoll";
	}

	@GetMapping("/nexuspolls/edit/{id}")
	public String showUpdate(@PathVariable("id") long id, Model model) {
		final Nexuspoll nexuspoll = nexusDelegate.findById(id);

		if (nexuspoll == null) {
			throw new RuntimeException();
		}

		model.addAttribute("nexuspoll", nexuspoll);

		return "/nexuspolls/update-nexuspoll";
	}

	@GetMapping("/nexuspolls/delete/{id}")
	public String deleteNexuspoll(@PathVariable("id") long id, Model model) {
		final Nexuspoll nexuspoll = nexusDelegate.findById(id);

		if (nexuspoll == null) {
			throw new RuntimeException();
		}

		nexusDelegate.delete(id);

		return "redirect:/nexuspolls";
	}

	// ----post----

	@PostMapping("/nexuspolls/add")
	public String saveNexuspoll(@RequestParam(value = "action", required = true) String action,
			@Validated @ModelAttribute Nexuspoll nexuspoll, BindingResult bindingResult, Model model) {

		if (!action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				return "nexuspolls/add-nexuspoll";
			}
			nexusDelegate.save(nexuspoll);
		}

		return "redirect:/nexuspolls";
	}

	@PostMapping("/nexuspolls/edit/{id}")
	public String updateNexuspoll(@Validated @ModelAttribute Nexuspoll nexuspoll, BindingResult bindingResult,
			@PathVariable("id") long id, @RequestParam(value = "action", required = true) String action, Model model) {

		if (action != null && !action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("nexuspoll", nexuspoll);
				return "nexuspolls/update-nexuspoll";
			}
			nexuspoll.setNexpollId(id);
			nexusDelegate.update(nexuspoll);
		}
		return "redirect:/nexuspolls";

	}
}

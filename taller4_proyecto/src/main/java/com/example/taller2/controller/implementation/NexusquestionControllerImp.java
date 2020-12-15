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
import com.example.taller2.model.Nexusquestion;
import com.example.taller2.services.interfaces.NexusquestionService;

@Controller
public class NexusquestionControllerImp {

	private NexusquestionService questionService;
	//private NexuspollService pollService;
	
	@Autowired
	public NexusquestionControllerImp(NexusquestionService questionService) {
		this.questionService= questionService;
		//this.pollService=pollService;
	}
	
	@GetMapping("/nexusquestions")
	public String indexQuestions(Model model) {
		model.addAttribute("nexusquestion", questionService.findAll());
		return "nexusquestions/index";
	}
	
	@GetMapping("nexusquestions/add")
	public String addQuestion(Model model) {

		model.addAttribute("nexusquestion", new Nexusquestion());
		//model.addAttribute("nexuspoll", pollService.findAll());
		return "nexusquestions/add-nexusquestion";
	}
	
	@GetMapping("/nexusquestions/edit/{id}")
	public String showUpdate(@PathVariable("id") long id, Model model) {
		final Nexusquestion nexusquestion = questionService.findById(id);

		if (nexusquestion == null) {
			throw new RuntimeException();
		}

		model.addAttribute("nexusquestion", nexusquestion);
		//model.addAttribute("nexuspoll", pollService.findAll());

		return "nexusquestions/update-nexusquestion";
	}
	
	@GetMapping("/nexusquestions/info/{id}")
	public String showInfo(@PathVariable("id") long id, Model model) {
		final Nexusquestion nexusquestion = questionService.findById(id);

		if (nexusquestion == null) {
			throw new RuntimeException();
		}

		model.addAttribute("nexusquestion", nexusquestion);

		return "nexusquestions/info-nexusquestion";
	}
	
	@PostMapping("nexusquestions/add")
	public String saveDevice(@RequestParam(value = "action", required = true) String action,
			@Validated @ModelAttribute Nexusquestion nexusquestion, BindingResult bindingResult, Model model) {

		if (!action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				return "nexusquestions/add-nexusquestion";
			}
			questionService.save(nexusquestion);
		}
		return "redirect:/devices";
	}
	
	@PostMapping("/nexusquestions/edit/{id}")
	public String updateInstitution(@Validated @ModelAttribute Nexusquestion nexusquestion,
			BindingResult bindingResult, @PathVariable("id") long id,
			@RequestParam(value = "action", required = true) String action, Model model) {
		if (action != null && !action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("nexusquestion", nexusquestion);
				//model.addAttribute("nexuspoll", pollService.findAll());

				return "nexusquestions/update-nexusquestion";
			}
			nexusquestion.setNexquesId(id);
			questionService.save(nexusquestion);
		}
		return "redirect:/nexusquestions";
	}
	
	
}

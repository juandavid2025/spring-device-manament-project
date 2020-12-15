package com.example.taller2.controller.implementation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.taller2.controller.interfaces.LoginController;
import com.example.taller2.model.Userr;

@Controller
public class LoginControllerImp implements LoginController {

	@RequestMapping("/login")
	public String login(Model model) {

		model.addAttribute("userr", new Userr());
		return "login";
	}

	@RequestMapping("/")
	public String loadIndex() {
		return "/index";
	}
}

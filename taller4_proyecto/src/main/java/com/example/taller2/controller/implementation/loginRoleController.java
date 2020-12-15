package com.example.taller2.controller.implementation;

import javax.annotation.security.RolesAllowed;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class loginRoleController {

	@RequestMapping("/admin")
	@RolesAllowed("ADMIN")
	public String getAdmin() {
		return "Welcome, admin";
	}

	@RequestMapping("/opera")
	@RolesAllowed("OPERA")
	public String getOpera() {
		System.out.print("llegue a opera");
		return "welcome, opera";
	}

}

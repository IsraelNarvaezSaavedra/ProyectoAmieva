package com.proyecto.amieva.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
	
	@GetMapping("/login")
	public String showLogin() {
		return "auth/login";
	}
	
	@GetMapping("/logout")
	public String showLogout() {
		return "auth/logout";
	}
	
	@GetMapping({"/", "/index","home"})
	public String showIndex() {
		return "auth/index";
	}
	
	@GetMapping("/error")
	public String showError() {
		return "auth/error";
	}
}

package com.tuhuella.main.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AppController {
	
	@GetMapping("")
	public String index() {
		return "index";
	}
	/*@GetMapping("/login")
	public String login() {
		return "login";
	}*/
	
	@GetMapping("/menu")
	public String menu() {
		return "menu";
	}
	
	/*@GetMapping("/user")
	public String user() {
		return "user";
	}*/
	
	@GetMapping("/404")
	public String admin() {
		return "404";
	}
}


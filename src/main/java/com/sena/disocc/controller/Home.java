package com.sena.disocc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("Dashboard/admin")
public class Home {
	
	
	@GetMapping("")
	public String homdos() {
		return "inicioAdmin";	
	}
	

}

package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class UhomeController {
	@RequestMapping(path = "/uhome", method = RequestMethod.GET)
	public String viewPage() {

		return "uhome";
	}
}

package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UsiController {
	@RequestMapping(path = "/usi", method = RequestMethod.GET)
	public String viewPage() {

		return "usi";
	}
}

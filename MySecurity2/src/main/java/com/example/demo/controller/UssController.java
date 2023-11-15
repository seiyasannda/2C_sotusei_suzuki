package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class UssController {
	
	
		@RequestMapping(path = "/uss", method = RequestMethod.GET)
		public String viewPage() {

			return "uss";
		}
	
}

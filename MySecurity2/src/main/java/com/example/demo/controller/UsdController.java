package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class UsdController {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@RequestMapping(path = "/usd", method = RequestMethod.GET)
	public String viewPage(Model model) {

		List<Map<String, Object>> items = jdbcTemplate.queryForList("SELECT * FROM vitems");

        // Pass the data to the HTML file
        model.addAttribute("items", items);

		
		return "usd";
	}
}

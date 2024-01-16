package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class UsdController {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@RequestMapping(path = "/usd/{stores}", method = RequestMethod.GET)
	public String viewPage(@PathVariable String stores,Model model) {

		List<Map<String, Object>> items = jdbcTemplate.queryForList("SELECT * FROM vitems");
		List<Map<String, Object>> store = jdbcTemplate.queryForList("SELECT * FROM vstoretable WHERE store_id = ?",stores);

		
        // Pass the data to the HTML file
        model.addAttribute("items", items);
        model.addAttribute("store", store);
		
		return "usd";
	}
}
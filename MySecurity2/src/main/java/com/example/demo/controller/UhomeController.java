package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class UhomeController {
	@Autowired
	JdbcTemplate jdbcTemplate;
	@GetMapping("/geocode")
	
	   @RequestMapping(path = "/uhome", method = RequestMethod.GET)
	    public String viewPage(Model model) {
	        // Fetch data from the database, assuming you have a table named 'items'
	        List<Map<String, Object>> items = jdbcTemplate.queryForList("SELECT * FROM vitems");
	        
	        List<Map<String, Object>> store = jdbcTemplate.queryForList("SELECT * FROM vstoretable");
	        
	        List<String> addresses = jdbcTemplate.queryForList(
	    		    "SELECT CONCAT(prefecture, ' - ', city, ' - ', address1, ' - ', address2) " +
	    		    "FROM vstoretable", String.class);

	        // Pass the data to the HTML file
	        model.addAttribute("items", items);
	        model.addAttribute("store", store);
	        model.addAttribute("addresses", addresses);

	        return "uhome";
	    }

}

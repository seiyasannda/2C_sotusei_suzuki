package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("id")
public class VhomeController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(path = "/vhome", method = RequestMethod.GET)
	 public String vhome(@ModelAttribute("id") String id, Model model) {
        // idを使って何かしらの処理
        model.addAttribute("id", id);
        return "vhome";
	}

	
}

package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("id")
public class uslistController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	   @RequestMapping(path = "/uslist", method = RequestMethod.GET)
	    public String viewPage( Model model) {
	        // Fetch data from the database, assuming you have a table named 'items'
	        List<Map<String, Object>> store = jdbcTemplate.queryForList("SELECT * FROM vstoretable");

//	        byte[] imageData = jdbcTemplate.queryForObject("SELECT imagePath FROM vitems WHERE id = ?", byte[].class, id);
//	        String encodedImage = Base64.getEncoder().encodeToString(imageData);
//
//	     // モデルにエンコードされた画像データを追加
//	        model.addAttribute("encodedImage", encodedImage);
	        // Pass the data to the HTML file
	        model.addAttribute("store", store);

	        return "uslist";
	    }

	//一覧表示用
	
	
}
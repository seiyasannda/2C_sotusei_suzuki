package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("id")
public class VitemListController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(path = "/vitemList", method = RequestMethod.GET)
    public String viewPage(@SessionAttribute("id") Long id, Model model) {
        // Fetch data from the database, assuming you have a table named 'items' and filtering by store_id
        List<Map<String, Object>> items = jdbcTemplate.queryForList("SELECT * FROM vitems WHERE store_id = ?", id);

        // List to store encoded image data for each item
        List<String> encodedImages = new ArrayList<>();

        // Fetch and encode image data for each item
        for (Map<String, Object> item : items) {
            byte[] imageData = jdbcTemplate.queryForObject("SELECT imagePath FROM vitems WHERE id = ?", byte[].class, item.get("id"));
            String encodedImage = Base64.getEncoder().encodeToString(imageData);
            encodedImages.add(encodedImage);
        }

        // Pass the data to the HTML file
        model.addAttribute("items", items);
        model.addAttribute("encodedImages", encodedImages);

        return "vitemList";
    }
}

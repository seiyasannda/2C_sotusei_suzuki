package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class VitemEditController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(path = "/viewItem/{itemId}", method = RequestMethod.GET)
    public String viewItem(@PathVariable Long itemId, Model model) {
        // Fetch the details of the specific item
        Map<String, Object> item = jdbcTemplate.queryForMap("SELECT * FROM vitems WHERE id = ?", itemId);

        // Pass the item details to the HTML file
        model.addAttribute("item", item);

        return "viewItem";
    }
}


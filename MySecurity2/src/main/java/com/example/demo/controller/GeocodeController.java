package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GeocodeController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/geocode")
    public String showMap(Model model) {
        // SQLクエリの実行
        List<String> results = jdbcTemplate.queryForList(
                "SELECT CONCAT(prefecture, ' - ', city, ' - ', address1, ' - ', address2) " +
                        "FROM vstoretable",
                String.class);

        // モデルにデータを追加
        model.addAttribute("storeDataList", results);

        return "geocode";
    }
}

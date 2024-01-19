package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UsdController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/usd/{stores}")
    public String viewPage(@PathVariable String stores, Model model) {
        // 商品情報の取得
        List<Map<String, Object>> items = jdbcTemplate.queryForList("SELECT * FROM vitems");

        // 店舗情報の取得
        List<Map<String, Object>> store = jdbcTemplate.queryForList("SELECT * FROM vstoretable WHERE Sname = ?", stores);

       
        // モデルにデータを追加
        model.addAttribute("items", items);
        model.addAttribute("store", store);
        

        // usd.htmlを表示
        return "test2";
    }
}
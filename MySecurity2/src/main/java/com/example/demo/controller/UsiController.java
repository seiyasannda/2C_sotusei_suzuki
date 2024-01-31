package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UsiController {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@RequestMapping(path = "/usi", method = RequestMethod.GET)
	public String viewPage() {

		return "usi";
	}
	@RequestMapping(path = "/usi", method = RequestMethod.POST)
	public String searchProducts(Model model, String productName, String discountPriceStr, String genre, String expirationDate) {
	    List<Map<String, Object>> items;
	    String query = "SELECT * FROM vitems";
	    List<Object> params = new ArrayList<>();

	    if (productName != null && !productName.isEmpty()) {
	        query += " WHERE productName LIKE ?";
	        params.add("%" + productName + "%");
	    }
	    if (discountPriceStr != null && !discountPriceStr.isEmpty()) {
	        try {
	            double discountPrice = Double.parseDouble(discountPriceStr);
	            if (query.contains("WHERE")) {
	                query += " AND nebiki <= ?"; // 値段が検索した値未満のものを取得する条件
	            } else {
	                query += " WHERE nebiki <= ?";
	            }
	            params.add(discountPrice);
	        } catch (NumberFormatException e) {
	            // 例外処理
	        }
	    }
	    if (genre != null && !genre.isEmpty()) {
	        if (query.contains("WHERE")) {
	            query += " AND genre = ?";
	        } else {
	            query += " WHERE genre = ?";
	        }
	        params.add(genre);
	    }
	    if (expirationDate != null && !expirationDate.isEmpty()) {
	        if (query.contains("WHERE")) {
	            query += " AND expirationDate <= ?"; // expirationDateが入力した日より前のものを取得する条件
	        } else {
	            query += " WHERE expirationDate <= ?";
	        }
	        params.add(expirationDate);
	    }

	    items = jdbcTemplate.queryForList(query, params.toArray());
	    List<Map<String, Object>> store = jdbcTemplate.queryForList("SELECT * FROM vstoretable");

	    model.addAttribute("items", items);
	    model.addAttribute("store", store);
	    return "usir";
	}
}


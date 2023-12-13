package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("id")
public class VhomeController {

	@Autowired
	JdbcTemplate jdbcTemplate;
	 @RequestMapping(path = "/vhome")
	 public String vhome(@SessionAttribute("id") String id, Model model) {
	        // idを使って何かしらの処理
	        model.addAttribute("id", id);
	        return "vhome";
	    }

	    @PostMapping("/vlogout")
	    public String registerVitem(@SessionAttribute("id") String id, Model model) {
	        // ファイルアップロードや他の処理が必要な場合はここで実装する
	        model.asMap().remove("id");
	        // データベースへの挿入処理

	        return "redirect:/vlogin";
	    }
	}

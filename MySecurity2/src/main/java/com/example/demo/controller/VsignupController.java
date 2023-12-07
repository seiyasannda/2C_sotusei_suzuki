package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class VsignupController {
	
	@Autowired
    JdbcTemplate jdbcTemplate;
	
	@RequestMapping(path = "/vsignup", method = RequestMethod.GET)
	public String viewPage1() {

		return "vsignup";
	}
	
	// 画面からの入力処理用メソッド
    @RequestMapping(path = "/vsignup", method = RequestMethod.POST)
    public String registerUser(String loginId, String password, Model model) {
        String sql = "INSERT INTO login (loginid, password) VALUES (?, ?)";
        
        jdbcTemplate.update(sql, loginId, password);

        // 登録が完了したら、micuser.html ページにリダイレクト
        return "redirect:/vsignup";
    }
}

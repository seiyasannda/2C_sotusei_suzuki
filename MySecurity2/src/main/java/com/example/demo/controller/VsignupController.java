package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class VsignupController {
	
	@Autowired
    JdbcTemplate jdbcTemplate;
	
	@RequestMapping(path = "/vsignup", method = RequestMethod.GET)
	public String viewPage1() {

		return "vsignup";
	}
	@RequestMapping(path = "/vsignup", method = RequestMethod.POST)
    public String micPost(String loginid, String password, Model model) {
       
		
		List<Map<String, Object>> resultList = jdbcTemplate
                .queryForList("SELECT * FROM miclogin WHERE loginid = ? AND password = ?", loginid, password);
        if (!resultList.isEmpty()) {
        	
        	model.addAttribute("loginerror","既に登録されています");
        	
        } else {
        	String sql = "INSERT INTO miclogin (loginid,password ) " +
                    "VALUES (?, ?)";
    		jdbcTemplate.update(sql, loginid, password);
    		model.addAttribute("message", "登録が完了しました。");
       
    		return"vhome" ;
        }	
		
        return "vsignup";
	
    
	}
}

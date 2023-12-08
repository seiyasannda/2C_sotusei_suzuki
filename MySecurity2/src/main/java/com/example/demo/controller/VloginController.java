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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes("id")
public class VloginController {
	@Autowired
    JdbcTemplate jdbcTemplate;

	//一覧表示用
	@RequestMapping(path = "/vlogin", method = RequestMethod.GET)
	public String viewPage() {

		return "vlogin";
	}
	
	

	//ログイン検証用
	@RequestMapping(path = "/vlogin", method = RequestMethod.POST)
    public String micPost(String loginid, String password, Model model,RedirectAttributes redirectAttributes) {
        if (loginid.length() > 16 || password.length() > 16) {
            model.addAttribute("mozierror","文字数が多すぎます");
        } else {
            List<Map<String, Object>> resultList = jdbcTemplate
                    .queryForList("SELECT * FROM miclogin WHERE loginid = ? AND password = ?", loginid, password);
            if (!resultList.isEmpty()) {
            	
            	Map<String, Object> userMap = resultList.get(0);
            	redirectAttributes.addFlashAttribute("id", userMap.get("id").toString());
                return "redirect:/vhome";

            } else {
            	model.addAttribute("loginerror","ログインに失敗しました");
            }
        }

        return "vlogin";
    }
	
}
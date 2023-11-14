package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;

@Controller
public class VloginController {

	//一覧表示用
	@RequestMapping(path = "/vlogin", method = RequestMethod.GET)
	public String viewPage() {

		return "vlogin";
	}
	
	@RequestMapping(path = "/vsignup", method = RequestMethod.GET)
	public String viewPage1() {

		return "vsignup";
	}

	//ログイン検証用
	@RequestMapping(path = "/vlogin", method = RequestMethod.POST)
	public String loginPost(String id, String password, HttpSession session) {

		session.setAttribute("username", id);

		return "redirect:/vhome";
	}
}
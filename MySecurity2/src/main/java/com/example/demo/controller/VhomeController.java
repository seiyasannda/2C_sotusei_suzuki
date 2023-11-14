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
public class VhomeController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(path = "/vhome", method = RequestMethod.GET)
	public String viewPage() {

		return "vhome";
	}

	//一覧表示用
	@RequestMapping(path = "/vhome", method = RequestMethod.POST)
	public String loginPost(String pjId, Model model) {

		List<Map<String, Object>> resultList = jdbcTemplate
				.queryForList("SELECT * FROM pjdata WHERE id = ?",pjId);

		model.addAttribute("resutList", resultList);

		return "vhome";
	}
}

package com.example.demo.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller

public class UssController {
    @Autowired
    JdbcTemplate jdbcTemplate;
	
		@RequestMapping(path = "/uss", method = RequestMethod.GET)
		public String viewPage() {

			return "uss";
		}
	
	
		@PostMapping("/ussearch")
	    public String registerVstore(
	    							 @RequestParam("Sname") String Sname,
	                                 @RequestParam("Simg") MultipartFile Simg,
	                                 @RequestParam("example1") String example1,
	                                 @RequestParam("example2") String example2,
	                                 @RequestParam("postal_code") String postalCode,
	                                 @RequestParam("prefecture") String prefecture,
	                                 @RequestParam("city") String city,
	                                 @RequestParam("address1") String address1,
	                                 @RequestParam("address2") String address2,
	                                 @RequestParam("Stel") String Stel,
	                                 Model model,
	                                 RedirectAttributes redirectAttributes)throws IOException {

			  String query = "SELECT * FROM vstoretable WHERE " +
	                   "Sname LIKE ? " +
	                   "AND example1 LIKE ? " +
	                   "AND example2 LIKE ? " +
	                   "AND postal_code LIKE ? " +
	                   "AND prefecture LIKE ? " +
	                   "AND city LIKE ? " +
	                   "AND address1 LIKE ? " +
	                   "AND address2 LIKE ? " +
	                   "AND Stel LIKE ?";

	    // SQLのパラメータに'%value%'をセットする
	    String searchSname = "%" + Sname + "%";
	    String searchExample1 = "%" + example1 + "%";
	    String searchExample2 = "%" + example2 + "%";
	    String searchPostalCode = "%" + postalCode + "%";
	    String searchPrefecture = "%" + prefecture + "%";
	    String searchCity = "%" + city + "%";
	    String searchAddress1 = "%" + address1 + "%";
	    String searchAddress2 = "%" + address2 + "%";
	    String searchStel = "%" + Stel + "%";

	    // パラメータを含めてクエリを実行する
	    List<Map<String, Object>> store = jdbcTemplate.queryForList(query,
	        searchSname, searchExample1, searchExample2,
	        searchPostalCode, searchPrefecture, searchCity,
	        searchAddress1, searchAddress2, searchStel);
		
	    
	    
	    return  "uslist";	    	    
	         
	         ///
		}

	   	    // 登録が完了したらリダイレクトまたは適切な画面に遷移する
	   	 
	   	
}

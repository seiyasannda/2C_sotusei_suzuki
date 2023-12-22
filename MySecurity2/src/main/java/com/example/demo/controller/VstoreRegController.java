package com.example.demo.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes("id")
public class VstoreRegController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(path = "/vstoreReg", method = RequestMethod.GET)
    public String viewPage(@SessionAttribute("id")String login_id, Model model) {
    	  model.addAttribute("id", login_id);
        return "vstoreReg";
    }

    @PostMapping("/vstoreReg")
    public String registerVstore(@ModelAttribute("id")String login_id,
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

       // ファイルアップロードや他の処理が必要な場合はここで実装する
    	List<Map<String, Object>> store = jdbcTemplate.queryForList("SELECT * FROM vstoretable WHERE store_id = ?", login_id);
    	
    	    
         
         ///
   	  if (!store.isEmpty()) {
   		  
   		
   		  
   		  
   		  
   		byte[] byteData = Simg.getBytes();
   		String encodedImage = Base64.getEncoder().encodeToString(byteData); 
   		 
             // queryForObjectを使用して単一の値（id）を取得
   		String sql = "UPDATE vstoretable SET Sname = ?, Simg = ?, example1 = ?, example2 = ?, postal_code = ?, prefecture = ?, city = ?, address1 = ?, address2 = ?, Stel = ? WHERE store_id = ?";
   		jdbcTemplate.update(sql, Sname,encodedImage , example1, example2, postalCode, prefecture, city, address1, address2, Stel, login_id);

             // modelに追加
             redirectAttributes.addFlashAttribute("message", "更新が完了しました。");
     		return "redirect:/vstoreReg";
   	   
   	  } else {
   		  
   	     String sql = "INSERT INTO vstoretable (Sname, Simg, example1, example2, postal_code, prefecture, city, address1, address2, Stel,store_id) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
   	     	jdbcTemplate.update(sql, Sname, Simg.getOriginalFilename(), example1, example2, postalCode, prefecture, city, address1, address2, Stel,login_id);
    		redirectAttributes.addFlashAttribute("message", "登録が完了しました。");
    		return "redirect:/vstoreReg";
   	    }
   	    

   	    // 登録が完了したらリダイレクトまたは適切な画面に遷移する
   	 
   	}
        // データベースへの挿入処理
    
        // 登録が完了したらリダイレクトまたは適切な画面に遷移する
       
}

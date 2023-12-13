package com.example.demo.controller;






import java.sql.Date;

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

public class VdbRegController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(path = "/vdbReg", method = RequestMethod.GET)
    public String viewPage(@SessionAttribute("id")String login_id, Model model) {
    	  model.addAttribute("id", login_id);
        return "vdbReg";
    }

    @PostMapping("/vdbReg")
    public String registerVitem(
    		 @RequestParam("barcode") String barcode,
            @RequestParam("productName") String Iname,
            @RequestParam("imagePath") MultipartFile Iimg,
            @RequestParam("genre") String genre,
            @RequestParam("expirationDate") Date expirationDate,
            @RequestParam("price") String Ipri,
            @ModelAttribute("id")String login_id,
            Model model,
            RedirectAttributes redirectAttributes) {

        // ファイルアップロードや他の処理が必要な場合はここで実装する

        // データベースへの挿入処理
    	String sql = "INSERT INTO vitems (barcode, productName, imagePath, genre, expirationDate, price, store_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, barcode, Iname, Iimg.getOriginalFilename(), genre, expirationDate, Ipri, login_id);

        redirectAttributes.addFlashAttribute("message", "登録が完了しました。");
        // 登録が完了したらリダイレクトまたは適切な画面に遷移する
        return "redirect:/vdbReg";
    }
}

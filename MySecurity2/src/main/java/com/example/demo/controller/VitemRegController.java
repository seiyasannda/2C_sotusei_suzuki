package com.example.demo.controller;

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
public class VitemRegController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(path = "/vitemReg", method = RequestMethod.GET)
    public String viewPage() {
        return "vitemReg";
    }

    @PostMapping("/vitemReg")
    public String registerVitem(
            @RequestParam("productName") String Iname,
            @RequestParam("imagePath") MultipartFile Iimg,
            @RequestParam("price") String Ipri,
            Model model,
            RedirectAttributes redirectAttributes) {

        // ファイルアップロードや他の処理が必要な場合はここで実装する

        // データベースへの挿入処理
        String sql = "INSERT INTO vitems (imagePath, productName, price) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, Iimg.getOriginalFilename(), Iname, Ipri);

        redirectAttributes.addFlashAttribute("message", "登録が完了しました。");

        // 登録が完了したらリダイレクトまたは適切な画面に遷移する
        return "redirect:/vitemReg";
    }
}

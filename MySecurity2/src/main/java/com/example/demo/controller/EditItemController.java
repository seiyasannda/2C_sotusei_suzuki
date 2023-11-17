package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EditItemController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @PostMapping("/editItem")
    public String editItem(
            @RequestParam("productName") String Iname,
            @RequestParam("imagePath") MultipartFile Iimg,
            @RequestParam("price") String Ipri,
            @RequestParam("itemId") Long itemId,
            Model model,
            RedirectAttributes redirectAttributes) {

        // ファイルアップロードや他の処理が必要な場合はここで実装する

        // データベースの商品情報を更新
        String sql = "UPDATE vitems SET productName = ?, imagePath = ?, price = ? WHERE id = ?";
        jdbcTemplate.update(sql, Iname, Iimg.getOriginalFilename(), Ipri, itemId);

        redirectAttributes.addFlashAttribute("message", "商品情報が更新されました。");

        // 更新が完了したらリダイレクトまたは適切な画面に遷移する
        return "redirect:/vitemList";
    }
}
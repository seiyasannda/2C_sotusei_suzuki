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
public class VstoreRegController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(path = "/vstoreReg", method = RequestMethod.GET)
    public String viewPage() {
        return "vstoreReg";
    }

    @PostMapping("/vstoreReg")
    public String registerVstore(@RequestParam("Sname") String Sname,
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
                                 RedirectAttributes redirectAttributes) {

        // ファイルアップロードや他の処理が必要な場合はここで実装する

        // データベースへの挿入処理
        String sql = "INSERT INTO vstoretable (Sname, Simg, example1, example2, postal_code, prefecture, city, address1, address2, Stel) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, Sname, Simg.getOriginalFilename(), example1, example2, postalCode, prefecture, city, address1, address2, Stel);
        redirectAttributes.addFlashAttribute("message", "登録が完了しました。");
        // 登録が完了したらリダイレクトまたは適切な画面に遷移する
        return "redirect:/vstoreReg";
    }
}

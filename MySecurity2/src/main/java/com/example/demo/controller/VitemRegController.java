package com.example.demo.controller;






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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes("id")

public class VitemRegController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(path = "/vitemReg", method = RequestMethod.GET)
    public String viewPage(@SessionAttribute("id")String login_id, Model model) {
    	  model.addAttribute("id", login_id);
        return "vitemReg";
    }

    @PostMapping("/vitemReg")
    public String registerVitem(
    		 @RequestParam("barcode") String barcode,
           
    		 @ModelAttribute("id")String login_id,
            Model model,
            RedirectAttributes redirectAttributes) {

        // ファイルアップロードや他の処理が必要な場合はここで実装する

        // データベースへの挿入処理
    	  Map<String, Object> item = jdbcTemplate.queryForMap("SELECT * FROM vitems WHERE barcode = ?", barcode);

    
         
          
    	  if (!item.isEmpty()) {
    	        String sql = "SELECT id FROM vitems WHERE barcode = ?";
    	        List<Map<String, Object>> items = jdbcTemplate.queryForList(sql, barcode);

    	        // Mapから"id"の値を取得してmodelに追加
    	        model.addAttribute("message", items);

    	        return "vitemReg";
    	    } else {
    	        model.addAttribute("loginerror", "ログインに失敗しました");
    	    }

    	    // 登録が完了したらリダイレクトまたは適切な画面に遷移する
    	    return "vitemReg";
    	}
}

package com.example.demo.controller;






import java.util.List;

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

        // データベースへの検索処理
    	String sql = "SELECT id FROM vitems WHERE barcode = ? and store_id =?";
    	// queryForListを使用して結果のリストを取得
    	List<Integer> itemIds = jdbcTemplate.queryForList(sql, Integer.class, barcode,login_id);

    	if (!itemIds.isEmpty()) {
    	    Integer itemId = itemIds.get(0);

    	    // modelに追加
    	    model.addAttribute("message", itemId);

    	    return "redirect:viewItem/" + itemId;
    	} else {
    		redirectAttributes.addFlashAttribute("message", "商品登録されていません");
    	

    	// 登録が完了したらリダイレクトまたは適切な画面に遷移する
    	return "redirect:vitemReg";

    	}
    }
    }

package com.example.demo.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Base64;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class VitemEditController {

    @Autowired
    JdbcTemplate jdbcTemplate;
   
    @RequestMapping(path = "/viewItem/{itemId}", method = RequestMethod.GET)
    public String viewItem(@PathVariable Long itemId, Model model)throws IOException {
        // Fetch the details of the specific item
        Map<String, Object> item = jdbcTemplate.queryForMap("SELECT * FROM vitems WHERE id = ?", itemId);
//        byte[] imageData = jdbcTemplate.queryForObject("SELECT imagePath FROM vitems WHERE id = ?", byte[].class, itemId);
//        String encodedImage = Base64.getEncoder().encodeToString(imageData);
//
//     // モデルにエンコードされた画像データを追加
//        model.addAttribute("encodedImage", encodedImage);
        // Pass the item details to the HTML file
        model.addAttribute("item", item);
        
        

        return "viewItem";
        
        
    }
    
    @RequestMapping(path = "/updateItem", method = RequestMethod.POST)
   public String updateItem(@RequestParam("itemId") Long itemId,
                            @RequestParam("productName") String productName,
                            @RequestParam("imagePath") MultipartFile Iimg,
                            @RequestParam("price") BigDecimal price,
                            RedirectAttributes redirectAttributes) throws IOException{
       // 商品情報をデータベースで更新
    	if (!Iimg.isEmpty()) {
    		byte[] byteData = Iimg.getBytes();
       		String encodedImage = Base64.getEncoder().encodeToString(byteData); 
        	
            String updateQuery = "UPDATE vitems SET productName = ?,imagePath = ?, nebiki = ? WHERE id = ?";
            jdbcTemplate.update(updateQuery, productName,encodedImage, price, itemId);

            redirectAttributes.addFlashAttribute("message", "商品情報が更新されました。");
            return "redirect:/viewItem/" + itemId;
    		
    	}else {
    
    	
        String updateQuery = "UPDATE vitems SET productName = ?, nebiki = ? WHERE id = ?";
        jdbcTemplate.update(updateQuery, productName, price, itemId);

        redirectAttributes.addFlashAttribute("message", "商品情報が更新されました。");
        return "redirect:/viewItem/" + itemId;
        }
    }
    
    @RequestMapping(path = "/barItem", method = RequestMethod.POST)
    public String barItem(@RequestParam("itemId") Long itemId,
                             @RequestParam("productName") String productName,
                             String imagePath,
                             @RequestParam("price") BigDecimal price,
                             RedirectAttributes redirectAttributes) {
        // 商品情報をデータベースで更新
         String updateQuery = "UPDATE vitems SET productName = ?,imagePath = ?, price = ? WHERE id = ?";
         jdbcTemplate.update(updateQuery, productName,imagePath, price, itemId);

         redirectAttributes.addFlashAttribute("message", "商品情報が更新されました。");
         return "redirect:/viewItem/" + itemId;
     }
}


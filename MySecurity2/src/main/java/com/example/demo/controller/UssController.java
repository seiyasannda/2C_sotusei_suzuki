package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UssController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(path = "/uss", method = RequestMethod.GET)
    public String viewPage() {
        return "uss"; // ここは「uss.html」や表示するビューの名前に合わせて変更してください
    }

    @RequestMapping(path = "/uss", method = RequestMethod.POST)
    public String search(
            Model model,
            @RequestParam(name = "Sname", required = false) String Sname,
            @RequestParam(name = "example1", required = false) String example1,
            @RequestParam(name = "example2", required = false) String example2,
            @RequestParam(name = "postal_code", required = false) String postalCode,
            @RequestParam(name = "prefecture", required = false) String prefecture,
            @RequestParam(name = "city", required = false) String city,
            @RequestParam(name = "address1", required = false) String address1,
            @RequestParam(name = "address2", required = false) String address2,
            @RequestParam(name = "Stel", required = false) String Stel
    ) {
        //List<Map<String, Object>> items = jdbcTemplate.queryForList("SELECT * FROM vitems");

        String storeQuery = "SELECT * FROM vstoretable WHERE 1=1"; // 店舗のクエリ
        List<Object> storeParams = new ArrayList<>();

        if (Sname != null && !Sname.isEmpty()) {
            storeQuery += " AND Sname LIKE ?";
            storeParams.add("%" + Sname + "%");
        }

        if (example1 != null && !example1.isEmpty() && example2 != null && !example2.isEmpty()) {
            storeQuery += " AND example1 >= ? AND example2 <= ?";
            storeParams.add(example1);
            storeParams.add(example2);
        }

        if (postalCode != null && !postalCode.isEmpty()) {
            storeQuery += " AND postal_code = ?";
            storeParams.add(postalCode);
        }

        if (prefecture != null && !prefecture.isEmpty()) {
            storeQuery += " AND prefecture LIKE ?";
            storeParams.add("%" + prefecture + "%");
        }

        if (city != null && !city.isEmpty()) {
            storeQuery += " AND city LIKE ?";
            storeParams.add("%" + city + "%");
        }

        if (address1 != null && !address1.isEmpty()) {
            storeQuery += " AND address1 LIKE ?";
            storeParams.add("%" + address1 + "%");
        }

        if (address2 != null && !address2.isEmpty()) {
            storeQuery += " AND address2 LIKE ?";
            storeParams.add("%" + address2 + "%");
        }

        if (Stel != null && !Stel.isEmpty()) {
            storeQuery += " AND Stel = ?";
            storeParams.add(Stel);
        }

        List<Map<String, Object>> store = jdbcTemplate.queryForList(storeQuery, storeParams.toArray());

        //model.addAttribute("items", items);
        model.addAttribute("store", store);
        
        return "ussr"; 
    }
}
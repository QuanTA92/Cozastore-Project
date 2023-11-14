package com.cybersoft.cozastore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {

    @GetMapping("/")
    public String index(Model model) {
//        // Thêm một thuộc tính "message" với giá trị là "hello" vào model
//        model.addAttribute("message", "hello");

        // Trả về tên của view (trang) mà bạn muốn hiển thị (ở đây là "index")
        return "index";
    }
}

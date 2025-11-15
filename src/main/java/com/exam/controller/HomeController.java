package main.java.com.exam.controller;  // đặt tên package gì thì lát nhớ đúng base-package

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")   // map cho GET "/"
    public String home(Model model) {
        model.addAttribute("message", "Hello Spring MVC không Maven!");
        return "home"; // => /WEB-INF/views/home.jsp
    }
}

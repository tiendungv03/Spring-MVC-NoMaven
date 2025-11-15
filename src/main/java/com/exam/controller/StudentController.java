package main.java.com.exam.controller;


import main.java.com.exam.dao.StudentDao;
import main.java.com.exam.model.Student;
import main.java.com.exam.service.StudentFileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    private StudentDao dao = new StudentDao();
    private StudentFileService fileService = new StudentFileService();

    // Hiển thị form nhập
    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("student", new Student());
        return "student-form";
    }

    // Submit form -> lưu DB + lưu file txt/json/xml
    @PostMapping("/save")
    public String save(@ModelAttribute("student") Student s, Model model) {
        // 1. Lưu DB
        dao.insert(s);

        // 2. Lấy lại danh sách từ DB
        List<Student> list = dao.findAll();

        // 3. Lưu ra file
        try {
            fileService.saveToTxt(list);
            fileService.saveToJson(list);
            fileService.saveToXml(list);
        } catch (IOException e) {
            e.printStackTrace();
        }

        model.addAttribute("students", list);
        return "student-list";
    }

    // Trang xem danh sách (chỉ load từ DB)
    @GetMapping("/list")
    public String list(Model model) {
        List<Student> list = dao.findAll();
        model.addAttribute("students", list);
        return "student-list";
    }

    // (Optional) Trang xem dữ liệu đọc từ TXT/JSON
    @GetMapping("/from-txt")
    public String listFromTxt(Model model) throws IOException {
        List<Student> list = fileService.readFromTxt();
        model.addAttribute("students", list);
        return "student-list";
    }

    @GetMapping("/from-json")
    public String listFromJson(Model model) throws IOException {
        List<Student> list = fileService.readFromJson();
        model.addAttribute("students", list);
        return "student-list";
    }
}

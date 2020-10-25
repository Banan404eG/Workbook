package tk.exdeath.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import tk.exdeath.model.service.StudentService;
import tk.exdeath.model.service.TeacherService;

@Controller
public class TestController {

    @GetMapping("/")
    public String test(Model model) {

        StudentService studentService = new StudentService();
        model.addAttribute("student", studentService.readByName("Иван", "Иванов"));

        TeacherService teacherService = new TeacherService();
        model.addAttribute("teacher", teacherService.readByName("John", "Doe"));

        return "test";
    }

}

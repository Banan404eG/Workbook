package tk.exdeath.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import tk.exdeath.model.Teacher;
import tk.exdeath.model.service.TeacherService;

@Controller
public class TestController {

    @GetMapping("/")
    public String test(Model model) {

        TeacherService service = new TeacherService();
        Teacher teacher = service.readByName("John", "Doe");

        model.addAttribute("teacher", teacher);
        model.addAttribute("students", teacher.getStudents());

        return "test";
    }

}

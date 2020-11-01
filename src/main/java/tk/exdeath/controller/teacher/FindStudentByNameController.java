package tk.exdeath.controller.teacher;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.model.service.StudentService;

@Controller
public class FindStudentByNameController {

    @GetMapping("/findStudentByName")
    public String findByName() {
        return "findStudentByName";
    }

    @PostMapping("/findStudentByName")
    public String findStudentByName(
            @RequestParam String firstName,
            @RequestParam String secondName, Model model) {

        StudentService studentService = new StudentService();
        int studentID = studentService.readByName(firstName, secondName).getStudentID();
        return "findStudentByName";
    }
}

package tk.exdeath.controller.teacher;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.model.Student;
import tk.exdeath.model.service.StudentService;

@Controller
public class FindStudentByNameController {

    final String PATH = "teacher/findStudentByName";

    @GetMapping("/findStudentByName")
    public String findByName() {
        return PATH;
    }

    @PostMapping("/findStudentByName")
    public String findStudentByName(
            @RequestParam String firstName,
            @RequestParam String secondName, Model model) {

        StudentService studentService = new StudentService();
        Student student = studentService.readByName(firstName, secondName);

        if (studentDoesNotExist(student)) {
            model.addAttribute("Error", "Ученика с такими именем и фамилией не существует");
            return PATH;
        }

        return "redirect:/tasksByID?id=" + student.getStudentID();
    }

    private boolean studentDoesNotExist(Student student) {
        return student.getLogin().equals("null");
    }
}

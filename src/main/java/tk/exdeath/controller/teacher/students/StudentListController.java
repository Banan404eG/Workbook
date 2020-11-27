package tk.exdeath.controller.teacher.students;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import tk.exdeath.model.teacher.students.StudentList;

@Controller
public class StudentListController {

    final String PATH = "teacher/students/studentList";

    @GetMapping("/studentList")
    public String studentList(Model model) {
        try {
            StudentList studentList = new StudentList();
            model.addAttribute("students", studentList.getStudentNames());
            model.addAttribute("IDs", studentList.getStudentIDs());
            return PATH;
        } catch (RuntimeException ex) {
            model.addAttribute("Error", ex.getMessage());
            return "errorPage";
        }
    }
}

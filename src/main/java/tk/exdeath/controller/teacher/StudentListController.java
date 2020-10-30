package tk.exdeath.controller.teacher;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import tk.exdeath.model.Student;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentListController {

    @GetMapping("/studentList")
    public String studentList(Model model) {
        model.addAttribute("students", getStudentNames());
        return "teacher/studentList";
    }


    private List<String> getStudentNames() {
        List<String> studentNames = new ArrayList<>();
        String firstName;
        String secondName;
        for (Student student : LoggedTeacher.getTeacher().getStudents()) {
            firstName = student.getFirstName();
            secondName = student.getSecondName();
            studentNames.add(firstName + " " + secondName);
        }

        return studentNames;
    }
}

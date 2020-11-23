package tk.exdeath.controller.teacher.students;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import tk.exdeath.controller.teacher.LoggedTeacher;
import tk.exdeath.model.database.entities.Student;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentList {

    final String PATH = "teacher/students/studentList";

    @GetMapping("/studentList")
    public String studentList(Model model) {
        List<String> studentNames = new ArrayList<>();
        List<String> studentIDs = new ArrayList<>();

        for (Student student : LoggedTeacher.getTeacher().getStudents()) {
            studentNames.add(student.getFirstName() + " " + student.getSecondName());
            studentIDs.add(String.valueOf(student.getStudentID()));
        }

        model.addAttribute("students", studentNames);
        model.addAttribute("IDs", studentIDs);
        return PATH;
    }
}

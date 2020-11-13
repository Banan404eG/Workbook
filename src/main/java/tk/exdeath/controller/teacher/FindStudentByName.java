package tk.exdeath.controller.teacher;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.model.Student;
import tk.exdeath.model.service.StudentService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FindStudentByName {

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
        List<Student> students = studentService.readByName(firstName, secondName);

        if (studentDoesNotExist(students)) {
            model.addAttribute("Error", "Ученика с такими именем и фамилией не существует");
            return PATH;
        }

        if (studentIsUnique(students)) {
            return "redirect:/tasksByStudentID?id=" + students.get(0).getStudentID();
        }

        return returnStudentList(model, students);
    }

    private boolean studentDoesNotExist(List<Student> students) {
        return students.isEmpty();
    }

    private boolean studentIsUnique(List<Student> students) {
        return students.size() == 1;
    }

    private String returnStudentList(Model model, List<Student> students) {
        List<String> studentNames = new ArrayList<>();
        List<String> studentIDs = new ArrayList<>();
        String firstName;
        String secondName;
        String studentClass;

        for (Student student : students) {
            firstName = student.getFirstName();
            secondName = student.getSecondName();
            studentClass = student.getStudentClass();

            studentNames.add(firstName + " " + secondName + " " + studentClass);
            studentIDs.add(String.valueOf(student.getStudentID()));
        }

        model.addAttribute("students", studentNames);
        model.addAttribute("IDs", studentIDs);
        return "teacher/studentList";
    }
}

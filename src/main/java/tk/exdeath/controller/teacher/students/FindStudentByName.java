package tk.exdeath.controller.teacher.students;

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
    public String returnPage() {
        return PATH;
    }

    @PostMapping("/findStudentByName")
    public String findStudentByName(
            @RequestParam(defaultValue = "null") String firstName,
            @RequestParam(defaultValue = "null") String secondName, Model model) {

        if (incorrectInput(firstName, secondName)) {
            model.addAttribute("Message", "Некорректный ввод");
            return PATH;
        }

        StudentService studentService = new StudentService();
        List<Student> students = studentService.readByName(firstName, secondName);
        if (studentDoesNotExist(students)) {
            model.addAttribute("Error", "Ученика с такими именем и фамилией не существует");
            return PATH;
        }
        if (studentIsUnique(students)) {
            return "redirect:/tasksByStudentID?id=" + students.get(0).getStudentID();
        }

        studentService.closeSession();
        return returnStudentList(model, students);
    }


    private boolean incorrectInput(String firstName, String secondName) {
        return firstName.equals("null") || secondName.equals("null");
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

        for (Student student : students) {
            studentNames.add(student.getFirstName() + " " + student.getSecondName() + " " + student.getStudentClass());
            studentIDs.add(String.valueOf(student.getStudentID()));
        }

        model.addAttribute("students", studentNames);
        model.addAttribute("IDs", studentIDs);
        return "teacher/studentList";
    }
}

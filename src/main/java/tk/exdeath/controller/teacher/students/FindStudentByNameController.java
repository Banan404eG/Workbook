package tk.exdeath.controller.teacher.students;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.model.logic.teacher.students.FindStudentByName;

import javax.annotation.Resource;

@Controller
public class FindStudentByNameController {

    final String PATH = "teacher/students/findStudentByName";
    final String STUDENT_LIST_PATH = "teacher/students/studentList";

    @Resource(name = "getFindStudentByName")
    private FindStudentByName findStudent;

    @GetMapping("/findStudentByName")
    public String returnPage() {
        return PATH;
    }

    @PostMapping("/findStudentByName")
    public String findStudentByName(
            @RequestParam(defaultValue = "null") String firstName,
            @RequestParam(defaultValue = "null") String secondName, Model model) {
        try {
            findStudent.findStudentByName(firstName, secondName);
            if (findStudent.studentIsUnique()) {
                return "redirect:/tasksByStudentID?id=" + findStudent.getStudentID();
            }
            model.addAttribute("students", findStudent.getStudentNames());
            model.addAttribute("IDs", findStudent.getStudentIDs());
            return STUDENT_LIST_PATH;
        } catch (RuntimeException ex) {
            model.addAttribute("Error", ex.getMessage());
            return PATH;
        }
    }
}

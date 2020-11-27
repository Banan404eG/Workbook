package tk.exdeath.controller.teacher.marks;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.model.teacher.marks.MarksByStudentID;

@Controller
public class MarksByStudentIDController {

    final String PATH = "teacher/marks/marksByStudentID";

    @GetMapping("/marksByStudentID")
    public String marksByStudentID(
            @RequestParam int studentID, Model model) {
        try {
            MarksByStudentID marksByID = new MarksByStudentID(studentID);
            model.addAttribute("studentName", marksByID.getStudentName());
            model.addAttribute("tableNames", marksByID.getTableNames());
            model.addAttribute("marks", marksByID.getMarks());
            return PATH;
        } catch (RuntimeException ex) {
            model.addAttribute("Error", ex.getMessage());
            return "errorPage";
        }
    }
}

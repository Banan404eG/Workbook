package tk.exdeath.controller.teacher.answers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.model.teacher.answers.TasksByStudentID;

@Controller
public class TasksByStudentIDController {

    final String PATH = "workbook/tasksByStudentID";

    @GetMapping("/tasksByStudentID")
    public String tasksByID(
            @RequestParam(defaultValue = "0") int id, Model model) {
        try {
            TasksByStudentID tasksByID = new TasksByStudentID(id);
            model.addAttribute("studentID", id);
            model.addAttribute("Name", tasksByID.getStudentName());
            model.addAttribute("tableNames", tasksByID.getTableNames());
            model.addAttribute("IDs", tasksByID.getTaskIDs());
        } catch (RuntimeException ex) {
            model.addAttribute("Error", ex.getMessage());
        }
        return PATH;
    }
}

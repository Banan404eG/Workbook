package tk.exdeath.controller.teacher.students;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.model.teacher.students.TasksByStudentID;

@Controller
public class TasksByStudentIDController {

    final String PATH = "workbook/tasksByStudentID";

    @GetMapping("/tasksByStudentID")
    public String tasksByID(
            @RequestParam(defaultValue = "0") int id, Model model) {

        TasksByStudentID tasksByID = new TasksByStudentID(id);
        if (tasksByID.incorrectInput()) {
            model.addAttribute("Error", "Некорректный ввод");
            return PATH;
        }
        if (tasksByID.studentDoesNotExist()) {
            model.addAttribute("Error", "Ученика с такими ID не существует");
            return PATH;
        }
        model.addAttribute("studentID", id);
        model.addAttribute("Name", tasksByID.getStudentName());
        model.addAttribute("tableNames", tasksByID.getTableNames());
        model.addAttribute("IDs", tasksByID.getTaskIDs());
        return PATH;
    }
}

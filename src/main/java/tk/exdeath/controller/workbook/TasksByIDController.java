package tk.exdeath.controller.workbook;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.model.Student;
import tk.exdeath.model.Task;
import tk.exdeath.model.service.StudentService;

import java.util.List;

@Controller
public class TasksByIDController {

    @GetMapping("/tasksByID")
    public String tasksByID(
            @RequestParam int id, Model model) {

        StudentService studentService = new StudentService();
        Student student = studentService.readByID(id);
        String firstName = student.getFirstName();
        String secondName = student.getSecondName();
        List<Task> tasks = student.getTasks();

        model.addAttribute("Name", firstName + " " + secondName);
        model.addAttribute("tasks", tasks);
        return "workbook/tasksByID";
    }
}

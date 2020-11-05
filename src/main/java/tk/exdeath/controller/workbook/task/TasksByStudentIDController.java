package tk.exdeath.controller.workbook.task;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.model.Student;
import tk.exdeath.model.service.StudentService;

@Controller
public class TasksByStudentIDController {

    final String PATH = "workbook/tasksByStudentID";


    @GetMapping("/tasksByStudentID")
    public String tasksByID(
            @RequestParam int id, Model model) {

        StudentService studentService = new StudentService();
        Student student = studentService.readByID(id);

        if (studentDoesNotExist(student)) {
            model.addAttribute("Error", "Ученика с такими ID не существует");
            return PATH;
        }

        TaskLibrary.setTasks(student.getTasks());

        model.addAttribute("Name", student.getFirstName() + " " + student.getSecondName());
        model.addAttribute("taskNames", TaskLibrary.getTaskNames());
        model.addAttribute("IDs", TaskLibrary.getIDs());
        return PATH;
    }


    private boolean studentDoesNotExist(Student student) {
        return student.getLogin().equals("null");
    }
}

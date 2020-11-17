package tk.exdeath.controller.workbook;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.model.Student;
import tk.exdeath.model.Task;
import tk.exdeath.model.service.StudentService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TasksByStudentID {

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

        List<Task> tasks = student.getTasks();

        model.addAttribute("Name", student.getFirstName() + " " + student.getSecondName());
        model.addAttribute("tableNames", tableNames(tasks));
        model.addAttribute("IDs", IDs(tasks));
        model.addAttribute("studentID", id);
        studentService.closeSession();
        return PATH;
    }


    private boolean studentDoesNotExist(Student student) {
        return student.getLogin().equals("null");
    }

    private List<Integer> IDs(List<Task> tasks) {
        List<Integer> IDs = new ArrayList<>();
        for (Task task : tasks) {
            IDs.add(task.getId());
        }
        return IDs;
    }

    private List<String> tableNames(List<Task> tasks) {
        List<String> tableNames = new ArrayList<>();
        for (Task task : tasks) {
            tableNames.add(task.getTableName());
        }
        return tableNames;
    }
}

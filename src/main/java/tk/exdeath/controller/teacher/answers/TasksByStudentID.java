package tk.exdeath.controller.teacher.answers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.model.database.entities.Student;
import tk.exdeath.model.database.entities.Task;
import tk.exdeath.model.database.service.StudentService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TasksByStudentID {

    final String PATH = "workbook/tasksByStudentID";

    @GetMapping("/tasksByStudentID")
    public String tasksByID(
            @RequestParam(defaultValue = "0") int id, Model model) {

        if (incorrectInput(id)) {
            model.addAttribute("Error", "Некорректный ввод");
            return PATH;
        }

        StudentService studentService = new StudentService();
        Student student = studentService.readByID(id);
        if (studentDoesNotExist(student)) {
            model.addAttribute("Error", "Ученика с такими ID не существует");
            return PATH;
        }

        setModelAttributes(student, model);
        studentService.closeSession();
        return PATH;
    }


    private boolean incorrectInput(int id) {
        return id < 1;
    }

    private boolean studentDoesNotExist(Student student) {
        return student.getLogin().equals("null");
    }

    private void setModelAttributes(Student student, Model model) {
        List<String> tableNames = new ArrayList<>();
        List<Integer> taskIDs = new ArrayList<>();

        for (Task task : student.getTasks()) {
            tableNames.add(task.getTableName());
            taskIDs.add(task.getId());
        }

        model.addAttribute("Name", student.getFirstName() + " " + student.getSecondName());
        model.addAttribute("studentID", student.getStudentID());
        model.addAttribute("tableNames", tableNames);
        model.addAttribute("IDs", taskIDs);
    }
}

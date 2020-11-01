package tk.exdeath.controller.workbook;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.model.Student;
import tk.exdeath.model.Task;
import tk.exdeath.model.service.StudentService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TasksByIDController {

    final String PATH = "workbook/tasksByID";

    private final List<String> taskNames = new ArrayList<>();
    private final Map<Integer, Object> IDWorkbook = new HashMap<>();
    private final Map<Integer, String> IDLesson = new HashMap<>();
    private final Map<Integer, Integer> IDGrade = new HashMap<>();
    private final Map<Integer, Integer> IDPage = new HashMap<>();

    @GetMapping("/tasksByID")
    public String tasksByID(
            @RequestParam int id, Model model) {

        StudentService studentService = new StudentService();
        Student student = studentService.readByID(id);

        if (studentDoesNotExist(student)) {
            model.addAttribute("Error", "Ученика с такими ID не существует");
            return PATH;
        }

        setTasks(student.getTasks());

        model.addAttribute("Name", student.getFirstName() + " " + student.getSecondName());
        model.addAttribute("taskNames", taskNames);
        model.addAttribute("IDs", IDWorkbook.keySet());
        return PATH;
    }


    private boolean studentDoesNotExist(Student student) {
        return student.getLogin().equals("null");
    }

    private void setTasks(List<Task> tasks) {
        int id;
        String tableName;

        for (Task task : tasks) {
            id = task.getId();
            tableName = task.getTableName();
            taskNames.add(tableName);

            WorkbookLibrary.setWorkbookAndPage(task.getTaskID(), tableName);
            IDWorkbook.put(id, WorkbookLibrary.getWorkbook());
            IDLesson.put(id, WorkbookLibrary.getLesson());
            IDGrade.put(id, WorkbookLibrary.getGrade());
            IDPage.put(id, WorkbookLibrary.getPage());
        }
    }


    @GetMapping("/taskByID")
    public String taskByID(
            @RequestParam int id, Model model) {

        String lesson = IDLesson.get(id);
        int grade = IDGrade.get(id);
        int page = IDPage.get(id);

        model.addAttribute("task", IDWorkbook.get(id));
        model.addAttribute("role", "teacher");
        return "workbook/" + lesson + "/" + grade + "/" + page;
    }
}

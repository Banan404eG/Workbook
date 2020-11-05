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
public class TasksByStudentIDController {

    final String PATH = "workbook/tasksByStudentID";

    private Map<Integer, TaskInfo> IDsTasks;
    private List<String> taskNames;

    @GetMapping("/tasksByStudentID")
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
        model.addAttribute("IDs", IDsTasks.keySet());
        return PATH;
    }


    private boolean studentDoesNotExist(Student student) {
        return student.getLogin().equals("null");
    }

    private void setTasks(List<Task> tasks) {
        IDsTasks = new HashMap<>();
        taskNames = new ArrayList<>();

        for (Task task : tasks) {
            String tableName = task.getTableName();
            taskNames.add(tableName);

            WorkbookLibrary.setWorkbook(task.getTaskID(), tableName);

            IDsTasks.put(task.getId(), new TaskInfo(
                    WorkbookLibrary.getGrade(),
                    WorkbookLibrary.getPage(),
                    WorkbookLibrary.getLesson(),
                    tableName,
                    WorkbookLibrary.getStudentAnswers(),
                    WorkbookLibrary.getRightAnswers()));
        }
    }


    private static class TaskInfo {
        int grade, page;
        String tableName, lesson;
        Object studentAnswers, rightAnswers;

        public TaskInfo(int grade, int page, String lesson, String tableName, Object studentAnswers, Object rightAnswers) {
            this.grade = grade;
            this.page = page;
            this.tableName = tableName;
            this.lesson = lesson;
            this.studentAnswers = studentAnswers;
            this.rightAnswers = rightAnswers;
        }
    }


    @GetMapping("/taskByID")
    public String taskByID(
            @RequestParam int id, Model model) {

        TaskInfo taskInfo = IDsTasks.get(id);

        model.addAttribute("studentAnswers", taskInfo.studentAnswers);
        model.addAttribute("role", "teacher");
        model.addAttribute("rightAnswers", taskInfo.rightAnswers);
        return "workbook/" + taskInfo.lesson + "/" + taskInfo.grade + "/" + taskInfo.page;
    }

}

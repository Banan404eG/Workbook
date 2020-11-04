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

    final int RIGHT_ANSWERS_USER_ID = 4;
    final int RIGHT_ANSWERS_TASK_ID = 1;
    final String PATH = "workbook/tasksByID";

    StudentService studentService;
    private final Map<Integer, TaskInfo> IDTask = new HashMap<>();
    private final List<String> taskNames = new ArrayList<>();

    @GetMapping("/tasksByID")
    public String tasksByID(
            @RequestParam int id, Model model) {

        studentService = new StudentService();
        Student student = studentService.readByID(id);

        if (studentDoesNotExist(student)) {
            model.addAttribute("Error", "Ученика с такими ID не существует");
            return PATH;
        }

        setTasks(student.getTasks());

        model.addAttribute("Name", student.getFirstName() + " " + student.getSecondName());
        model.addAttribute("taskNames", taskNames);
        model.addAttribute("IDs", IDTask.keySet());
        return PATH;
    }


    private boolean studentDoesNotExist(Student student) {
        return student.getLogin().equals("null");
    }

    private void setTasks(List<Task> tasks) {
        String tableName;

        for (Task task : tasks) {
            tableName = task.getTableName();
            taskNames.add(tableName);

            WorkbookLibrary.setWorkbookAndPage(task.getTaskID(), tableName);

            IDTask.put(task.getId(), new TaskInfo(
                    WorkbookLibrary.getGrade(),
                    WorkbookLibrary.getPage(),
                    WorkbookLibrary.getLesson(),
                    WorkbookLibrary.getWorkbook(),
                    tableName));
        }
    }


    private static class TaskInfo {
        int grade, page;
        String tableName, lesson;
        Object workbook;

        public TaskInfo(int grade, int page, String lesson, Object workbook, String tableName) {
            this.grade = grade;
            this.page = page;
            this.tableName = tableName;
            this.lesson = lesson;
            this.workbook = workbook;
        }
    }


    @GetMapping("/taskByID")
    public String taskByID(
            @RequestParam int id, Model model) {

        TaskInfo taskInfo = IDTask.get(id);
        String lesson = taskInfo.lesson;
        int grade = taskInfo.grade;
        int page = taskInfo.page;

        model.addAttribute("workbook", IDTask.get(id).workbook);
        model.addAttribute("role", "teacher");
        model.addAttribute("rightAnswers", getRightAnswers(taskInfo.tableName));
        return "workbook/" + lesson + "/" + grade + "/" + page;
    }

    private String getRightAnswers(String tableName) {

        for (Task task : studentService.readByID(RIGHT_ANSWERS_USER_ID).getTasks()) {

            if (task.getTaskID() == RIGHT_ANSWERS_TASK_ID && task.getTableName().equals(tableName)) {
                WorkbookLibrary.setWorkbookAndPage(RIGHT_ANSWERS_TASK_ID, tableName);
                return WorkbookLibrary.getWorkbook().toString();
            }
        }

        return "";
    }

}

package tk.exdeath.controller.workbook.task;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.controller.teacher.LoggedTeacher;
import tk.exdeath.model.Task;
import tk.exdeath.model.service.StudentService;

@Controller
public class AnswersByIDController {

    @GetMapping("/taskByID")
    public String taskByID(@RequestParam int studentID,
            @RequestParam int id, Model model) {

        Task task = getTask(studentID, id);
        if (task == null) {
            model.addAttribute("Error", "Неверный ID");
            return "errorPage";
        }

        if (LoggedTeacher.getTeacher() != null) {
            model.addAttribute("role", "teacher");
        } else {
            model.addAttribute("role", "watcher");
        }
        model.addAttribute("studentAnswers", task.getAnswers());
        //model.addAttribute("rightAnswers", taskInfo.rightAnswers);
        return "workbook/" + task.getLesson() + "/" + task.getGrade() + "/" + task.getPage();
    }

    private Task getTask(int studentID, int id) {
        StudentService studentService = new StudentService();
        for (Task task : studentService.readByID(studentID).getTasks()) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }
}

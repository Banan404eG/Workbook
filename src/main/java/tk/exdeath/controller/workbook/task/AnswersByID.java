package tk.exdeath.controller.workbook.task;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.controller.teacher.LoggedTeacher;
import tk.exdeath.model.Task;
import tk.exdeath.model.service.StudentService;

@Controller
public class AnswersByID {

    final int RIGHT_ANSWERS_STUDENT_ID = 4;

    Model model;
    StudentService studentService;
    String lesson;
    int grade, page;

    @GetMapping("/answersByID")
    public String taskByID(@RequestParam int studentID,
                           @RequestParam int id, Model model) {


        studentService = new StudentService();
        this.model = model;

        Task task = getTask(studentID, id);
        if (task == null) {
            model.addAttribute("Error", "Неверный ID");
            return "errorPage";
        }

        lesson = task.getLesson();
        grade = task.getGrade();
        page = task.getPage();
        model.addAttribute("studentAnswers", task.getAnswers());

        setRightAnswers();
        setRole();

        studentService.closeSession();
        return "workbook/" + lesson + "/" + grade + "/" + page;
    }


    private Task getTask(int studentID, int id) {

        for (Task task : studentService.readByID(studentID).getTasks()) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    private void setRightAnswers() {

        for (Task task : studentService.readByID(RIGHT_ANSWERS_STUDENT_ID).getTasks()) {
            if (task.getLesson().equals(lesson) && task.getGrade() == grade && task.getPage() == page) {
                model.addAttribute("rightAnswers", task.getAnswers());
                return;
            }
        }
        model.addAttribute("rightAnswers", "Правильные ответы не обнаружены");
    }

    private void setRole() {

        if (LoggedTeacher.getTeacher() != null) {
            model.addAttribute("role", "teacher");
        } else {
            model.addAttribute("role", "watcher");
        }
    }
}

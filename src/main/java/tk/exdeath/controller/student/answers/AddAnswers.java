package tk.exdeath.controller.student.answers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.controller.student.LoggedStudent;
import tk.exdeath.model.Student;
import tk.exdeath.model.Task;

@Controller
public class AddAnswers {

    @PostMapping("/answers")
    public String answers(
            @RequestParam(required = false, value = "answers[]") String[] answers,
            @RequestParam String lesson,
            @RequestParam int grade,
            @RequestParam int page) {

        Student student = LoggedStudent.getStudent();
        student.addTask(new Task(student, lesson, grade, page, answers));
        LoggedStudent.update();
        return "redirect:/page?lesson=" + lesson + "&grade=" + grade + "&page=" + page;
    }
}

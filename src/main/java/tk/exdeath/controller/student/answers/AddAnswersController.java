package tk.exdeath.controller.student.answers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.model.student.answers.AddAnswers;

@Controller
public class AddAnswersController {

    @PostMapping("/answers")
    public String answers(
            @RequestParam(required = false, value = "answers[]") String[] answers,
            @RequestParam String lesson, @RequestParam int grade, @RequestParam int page) {

        AddAnswers.addAnswers(lesson, grade, page, answers);
        return "redirect:/page?lesson=" + lesson + "&grade=" + grade + "&page=" + page;
    }
}

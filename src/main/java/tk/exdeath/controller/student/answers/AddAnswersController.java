package tk.exdeath.controller.student.answers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.model.student.answers.AddAnswers;

import javax.annotation.Resource;

@Controller
public class AddAnswersController {

    @Resource(name = "getAddAnswers")
    private AddAnswers addAnswers;

    @PostMapping("/answers")
    public String answers(
            @RequestParam(required = false, value = "answers[]") String[] answers,
            @RequestParam String lesson, @RequestParam int grade, @RequestParam int page, Model model) {
        try {
            addAnswers.addAnswers(lesson, grade, page, answers);
            return "redirect:/page?lesson=" + lesson + "&grade=" + grade + "&page=" + page;
        } catch (RuntimeException ex) {
            model.addAttribute("Error", ex.getMessage());
            return "errorPage";
        }
    }
}

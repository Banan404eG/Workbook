package tk.exdeath.controller.workbook;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;

@Controller
public class AnswersController {

    @PostMapping("/answers")
    public String answers(
            @RequestParam(required = false, value = "answers[]") String[] studentAnswers,
            @RequestParam() String lesson, @RequestParam() int grade, @RequestParam() int page, Model model) {


        //int studentID = LoggedStudent.getStudent().getStudentID();

        ArrayList<String> answers = new ArrayList<>(Arrays.asList(studentAnswers));

        answers.add(lesson);
        answers.add(String.valueOf(grade));
        answers.add(String.valueOf(page));

        model.addAttribute("Error", answers);
        return "errorPage";
    }
}

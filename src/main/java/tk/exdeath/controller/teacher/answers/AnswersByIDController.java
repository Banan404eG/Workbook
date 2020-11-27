package tk.exdeath.controller.teacher.answers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.model.teacher.answers.AnswersByID;

@Controller
public class AnswersByIDController {

    final String PATH = "workbook/page";
    final String INPUT = "<input name=\"marks[]\" placeholder=\"Оценка: \" type=\"text\"><br><br>";

    @GetMapping("/answersByID")
    public String taskByID(@RequestParam(defaultValue = "0") int studentID,
                           @RequestParam(defaultValue = "0") int id, Model model) {
        try {
            AnswersByID answersByID = new AnswersByID(studentID, id);
            model.addAttribute("id", id);
            model.addAttribute("studentID", studentID);
            model.addAttribute("studentAnswers", answersByID.getStudentAnswers());
            model.addAttribute("rightAnswers", answersByID.getRightAnswers());
            model.addAttribute("picture", answersByID.getPicture());
            model.addAttribute("inputs", answersByID.getInputs(INPUT));
            model.addAttribute("role", answersByID.getRole());
            model.addAttribute("marks", answersByID.getMarks());
            return PATH;
        } catch (RuntimeException ex) {
            model.addAttribute("Error", ex.getMessage());
            return "errorPage";
        }
    }
}

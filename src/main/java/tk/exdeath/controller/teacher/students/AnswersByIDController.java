package tk.exdeath.controller.teacher.students;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.model.teacher.students.AnswersByID;

@Controller
public class AnswersByIDController {

    final String PATH = "workbook/page";
    final String INPUT = "<input name=\"marks[]\" placeholder=\"Оценка: \" type=\"text\"><br><br>";

    @GetMapping("/answersByID")
    public String taskByID(@RequestParam(defaultValue = "0") int studentID,
                           @RequestParam(defaultValue = "0") int id, Model model) {

        AnswersByID answersByID = new AnswersByID(studentID, id);
        if (answersByID.incorrectInput()) {
            model.addAttribute("Error", "Некорректный ID ученика или ID задания");
            return "errorPage";
        }
        if (answersByID.taskDoesNotExist()) {
            model.addAttribute("Error", "Задания с таким ID не существует");
            return "errorPage";
        }
        if (answersByID.teacherIsLogged()) {
            model.addAttribute("role", "teacher");
            model.addAttribute("marks", answersByID.getMarks());
        } else {
            model.addAttribute("role", "watcher");
        }
        model.addAttribute("id", id);
        model.addAttribute("studentID", studentID);
        model.addAttribute("studentAnswers", answersByID.getStudentAnswers());
        model.addAttribute("rightAnswers", answersByID.getRightAnswers());
        model.addAttribute("picture", answersByID.getPicture());
        model.addAttribute("inputs", answersByID.getInputs(INPUT));
        return PATH;
    }
}

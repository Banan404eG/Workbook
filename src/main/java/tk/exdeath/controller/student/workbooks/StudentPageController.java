package tk.exdeath.controller.student.workbooks;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.model.logic.student.workbooks.StudentPage;

import javax.annotation.Resource;

@Controller
public class StudentPageController {

    final String PATH = "workbook/page";
    final String INPUT = "<input name=\"answers[]\" placeholder=\"Ответ: \" type=\"text\"><br><br>";

    @Resource(name = "getStudentPage")
    private StudentPage studentPage;

    @GetMapping("/page")
    public String returnPage(
            @RequestParam(defaultValue = "null") String lesson,
            @RequestParam(defaultValue = "-1") int grade,
            @RequestParam(defaultValue = "-1") int page, Model model) {
        try {
            studentPage.studentPage(lesson, grade, page);
            model.addAttribute("picture", studentPage.getPicture());
            model.addAttribute("inputs", studentPage.getInputs(INPUT));
            model.addAttribute("lesson", lesson);
            model.addAttribute("grade", grade);
            model.addAttribute("page", page);
            return PATH;
        } catch (RuntimeException ex) {
            model.addAttribute("Error", ex.getMessage());
            return "errorPage";
        }
    }
}

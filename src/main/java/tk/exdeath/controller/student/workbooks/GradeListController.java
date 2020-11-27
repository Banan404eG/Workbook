package tk.exdeath.controller.student.workbooks;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.model.student.workbooks.GradeList;

@Controller
public class GradeListController {

    final String PATH = "student/pages/gradeList";

    @GetMapping("/gradeList")
    public String getGrades(
            @RequestParam(defaultValue = "null") String lesson, Model model) {
        try {
            model.addAttribute("lesson", lesson);
            model.addAttribute("grades", GradeList.getGrades(lesson));
            return PATH;
        } catch (RuntimeException ex) {
            model.addAttribute("Error", ex.getMessage());
            return "errorPage";
        }
    }
}

package tk.exdeath.controller.student.pages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.model.service.PageService;

import java.util.Set;

@Controller
public class GradeList {

    final String PATH = "student/pages/gradeList";

    @GetMapping("/gradeList")
    public String getGrades(
            @RequestParam(defaultValue = "null") String lesson, Model model) {

        if (lessonIsInvalid(lesson)) {
            model.addAttribute("Error", "Параметры тетради неверны (предмет)");
            return "errorPage";
        }

        PageService pageService = new PageService();
        model.addAttribute("grades", pageService.readGrades(lesson));
        model.addAttribute("lesson", lesson);
        pageService.closeSession();
        return PATH;
    }


    private boolean lessonIsInvalid(String lesson) {
        return lesson.equals("null");
    }
}

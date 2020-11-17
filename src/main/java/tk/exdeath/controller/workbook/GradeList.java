package tk.exdeath.controller.workbook;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.model.service.PageService;

import java.util.Set;

@Controller
public class GradeList {

    @GetMapping("/gradeList")
    public String getLibrary(
            @RequestParam(defaultValue = "NULL") String lesson, Model model) {

        if (lessonIsInvalid(lesson)) {
            model.addAttribute("Error", "Параметры тетради неверны (предмет)");
            return "errorPage";
        }

        PageService pageService = new PageService();
        Set<Integer> grades = pageService.readGrades(lesson);

        model.addAttribute("grades", grades);
        pageService.closeSession();
        model.addAttribute("lesson", lesson);
        return "workbook/gradeList";
    }

    private boolean lessonIsInvalid(String lesson) {
        return lesson.equals("NULL");
    }
}

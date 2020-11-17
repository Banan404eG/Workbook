package tk.exdeath.controller.workbook;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import tk.exdeath.model.service.PageService;

import java.util.Set;

@Controller
public class LessonList {

    @GetMapping("/lessonList")
    public String getLibrary(Model model) {

        PageService pageService = new PageService();
        Set<String> lessons = pageService.readLessons();

        model.addAttribute("lessons", lessons);
        pageService.closeSession();
        return "workbook/lessonList";
    }
}

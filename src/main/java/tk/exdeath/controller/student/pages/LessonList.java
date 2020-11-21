package tk.exdeath.controller.student.pages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import tk.exdeath.model.service.PageService;

@Controller
public class LessonList {

    final String PATH = "student/pages/lessonList";

    @GetMapping("/lessonList")
    public String getLessons(Model model) {
        PageService pageService = new PageService();
        model.addAttribute("lessons", pageService.readLessons());
        pageService.closeSession();
        return PATH;
    }
}

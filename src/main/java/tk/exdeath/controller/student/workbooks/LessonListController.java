package tk.exdeath.controller.student.workbooks;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import tk.exdeath.model.logic.student.workbooks.LessonList;

import javax.annotation.Resource;

@Controller
public class LessonListController {

    final String PATH = "student/pages/lessonList";

    @Resource(name = "getLessonList")
    private LessonList lessonList;

    @GetMapping("/lessonList")
    public String getLessons(Model model) {
        model.addAttribute("lessons", lessonList.getLessons());
        return PATH;
    }
}

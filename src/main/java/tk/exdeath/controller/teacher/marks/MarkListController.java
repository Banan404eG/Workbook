package tk.exdeath.controller.teacher.marks;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import tk.exdeath.model.logic.teacher.marks.MarkList;

import javax.annotation.Resource;

@Controller
public class MarkListController {

    final String PATH = "teacher/marks/markList";

    @Resource(name = "getMarkList")
    private MarkList markList;

    @GetMapping("/markList")
    public String markList(Model model) {
        try {
            markList.markList();
            model.addAttribute("marks", markList.getMarks());
            model.addAttribute("students", markList.getStudentNames());
            model.addAttribute("studentIDs", markList.getStudentIDs());
            return PATH;
        } catch (RuntimeException ex) {
            model.addAttribute("Error", ex.getMessage());
            return "errorPage";
        }
    }
}

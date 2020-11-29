package tk.exdeath.controller.teacher.marks;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.model.logic.teacher.marks.AddMarks;

import javax.annotation.Resource;

@Controller
public class AddMarksController {

    @Resource(name = "getAddMarks")
    private AddMarks addMarks;

    @PostMapping("/marks")
    public String marks(
            @RequestParam(value = "marks[]") String[] marks,
            @RequestParam int id, @RequestParam int studentID, Model model) {
        try {
            addMarks.addMarks(id, studentID, marks);
            return "redirect:/answersByID?studentID=" + studentID + "&id=" + id;
        } catch (RuntimeException ex) {
            model.addAttribute("Error", ex.getMessage());
            return "errorPage";
        }
    }
}

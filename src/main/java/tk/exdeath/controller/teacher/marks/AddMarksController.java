package tk.exdeath.controller.teacher.marks;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.model.teacher.marks.AddMarks;

@Controller
public class AddMarksController {

    @PostMapping("/marks")
    public String marks(
            @RequestParam(required = false, value = "marks[]") String[] marks,
            @RequestParam int id, @RequestParam int studentID, Model model) {
        try {
            AddMarks.addMarks(id, studentID, marks);
            return "redirect:/answersByID?studentID=" + studentID + "&id=" + id;
        } catch (RuntimeException ex) {
            model.addAttribute("Error", ex.getMessage());
            return "errorPage";
        }
    }
}

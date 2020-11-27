package tk.exdeath.controller.teacher.account;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import tk.exdeath.model.teacher.account.AccountTeacher;

@Controller
public class AccountTeacherController {

    final String PATH = "teacher/account/accountTeacher";

    @GetMapping("/accountTeacher")
    public String returnPage(Model model) {
        try {
            model.addAttribute("Name", AccountTeacher.getTeacherName());
            return PATH;
        } catch (RuntimeException ex) {
            model.addAttribute("Error", ex.getMessage());
            return "errorPage";
        }
    }
}

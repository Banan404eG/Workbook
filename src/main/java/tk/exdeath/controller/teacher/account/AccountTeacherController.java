package tk.exdeath.controller.teacher.account;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import tk.exdeath.controller.teacher.LoggedTeacher;

@Controller
public class AccountTeacherController {

    final String PATH = "teacher/account/accountTeacher";

    @GetMapping("/accountTeacher")
    public String accountTeacher(Model model) {
        model.addAttribute("Name", LoggedTeacher.getTeacher().getFirstName());
        return PATH;
    }
}

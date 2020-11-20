package tk.exdeath.controller.teacher;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountTeacher {

    final String PATH = "teacher/accountTeacher";

    @GetMapping("/accountTeacher")
    public String returnPage(Model model) {
        model.addAttribute("Name", LoggedTeacher.getTeacher().getFirstName());
        return PATH;
    }
}

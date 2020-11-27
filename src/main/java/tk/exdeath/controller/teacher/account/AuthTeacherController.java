package tk.exdeath.controller.teacher.account;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static tk.exdeath.model.teacher.account.AuthTeacher.authTeacher;

@Controller
public class AuthTeacherController {

    final String PATH = "teacher/account/authTeacher";

    @GetMapping("/authTeacher")
    public String returnPage() {
        return PATH;
    }

    @PostMapping("/authTeacher")
    public String passCheck(
            @RequestParam String login, @RequestParam String password, Model model) {
        try {
            authTeacher(login, password);
            return "redirect:/accountTeacher";
        } catch (RuntimeException ex) {
            model.addAttribute("Error", ex.getMessage());
            return PATH;
        }
    }
}

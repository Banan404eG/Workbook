package tk.exdeath.controller.teacher.account;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.model.teacher.account.AuthTeacher;

@Controller
public class AuthTeacherController {

    final String PATH = "teacher/authTeacher";

    @GetMapping("/authTeacher")
    public String returnPage() {
        return PATH;
    }

    @PostMapping("/authTeacher")
    public String passCheck(
            @RequestParam String login, @RequestParam String password, Model model) {

        AuthTeacher authTeacher = new AuthTeacher(login, password);
        if (authTeacher.invalidLogin()) {
            model.addAttribute("Error", "Аккаунта с таким логином не существует");
            return PATH;
        }
        if (authTeacher.invalidPassword()) {
            model.addAttribute("Error", "Неверный пароль, попробуйте ещё раз");
            return PATH;
        }
        authTeacher.validationIsSuccessful();
        return "redirect:/accountTeacher";
    }
}

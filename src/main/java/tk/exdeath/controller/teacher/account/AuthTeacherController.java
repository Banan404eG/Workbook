package tk.exdeath.controller.teacher.account;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.controller.teacher.LoggedTeacher;
import tk.exdeath.model.Teacher;
import tk.exdeath.model.service.TeacherService;

@Controller
public class AuthTeacherController {

    final String PATH = "teacher/account/authTeacher";

    Teacher teacher;
    String login;
    Model model;

    @GetMapping("/authTeacher")
    public String auth() {
        return PATH;
    }

    @PostMapping("/authTeacher")
    public String passCheck(
            @RequestParam String login,
            @RequestParam String password, Model model) {

        this.login = login;
        this.model = model;
        TeacherService service = LoggedTeacher.getTeacherService();
        teacher = service.readByLogin(login);

        if (teacher.getLogin().equals("null")) {
            return invalidLogin();
        }

        if (teacher.getPassword().equals(password)) {
            return signIn();
        }

        return invalidPassword();
    }

    private String signIn() {
        LoggedTeacher.setLogin(login);
        LoggedTeacher.setTeacher(teacher);
        return "redirect:/accountTeacher";
    }

    private String invalidLogin() {
        model.addAttribute("Error", "Аккаунта с таким логином не существует, вы можете создать его нажав на одноименную ссылку");
        return PATH;
    }

    private String invalidPassword() {
        model.addAttribute("Error", "Неверный пароль, попробуйте ещё раз");
        return PATH;
    }
}

package tk.exdeath.controller.teacher;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.model.Teacher;
import tk.exdeath.model.service.TeacherService;

@Controller
public class AuthTeacher {

    final String PATH = "teacher/authTeacher";

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

        if (teacherDoesNotExist()) {
            return invalidLogin();
        }

        if (passwordIsNotCorrect(password)) {
            return invalidPassword();
        }

        return signIn();
    }


    private boolean teacherDoesNotExist() {
        return teacher.getLogin().equals("null");
    }

    private String invalidLogin() {
        model.addAttribute("Error", "Аккаунта с таким логином не существует");
        return PATH;
    }

    private boolean passwordIsNotCorrect(String password) {
        return !teacher.getPassword().equals(password);
    }

    private String invalidPassword() {
        model.addAttribute("Error", "Неверный пароль, попробуйте ещё раз");
        return PATH;
    }

    private String signIn() {
        LoggedTeacher.setLogin(login);
        LoggedTeacher.setTeacher(teacher);
        return "redirect:/accountTeacher";
    }
}

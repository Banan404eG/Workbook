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

    @GetMapping("/authTeacher")
    public String auth() {
        return PATH;
    }

    @PostMapping("/authTeacher")
    public String passCheck(
            @RequestParam String login,
            @RequestParam String password, Model model) {

        TeacherService service = LoggedTeacher.getTeacherService();
        Teacher teacher = service.readByLogin(login);

        if (teacherDoesNotExist(teacher)) {
            model.addAttribute("Error", "Аккаунта с таким логином не существует");
            return PATH;
        }
        if (passwordIsNotCorrect(teacher, password)) {
            model.addAttribute("Error", "Неверный пароль, попробуйте ещё раз");
            return PATH;
        }
        return signIn(teacher, login);
    }


    private boolean teacherDoesNotExist(Teacher teacher) {
        return teacher.getLogin().equals("null");
    }

    private boolean passwordIsNotCorrect(Teacher teacher, String password) {
        return !teacher.getPassword().equals(password);
    }

    private String signIn(Teacher teacher, String login) {
        LoggedTeacher.setTeacher(teacher);
        LoggedTeacher.setLogin(login);
        return "redirect:/accountTeacher";
    }
}

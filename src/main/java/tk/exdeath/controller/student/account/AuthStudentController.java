package tk.exdeath.controller.student.account;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.controller.student.LoggedStudent;
import tk.exdeath.model.Student;
import tk.exdeath.model.service.StudentService;

@Controller
public class AuthStudentController {

    final String PATH = "student/account/authStudent";

    Student student;
    String login;
    Model model;

    @GetMapping("/authStudent")
    public String auth() {
        return PATH;
    }

    @PostMapping("/authStudent")
    public String passCheck(
            @RequestParam String login,
            @RequestParam String password, Model model) {

        this.login = login;
        this.model = model;
        StudentService service = LoggedStudent.getStudentService();
        student = service.readByLogin(login);

        if (student.getLogin().equals("null")) {
            return invalidLogin();
        }

        if (student.getPassword().equals(password)) {
            return signIn();
        }

        return invalidPassword();
    }

    private String signIn() {
        LoggedStudent.setLogin(login);
        LoggedStudent.setStudent(student);
        return "redirect:/accountStudent";
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

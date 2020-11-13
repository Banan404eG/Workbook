package tk.exdeath.controller.student;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.model.Student;
import tk.exdeath.model.service.StudentService;

@Controller
public class AuthStudent {

    final String PATH = "student/authStudent";

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

        if (studentDoesNotExist()) {
            return invalidLogin();
        }

        if (passwordIsNotCorrect(password)) {
            return invalidPassword();
        }

        return signIn();
    }


    private boolean studentDoesNotExist() {
        return student.getLogin().equals("null");
    }

    private String invalidLogin() {
        model.addAttribute("Error", "Аккаунта с таким логином не существует");
        return PATH;
    }

    private boolean passwordIsNotCorrect(String password) {
        return !student.getPassword().equals(password);
    }

    private String invalidPassword() {
        model.addAttribute("Error", "Неверный пароль, попробуйте ещё раз");
        return PATH;
    }

    private String signIn() {
        LoggedStudent.setLogin(login);
        LoggedStudent.setStudent(student);
        return "redirect:/accountStudent";
    }
}

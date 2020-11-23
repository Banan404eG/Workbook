package tk.exdeath.controller.student.account;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.model.student.account.AuthStudent;

@Controller
public class AuthStudentController {

    final String PATH = "student/account/authStudent";

    @GetMapping("/authStudent")
    public String returnPage() {
        return PATH;
    }

    @PostMapping("/authStudent")
    public String passCheck(
            @RequestParam String login, @RequestParam String password, Model model) {

        AuthStudent authStudent = new AuthStudent(login, password);
        if (authStudent.invalidLogin()) {
            model.addAttribute("Error", "Аккаунта с таким логином не существует");
            return PATH;
        }
        if (authStudent.invalidPassword()) {
            model.addAttribute("Error", "Неверный пароль, попробуйте ещё раз");
            return PATH;
        }
        authStudent.validationIsSuccessful();
        return "redirect:/accountStudent";
    }
}

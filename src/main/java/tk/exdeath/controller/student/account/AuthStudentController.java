package tk.exdeath.controller.student.account;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.model.logic.student.account.AuthStudent;

import javax.annotation.Resource;

@Controller
public class AuthStudentController {

    final String PATH = "student/account/authStudent";

    @Resource(name = "getAuthStudent")
    private AuthStudent authStudent;

    @GetMapping("/authStudent")
    public String returnPage() {
        return PATH;
    }

    @PostMapping("/authStudent")
    public String passCheck(
            @RequestParam String login, @RequestParam String password, Model model) {
        try {
            authStudent.authStudent(login, password);
            return "redirect:/accountStudent";
        } catch (RuntimeException ex) {
            model.addAttribute("Error", ex.getMessage());
            return PATH;
        }
    }
}

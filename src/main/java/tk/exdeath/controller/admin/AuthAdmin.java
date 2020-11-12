package tk.exdeath.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthAdmin {

    final String PATH = "admin/authAdmin";
    final String KEY = "O0O-StArTuP-C0MP7NY";
    final String LOGIN = "1";
    final String PASSWORD = "1";

    @GetMapping("/authAdmin")
    public String auth(
            @RequestParam(defaultValue = "ERROR") String key) {

        if (key.equals(KEY)) {
            return PATH;
        }

        return "errorPage";
    }

    @PostMapping("/authAdmin")
    public String passCheck(
            @RequestParam String login,
            @RequestParam String password, Model model) {

        if (login.equals(LOGIN) && password.equals(PASSWORD)) {
            LoggedAdmin.LogIn();
            return "redirect:/accountAdmin";
        }

        model.addAttribute("Error", "Неверные логин или пароль");
        return PATH;
    }
}

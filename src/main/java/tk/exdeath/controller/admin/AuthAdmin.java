package tk.exdeath.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthAdmin {

    final String PATH = "admin/authAdmin";

    @GetMapping("/authAdmin")
    public String returnPage(
            @RequestParam(defaultValue = "ERROR") String key) {

        if (LoggedAdmin.isKeyValid(key)) {
            return PATH;
        }
        return "errorPage";
    }

    @PostMapping("/authAdmin")
    public String authAdmin(
            @RequestParam String login,
            @RequestParam String password, Model model) {

        if (LoggedAdmin.areLoginAndPasswordValid(login, password)) {
            return "redirect:/accountAdmin";
        }
        model.addAttribute("Error", "Неверные логин или пароль");
        return PATH;
    }
}

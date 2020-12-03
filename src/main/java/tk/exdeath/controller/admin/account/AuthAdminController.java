package tk.exdeath.controller.admin.account;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.model.logic.admin.account.AuthAdmin;
import tk.exdeath.model.logic.admin.account.LoggedAdmin;

import javax.annotation.Resource;

@Controller
public class AuthAdminController {

    final String PATH = "admin/account/authAdmin";

    @Resource(name = "getAuthAdmin")
    private AuthAdmin authAdmin;

    @GetMapping("/authAdmin")
    public String returnPage(
            @RequestParam(defaultValue = "ERROR") String key, Model model) {
        try {
            authAdmin.keyCheck(key);
            return PATH;
        } catch (RuntimeException ex) {
            model.addAttribute("Error", ex.getMessage());
            return "errorPage";
        }
    }

    @PostMapping("/authAdmin")
    public String authAdmin(
            @RequestParam String login, @RequestParam String password, Model model) {
        try {
            authAdmin.loginAndPasswordCheck(login, password);
            return "redirect:/accountAdmin";
        } catch (RuntimeException ex) {
            model.addAttribute("Message", ex.getMessage());
            return PATH;
        }
    }
}

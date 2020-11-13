package tk.exdeath.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountAdmin {

    final String PATH = "admin/accountAdmin";

    @GetMapping("/accountAdmin")
    public String account(Model model) {

        if (LoggedAdmin.isLogged()) {
            model.addAttribute("key", LoggedAdmin.KEY);
            return PATH;
        }

        return "errorPage";
    }
}

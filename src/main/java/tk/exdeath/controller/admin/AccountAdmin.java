package tk.exdeath.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountAdmin {

    final String PATH = "admin/accountAdmin";

    @GetMapping("/accountAdmin")
    public String account() {

        if (LoggedAdmin.isLogged()) {
            LoggedAdmin.LogOut();
            return PATH;
        }

        return "errorPage";
    }
}

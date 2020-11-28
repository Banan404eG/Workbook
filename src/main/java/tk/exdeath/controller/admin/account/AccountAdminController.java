package tk.exdeath.controller.admin.account;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import tk.exdeath.model.admin.account.AccountAdmin;

@Controller
public class AccountAdminController {

    final String PATH = "admin/account/accountAdmin";

    @GetMapping("/accountAdmin")
    public String returnPage(Model model) {
        try {
            model.addAttribute("key", AccountAdmin.getKey());
            return PATH;
        } catch (RuntimeException ex) {
            model.addAttribute("Error", ex.getMessage());
            return "errorPage";
        }
    }
}

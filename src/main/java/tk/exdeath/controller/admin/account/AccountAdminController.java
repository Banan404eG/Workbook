package tk.exdeath.controller.admin.account;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import tk.exdeath.model.logic.admin.account.AccountAdmin;

import javax.annotation.Resource;

@Controller
public class AccountAdminController {

    final String PATH = "admin/account/accountAdmin";

    @Resource(name = "getAccountAdmin")
    private AccountAdmin accountAdmin;

    @GetMapping("/accountAdmin")
    public String returnPage(Model model) {
        try {
            accountAdmin.validationCheck();
            return PATH;
        } catch (RuntimeException ex) {
            model.addAttribute("Error", ex.getMessage());
            return "errorPage";
        }
    }
}

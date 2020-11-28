package tk.exdeath.controller.student.account;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import tk.exdeath.model.student.account.AccountStudent;

import javax.annotation.Resource;

@Controller
public class AccountStudentController {

    final String PATH = "student/account/accountStudent";

    @Resource(name = "getAccountStudent")
    private AccountStudent accountStudent;

    @GetMapping("/accountStudent")
    public String returnPage(Model model) {
        try {
            model.addAttribute("Name", accountStudent.getStudentName());
            return PATH;
        } catch (RuntimeException ex) {
            model.addAttribute("Error", ex.getMessage());
            return "errorPage";
        }
    }
}

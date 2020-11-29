package tk.exdeath.controller.teacher.account;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import tk.exdeath.model.logic.teacher.account.AccountTeacher;

import javax.annotation.Resource;

@Controller
public class AccountTeacherController {

    final String PATH = "teacher/account/accountTeacher";

    @Resource(name = "getAccountTeacher")
    private AccountTeacher accountTeacher;

    @GetMapping("/accountTeacher")
    public String returnPage(Model model) {
        try {
            model.addAttribute("Name", accountTeacher.getTeacherName());
            return PATH;
        } catch (RuntimeException ex) {
            model.addAttribute("Error", ex.getMessage());
            return "errorPage";
        }
    }
}

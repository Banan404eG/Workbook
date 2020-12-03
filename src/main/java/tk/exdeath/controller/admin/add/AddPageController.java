package tk.exdeath.controller.admin.add;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.model.logic.admin.add.AddPage;

import javax.annotation.Resource;

@Controller
public class AddPageController {

    final String PATH = "admin/add/addPage";

    @Resource(name = "getAddPage")
    private AddPage addPage;

    @GetMapping("/addPage")
    public String returnPage(Model model) {
        try {
            addPage.validationCheck();
            return PATH;
        } catch (RuntimeException ex) {
            model.addAttribute("Error", ex.getMessage());
            return "errorPage";
        }
    }

    @PostMapping("/addPage")
    public String addPage(
            @RequestParam(defaultValue = "null") String lesson,
            @RequestParam(defaultValue = "0") int grade,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "0") int numberOfInputs,
            @RequestParam(defaultValue = "null") String pictureURL, Model model) {
        try {
            addPage.addPage(lesson, grade, page, numberOfInputs, pictureURL);
            model.addAttribute("Message", lesson + " " + grade + " " + page + " успешно создан");
            return PATH;
        } catch (RuntimeException ex) {
            model.addAttribute("Message", ex.getMessage());
            return PATH;
        }
    }
}

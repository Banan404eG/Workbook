package tk.exdeath.controller.workbooks.Informatics.grade7.page8;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.controller.student.LoggedStudent;
import tk.exdeath.model.Task;
import tk.exdeath.model.service.WorkbookService;
import tk.exdeath.model.workbooks.Informatics.grade7.page8.Informatics78;

@Controller
public class Informatics78Controller {

    private final String TABLE_NAME = "inf7_8";

    @PostMapping("/informatics78")
    public String informatics78(
            @RequestParam String three1,
            @RequestParam String three2,
            @RequestParam String four11,
            @RequestParam String four12,
            @RequestParam String four21,
            @RequestParam String four22,
            @RequestParam String four31,
            @RequestParam String four32,
            @RequestParam String four41,
            @RequestParam String four42,
            @RequestParam String four51,
            @RequestParam String four52,
            @RequestParam String four61,
            @RequestParam String four62,
            @RequestParam String four71,
            @RequestParam String four72,
            @RequestParam String four81,
            @RequestParam String four82,
            @RequestParam String four91,
            @RequestParam String four92, Model model) {

        int studentID = LoggedStudent.getStudent().getStudentID();
        Informatics78 workbook = new Informatics78(three1, three2, four11, four12, four21, four22, four31, four32, four41, four42, four51, four52, four61, four62, four71, four72, four81, four82, four91, four92, studentID);
        WorkbookService<Informatics78> workbookService = new WorkbookService<>();
        workbookService.create(workbook);

        LoggedStudent.getStudent().addTask(new Task(LoggedStudent.getStudent(), workbook.getId(), TABLE_NAME));
        LoggedStudent.update();

        model.addAttribute("Error", workbook);
        return "errorPage";
    }
}

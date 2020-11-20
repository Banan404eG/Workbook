package tk.exdeath.controller.student.answers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.model.Page;
import tk.exdeath.model.service.PageService;

@Controller
public class PageWithInputs {

    final String PATH = "workbook/page";
    final String INPUT = "<input name=\"answers[]\" placeholder=\"Ответ: \" type=\"text\"><br><br>";

    @GetMapping("/page")
    public String returnPage(
            @RequestParam(defaultValue = "null") String lesson,
            @RequestParam(defaultValue = "-1") int grade,
            @RequestParam(defaultValue = "-1") int page, Model model) {

        if (invalidParams(lesson, grade, page)) {
            model.addAttribute("Error", "Параметры неверны (класс, предмет или страница)");
            return "errorPage";
        }

        PageService pageService = new PageService();
        Page pageEntity = pageService.read(lesson, grade, page);
        if (pageDoesNotExist(pageEntity)) {
            model.addAttribute("Error", "Такой страницы не существует");
            return "errorPage";
        }

        model.addAttribute("picture", pageEntity.getPicture());
        model.addAttribute("inputs", getInputs(pageEntity.getNumberOfInputs()));
        model.addAttribute("lesson", lesson);
        model.addAttribute("grade", grade);
        model.addAttribute("page", page);
        pageService.closeSession();
        return PATH;
    }


    private boolean invalidParams(String lesson, int grade, int page) {
        return lesson.equals("null") || grade == -1 || page == -1;
    }

    private boolean pageDoesNotExist(Page page) {
        return page.getLesson().equals("null");
    }

    private String getInputs(int numberOfInputs) {
        StringBuilder inputs = new StringBuilder();

        for (int i = 0; i < numberOfInputs; i++) {
            inputs.append(INPUT);
        }

        return inputs.toString();
    }
}

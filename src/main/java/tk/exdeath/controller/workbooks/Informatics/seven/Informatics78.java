package tk.exdeath.controller.workbooks.Informatics.seven;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class Informatics78 {

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

        ArrayList<String> answers = new ArrayList<>();

        answers.add(three1);
        answers.add(three2);
        answers.add(four11);
        answers.add(four12);
        answers.add(four21);
        answers.add(four22);
        answers.add(four31);
        answers.add(four32);
        answers.add(four41);
        answers.add(four42);
        answers.add(four51);
        answers.add(four52);
        answers.add(four61);
        answers.add(four62);
        answers.add(four71);
        answers.add(four72);
        answers.add(four81);
        answers.add(four82);
        answers.add(four91);
        answers.add(four92);

        model.addAttribute("Error", answers);
        return "errorPage";
    }
}

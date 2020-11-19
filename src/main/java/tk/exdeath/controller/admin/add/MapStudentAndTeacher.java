package tk.exdeath.controller.admin.add;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.controller.admin.LoggedAdmin;
import tk.exdeath.model.Student;
import tk.exdeath.model.Teacher;
import tk.exdeath.model.service.StudentService;
import tk.exdeath.model.service.TeacherService;

@Controller
public class MapStudentAndTeacher {

    final String PATH = "admin/add/mapStudentAndTeacher";

    @GetMapping("/mapStudentAndTeacher")
    public String returnPage(
            @RequestParam(defaultValue = "ERROR") String key) {

        if (LoggedAdmin.isKeyValid(key)) {
            return PATH;
        }
        return "errorPage";
    }

    @PostMapping("/mapStudentAndTeacher")
    public String mapStudentAndTeacher(
            @RequestParam(defaultValue = "null") String studentLogin,
            @RequestParam(defaultValue = "null") String teacherLogin, Model model) {

        if (incorrectInput(studentLogin, teacherLogin)) {
            model.addAttribute("Message", "Некорректный ввод");
            return PATH;
        }

        StudentService studentService = new StudentService();
        Student student = studentService.readByLogin(studentLogin);
        if (loginIsInvalid(student.getLogin())) {
            model.addAttribute("Message", "Ученика с таким логином не существует");
            return PATH;
        }
        studentService.closeSession();

        TeacherService teacherService = new TeacherService();
        Teacher teacher = teacherService.readByLogin(teacherLogin);
        if (loginIsInvalid(teacher.getLogin())) {
            model.addAttribute("Message", "Учителя с таким логином не существует");
            return PATH;
        }

        teacher.addStudent(student);
        teacherService.update(teacher);
        teacherService.closeSession();
        model.addAttribute("Message", studentLogin + " успешно добавлен к " + teacherLogin);
        return PATH;
    }


    private boolean incorrectInput(String studentLogin, String teacherLogin) {
        return studentLogin.equals("null") || teacherLogin.equals("null");
    }

    private boolean loginIsInvalid(String login) {
        return login.equals("null");
    }
}

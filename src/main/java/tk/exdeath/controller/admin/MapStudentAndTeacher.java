package tk.exdeath.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.model.Student;
import tk.exdeath.model.Teacher;
import tk.exdeath.model.service.StudentService;
import tk.exdeath.model.service.TeacherService;

@Controller
public class MapStudentAndTeacher {

    final String PATH = "admin/mapStudentAndTeacher";



    @GetMapping("/mapStudentAndTeacher")
    public String returnPage(
            @RequestParam(defaultValue = "ERROR") String key, Model model) {

        if (LoggedAdmin.isKeyValid(key)) {
            return PATH;
        }

        return "errorPage";
    }

    @PostMapping("/mapStudentAndTeacher")
    public String mapStudentAndTeacher(
            @RequestParam String studentLogin,
            @RequestParam String teacherLogin, Model model) {


        StudentService studentService = new StudentService();
        Student student = studentService.readByLogin(studentLogin);

        if (loginIsInvalid(student)) {
            model.addAttribute("Message", "Ученика с таким логином не существует");
            return PATH;
        }
        studentService.closeSession();

        TeacherService teacherService = new TeacherService();
        Teacher teacher = teacherService.readByLogin(teacherLogin);

        if (loginIsInvalid(teacher)) {
            model.addAttribute("Message", "Учителя с таким логином не существует");
            return PATH;
        }

        teacher.addStudent(student);
        teacherService.update(teacher);

        model.addAttribute("Message", studentLogin + " успешно добавлен к " + teacherLogin);
        return PATH;
    }

    private boolean loginIsInvalid(Teacher teacher) {
        return teacher.getLogin().equals("null");
    }

    private boolean loginIsInvalid(Student student) {
        return student.getLogin().equals("null");
    }
}

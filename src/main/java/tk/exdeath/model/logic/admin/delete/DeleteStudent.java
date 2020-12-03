package tk.exdeath.model.logic.admin.delete;

import tk.exdeath.model.logic.admin.account.LoggedAdmin;
import tk.exdeath.model.database.entities.Student;
import tk.exdeath.model.database.service.StudentService;

import javax.annotation.Resource;

public class DeleteStudent {

    @Resource(name = "getLoggedAdmin")
    private LoggedAdmin loggedAdmin;

    public void validationCheck() {
        loggedAdmin.validationCheck();
    }

    public void deleteStudent(String login) {
        if (incorrectInput(login)) {
            throw new RuntimeException("Некорректный ввод");
        }
        StudentService studentService = new StudentService();
        Student student = studentService.readByLogin(login);
        if (loginIsInvalid(student)) {
            studentService.closeSession();
            throw new RuntimeException("Ученика с таким логином не существует");
        }
        studentService.delete(student);
        studentService.closeSession();
    }


    private boolean incorrectInput(String login) {
        return login.equals("null");
    }

    private boolean loginIsInvalid(Student student) {
        return student.getLogin().equals("null");
    }
}

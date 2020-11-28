package tk.exdeath.model.student.account;

import tk.exdeath.model.database.entities.Student;
import tk.exdeath.model.database.service.StudentService;

import javax.annotation.Resource;

public class AuthStudent {

    @Resource(name = "getLoggedStudent")
    private LoggedStudent loggedStudent;

    public void authStudent(String login, String password) {
        StudentService service = loggedStudent.getStudentService();
        Student student = service.readByLogin(login);
        if (invalidLogin(student)) {
            throw new RuntimeException("Аккаунта с таким логином не существует");
        }
        if (invalidPassword(student, password)) {
            throw new RuntimeException("Неверный пароль, попробуйте ещё раз");
        }
        loggedStudent.setLogin(login);
        loggedStudent.setStudent(student);
    }


    private boolean invalidLogin(Student student) {
        return student.getLogin().equals("null");
    }

    private boolean invalidPassword(Student student, String password) {
        return !student.getPassword().equals(password);
    }
}

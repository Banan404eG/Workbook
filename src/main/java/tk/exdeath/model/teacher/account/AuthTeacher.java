package tk.exdeath.model.teacher.account;

import tk.exdeath.model.database.entities.Teacher;
import tk.exdeath.model.database.service.TeacherService;

public class AuthTeacher {

    Teacher teacher;
    String login, password;

    public AuthTeacher(String login, String password) {
        TeacherService service = LoggedTeacher.getTeacherService();
        teacher = service.readByLogin(login);
        this.login = login;
        this.password = password;
    }

    public boolean invalidLogin() {
        return teacher.getLogin().equals("null");
    }

    public boolean invalidPassword() {
        return !teacher.getPassword().equals(password);
    }

    public void validationIsSuccessful() {
        LoggedTeacher.setLogin(login);
        LoggedTeacher.setTeacher(teacher);
    }
}

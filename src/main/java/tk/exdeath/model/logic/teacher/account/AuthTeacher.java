package tk.exdeath.model.logic.teacher.account;

import tk.exdeath.model.database.entities.Teacher;
import tk.exdeath.model.database.service.TeacherService;

import javax.annotation.Resource;

public class AuthTeacher {

    @Resource(name = "getLoggedTeacher")
    private LoggedTeacher loggedTeacher;

    public void authTeacher(String login, String password) {
        TeacherService service = loggedTeacher.getTeacherService();
        Teacher teacher = service.readByLogin(login);
        if (invalidLogin(teacher)) {
            throw new RuntimeException("Аккаунта с таким логином не существует");
        }
        if (invalidPassword(teacher, password)) {
            throw new RuntimeException("Неверный пароль, попробуйте ещё раз");
        }
        loggedTeacher.setLogin(login);
        loggedTeacher.setTeacher(teacher);
    }


    private boolean invalidLogin(Teacher teacher) {
        return teacher.getLogin().equals("null");
    }

    private boolean invalidPassword(Teacher teacher, String password) {
        return !teacher.getPassword().equals(password);
    }
}

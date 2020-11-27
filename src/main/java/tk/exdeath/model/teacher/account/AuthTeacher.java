package tk.exdeath.model.teacher.account;

import tk.exdeath.model.database.entities.Teacher;
import tk.exdeath.model.database.service.TeacherService;

public abstract class AuthTeacher {

    public static void authTeacher(String login, String password) {
        TeacherService service = LoggedTeacher.getTeacherService();
        Teacher teacher = service.readByLogin(login);
        if (invalidLogin(teacher)) {
            throw new RuntimeException("Аккаунта с таким логином не существует");
        }
        if (invalidPassword(teacher, password)) {
            throw new RuntimeException("Неверный пароль, попробуйте ещё раз");
        }
        LoggedTeacher.setLogin(login);
        LoggedTeacher.setTeacher(teacher);
    }


    private static boolean invalidLogin(Teacher teacher) {
        return teacher.getLogin().equals("null");
    }

    private static boolean invalidPassword(Teacher teacher, String password) {
        return !teacher.getPassword().equals(password);
    }
}

package tk.exdeath.model.admin.add;

import tk.exdeath.model.admin.account.LoggedAdmin;
import tk.exdeath.model.database.entities.Teacher;
import tk.exdeath.model.database.service.TeacherService;

public abstract class AddTeacher {

    public static void keyCheck(String key) {
        LoggedAdmin.keyCheck(key);
    }

    public static void addTeacher(String login, String password, String firstName, String secondName) {
        if (incorrectInput(login, password, firstName, secondName)) {
            throw new RuntimeException("Некорректный ввод");
        }
        TeacherService teacherService = new TeacherService();
        if (loginIsInvalid(login, teacherService)) {
            teacherService.closeSession();
            throw new RuntimeException("Учитель с таким логином уже существует");
        }
        teacherService.create(new Teacher(login, password, firstName, secondName));
        teacherService.closeSession();
    }


    private static boolean incorrectInput(String login, String password, String firstName, String secondName) {
        return login.equals("null") || password.equals("null") || firstName.equals("null") || secondName.equals("null");
    }

    private static boolean loginIsInvalid(String login, TeacherService teacherService) {
        return !teacherService.readByLogin(login).getLogin().equals("null");
    }
}

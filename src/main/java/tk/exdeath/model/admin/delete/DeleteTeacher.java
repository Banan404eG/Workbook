package tk.exdeath.model.admin.delete;

import tk.exdeath.model.admin.account.LoggedAdmin;
import tk.exdeath.model.database.entities.Teacher;
import tk.exdeath.model.database.service.TeacherService;

public abstract class DeleteTeacher {

    public static void keyCheck(String key) {
        LoggedAdmin.keyCheck(key);
    }

    public static void deleteTeacher(String login) {
        if (incorrectInput(login)) {
            throw new RuntimeException("Некорректный ввод");
        }
        TeacherService teacherService = new TeacherService();
        Teacher teacher = teacherService.readByLogin(login);
        if (loginIsInvalid(teacher)) {
            teacherService.closeSession();
            throw new RuntimeException("Учителя с таким логином не существует");
        }
        teacherService.delete(teacher);
        teacherService.closeSession();
    }


    private static boolean incorrectInput(String login) {
        return login.equals("null");
    }

    private static boolean loginIsInvalid(Teacher teacher) {
        return teacher.getLogin().equals("null");
    }
}

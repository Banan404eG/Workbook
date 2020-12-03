package tk.exdeath.model.logic.admin.delete;

import tk.exdeath.model.database.entities.Teacher;
import tk.exdeath.model.database.service.TeacherService;
import tk.exdeath.model.logic.admin.account.LoggedAdmin;

import javax.annotation.Resource;

public class DeleteTeacher {

    @Resource(name = "getLoggedAdmin")
    private LoggedAdmin loggedAdmin;

    public void validationCheck() {
        loggedAdmin.validationCheck();
    }

    public void deleteTeacher(String login) {
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


    private boolean incorrectInput(String login) {
        return login.equals("null");
    }

    private boolean loginIsInvalid(Teacher teacher) {
        return teacher.getLogin().equals("null");
    }
}

package tk.exdeath.model.logic.admin.add;

import tk.exdeath.model.logic.admin.account.LoggedAdmin;
import tk.exdeath.model.database.entities.Teacher;
import tk.exdeath.model.database.service.TeacherService;

import javax.annotation.Resource;

public class AddTeacher {

    @Resource(name = "getLoggedAdmin")
    private LoggedAdmin loggedAdmin;

    public void validationCheck() {
        loggedAdmin.validationCheck();
    }

    public void addTeacher(String login, String password, String firstName, String secondName) {
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


    private boolean incorrectInput(String login, String password, String firstName, String secondName) {
        return login.equals("null") || password.equals("null") || firstName.equals("null") || secondName.equals("null");
    }

    private boolean loginIsInvalid(String login, TeacherService teacherService) {
        return !teacherService.readByLogin(login).getLogin().equals("null");
    }
}

package tk.exdeath.model.logic.teacher.account;

import javax.annotation.Resource;

public class AccountTeacher {

    @Resource(name = "getLoggedTeacher")
    private LoggedTeacher loggedTeacher;

    public String getTeacherName() {
        if (loggedTeacher.getTeacher() == null) {
            throw new RuntimeException("Для использования аккаунта, вы должны пройти аутентификацию");
        }
        return loggedTeacher.getTeacher().getFirstName();
    }
}

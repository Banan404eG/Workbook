package tk.exdeath.model.student.account;

import javax.annotation.Resource;

public class AccountStudent {

    @Resource(name = "getLoggedStudent")
    private LoggedStudent loggedStudent;

    public String getStudentName() {
        if (loggedStudent.getStudent() == null) {
            throw new RuntimeException("Для использования аккаунта, вы должны пройти аутентификацию");
        }
        return loggedStudent.getStudent().getFirstName();
    }
}

package tk.exdeath.model.student.account;

public abstract class AccountStudent {

    public static String getStudentName() {
        if (LoggedStudent.getStudent() == null) {
            throw new RuntimeException("Для использования аккаунта, вы должны пройти аутентификацию");
        }
        return LoggedStudent.getStudent().getFirstName();
    }
}

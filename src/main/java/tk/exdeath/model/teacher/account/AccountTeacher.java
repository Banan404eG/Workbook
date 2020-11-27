package tk.exdeath.model.teacher.account;

public abstract class AccountTeacher {

    public static String getTeacherName() {
        if (LoggedTeacher.getTeacher() == null) {
            throw new RuntimeException("Для использования аккаунта, вы должны пройти аутентификацию");
        }
        return LoggedTeacher.getTeacher().getFirstName();
    }
}

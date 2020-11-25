package tk.exdeath.model.teacher.account;

public abstract class AccountTeacher {

    public static String getTeacherName() {
        return LoggedTeacher.getTeacher().getFirstName();
    }
}

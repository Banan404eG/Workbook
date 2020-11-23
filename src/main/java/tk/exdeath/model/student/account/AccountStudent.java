package tk.exdeath.model.student.account;

public abstract class AccountStudent {

    public static String getStudentName() {
        return LoggedStudent.getStudent().getFirstName();
    }
}

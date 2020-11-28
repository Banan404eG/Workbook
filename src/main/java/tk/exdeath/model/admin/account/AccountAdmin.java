package tk.exdeath.model.admin.account;

public abstract class AccountAdmin {

    public static String getKey() {
        if (!LoggedAdmin.isLogged()) {
            throw new RuntimeException("Oops");
        }
        return LoggedAdmin.KEY;
    }
}

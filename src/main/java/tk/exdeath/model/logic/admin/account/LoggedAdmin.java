package tk.exdeath.model.logic.admin.account;

public abstract class LoggedAdmin {

    static final String KEY = "O0O-StArTuP-C0MP7NY";
    static final String LOGIN = "1";
    static final String PASSWORD = "1";

    private static boolean isLogged = false;

    public static void keyCheck(String key) {
        if (!key.equals(LoggedAdmin.KEY)) {
            throw new RuntimeException("Oops");
        }
    }

    public static boolean areLoginAndPasswordValid(String login, String password) {
        if (login.equals(LOGIN) && password.equals(PASSWORD)) {
            isLogged = true;
            return true;
        }
        return false;
    }

    public static boolean isLogged() {
        boolean isLogged = LoggedAdmin.isLogged;
        LoggedAdmin.isLogged = false;
        return isLogged;
    }
}

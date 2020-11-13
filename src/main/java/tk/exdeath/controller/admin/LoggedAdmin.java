package tk.exdeath.controller.admin;

public abstract class LoggedAdmin {

    static final String KEY = "O0O-StArTuP-C0MP7NY";
    static final String LOGIN = "1";
    static final String PASSWORD = "1";

    private static boolean isLogged = false;

    public static boolean isKeyValid(String key) {
        return key.equals(LoggedAdmin.KEY);
    }

    public static boolean isLoginAndPasswordValid(String login, String password) {

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

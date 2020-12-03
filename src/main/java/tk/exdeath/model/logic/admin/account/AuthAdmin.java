package tk.exdeath.model.logic.admin.account;

import javax.annotation.Resource;

public class AuthAdmin {
    final String KEY = "O0O-StArTuP-C0MP7NY";
    final String LOGIN = "1";
    final String PASSWORD = "1";

    @Resource(name = "getLoggedAdmin")
    private LoggedAdmin loggedAdmin;

    public void keyCheck(String key) {
        if (!key.equals(KEY)) {
            throw new RuntimeException("Oops");
        }
    }

    public void loginAndPasswordCheck(String login, String password) {
        if (login.equals(LOGIN) && password.equals(PASSWORD)) {
            loggedAdmin.logIn();
            return;
        }
        throw new RuntimeException("Неверные логин или пароль");
    }
}

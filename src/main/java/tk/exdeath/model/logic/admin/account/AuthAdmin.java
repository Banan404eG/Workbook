package tk.exdeath.model.logic.admin.account;

public abstract class AuthAdmin {

    public static void keyCheck(String key) {
        LoggedAdmin.keyCheck(key);
    }

    public static void loginAndPasswordCheck(String login, String password) {
        if (!LoggedAdmin.areLoginAndPasswordValid(login, password)) {
            throw new RuntimeException("Неверные логин или пароль");
        }
    }
}

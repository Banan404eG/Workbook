package tk.exdeath.controller.admin;

public abstract class LoggedAdmin {

    private static boolean isLogged = false;

    public static boolean isLogged() {
        return isLogged;
    }

    public static void LogIn() {
        isLogged = true;
    }

    public static void LogOut() {
        isLogged = false;
    }
}

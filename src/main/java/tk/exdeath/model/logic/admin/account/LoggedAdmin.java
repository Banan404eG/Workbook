package tk.exdeath.model.logic.admin.account;

public class LoggedAdmin {

    private boolean isLogged = false;

    public void logIn() {
        isLogged = true;
    }

    public void validationCheck() {
        if (!isLogged) {
            throw new RuntimeException("Oops");
        }
    }
}

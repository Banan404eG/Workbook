package tk.exdeath.model.logic.admin.account;

import javax.annotation.Resource;

public class AccountAdmin {

    @Resource(name = "getLoggedAdmin")
    private LoggedAdmin loggedAdmin;

    public void validationCheck() {
        loggedAdmin.validationCheck();
    }
}

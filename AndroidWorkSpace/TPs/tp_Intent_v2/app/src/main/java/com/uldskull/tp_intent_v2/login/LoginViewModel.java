package com.uldskull.tp_intent_v2.login;

import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {

    private final String PACKAGE_PATH = "com.uldskull.tp_intent_v2";

    //  String for defaults login and password.
    String defaultLogin = "pierre";
    String defaultPassword = "0000";

    public String getTypedPassword() {
        return typedPassword;
    }

    public void setTypedPassword(String typedPassword) {
        this.typedPassword = typedPassword;
    }

    //  Typed strings
    private String typedPassword;

    public boolean checkValues() {

        if ((this.getTypedPassword().equals("0000")) && (this.getTypedLogin().equals("pierre"))) {
            return true;
        }
        return false;
    }


    public void setTypedLogin(String typedLogin) {
        this.typedLogin = typedLogin;
    }

    private String typedLogin;

    public String getTypedLogin(){
        return this.typedLogin;
    }



    // TODO: Implement the ViewModel
}

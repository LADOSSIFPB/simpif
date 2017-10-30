package br.edu.ladoss.simpifladoss.mvp;

import android.content.Intent;
import android.widget.EditText;

import br.edu.ladoss.simpifladoss.MVPApp;
import br.edu.ladoss.simpifladoss.models.User;

/**
 * Created by Rennan on 06/09/2017.
 */

public interface EnterMVP {

    interface Model extends MVPApp.Model {
        void login(User user);
    }

    interface View extends MVPApp.View {
        void showMessage(String msg);
        void setInvalidEmail();
        void setInvalidPassword();
    }

    interface Presenter extends MVPApp.Presenter {
        void login(User user);
        void redirectToLoginActivity(Intent intent);
        Boolean isLoginValid(String identificador, String senha);
    }
}

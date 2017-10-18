package br.edu.ladoss.simpifladoss.mvp;

import android.os.Bundle;

import br.edu.ladoss.simpifladoss.MVPApp;
import br.edu.ladoss.simpifladoss.models.User;

/**
 * Created by Rennan on 18/10/17.
 */

public interface LoginMVP {

    interface Model extends MVPApp.Model {
        void doLogin(User user, Bundle extra);
        String receiveAcessKey(User user);
        void redirectLogin(Bundle extra);
    }

    interface View extends MVPApp.View {

    }

    interface Presenter extends MVPApp.Presenter {
        void doLogin(Bundle extra);
        void finishActivity();
    }

}

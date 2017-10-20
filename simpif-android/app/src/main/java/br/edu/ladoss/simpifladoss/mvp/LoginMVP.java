package br.edu.ladoss.simpifladoss.mvp;

import android.content.Intent;
import android.os.Bundle;

import br.edu.ladoss.simpifladoss.MVPApp;
import br.edu.ladoss.simpifladoss.exception.FormLoginException;
import br.edu.ladoss.simpifladoss.exception.ValidationException;
import br.edu.ladoss.simpifladoss.models.User;

/**
 * Created by Rennan on 18/10/17.
 */

public interface LoginMVP {

    interface Model extends MVPApp.Model {
        void tryLoginUser(User user);
    }

    interface View extends MVPApp.View {
        void onError();
        void onFailure(FormLoginException e);
        void onSuccess();
    }

    interface Presenter extends MVPApp.Presenter {
        void doLogin(Bundle extra);
        void onSuccessLogin();
        void onFailureLogin(RuntimeException e);
        void redirectToLogin(Bundle extra);
        void redirectToHome(Bundle extra);
    }

}

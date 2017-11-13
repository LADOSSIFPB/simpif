package br.edu.ladoss.simpifladoss.mvp.presenter;

import android.content.Context;
import android.content.Intent;

import br.edu.ladoss.simpifladoss.models.User;
import br.edu.ladoss.simpifladoss.mvp.EnterMVP;
import br.edu.ladoss.simpifladoss.mvp.model.EnterModelImp;
import br.edu.ladoss.simpifladoss.validation.Validate;

/**
 * Created by Rennan on 07/09/2017.
 */

public class EnterPresenterImp implements EnterMVP.Presenter {

    private EnterMVP.Model model;
    private transient EnterMVP.View view;

    public EnterPresenterImp(EnterMVP.View view) {
        this.view = view;
        this.model = new EnterModelImp(this);
    }

    @Override
    public void login(User user) {
        if(isLoginValid(user.getEmail(), user.getSenha())) {
            model.login(user);
            view.get().finish();
        }
    }

    @Override
    public void redirectToLoginActivity(Intent intent) {
        getContext().startActivity(intent);
    }

    @Override
    public Boolean isLoginValid(String identificador, String senha) {
        Boolean validated = Validate.validaIdentificador(identificador);

        if (!validated) {
            view.setInvalidEmail();
            return false;
        }

        if (senha == null || senha.isEmpty()) {
            view.setInvalidPassword();
            return false;
        }

        return true;
    }

    @Override
    public Context getContext() {
        return view.getContext();
    }

    @Override
    public void onDestroy() {
        model.onDestroy();
        model = null;
        view = null;
    }
}

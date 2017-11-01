package br.edu.ladoss.simpifladoss.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import br.edu.ladoss.simpifladoss.exception.ValidationException;
import br.edu.ladoss.simpifladoss.models.User;
import br.edu.ladoss.simpifladoss.mvp.LoginMVP;
import br.edu.ladoss.simpifladoss.mvp.model.LoginModelImp;
import br.edu.ladoss.simpifladoss.view.activities.EnterActivity;
import br.edu.ladoss.simpifladoss.view.activities.HomeActivity;

/**
 * Created by Rennan on 18/10/17.
 */

public class LoginPresenterImp implements LoginMVP.Presenter {

    private LoginMVP.View view;
    private LoginMVP.Model model;

    public LoginPresenterImp(LoginMVP.View view) {
        this.view = view;
        model = new LoginModelImp(this);
    }

    @Override
    public void doLogin(Bundle extra) {
        try {
            User user = new User();
            user.setEmail(extra.getString("email"));
            user.setSenha(extra.getString("senha"));

            model.tryLoginUser(user);
        } catch (ValidationException | NullPointerException e) {
            this.redirectToLogin(extra);
        }
    }

    @Override
    public void onSuccessLogin() {
        view.onSuccess();
        this.redirectToHome();
    }

    @Override
    public void onFailureLogin(RuntimeException e, User user) {
        Bundle bundle = new Bundle();
        bundle.putString("email", user.getEmail());
        redirectToLogin(bundle);
    }

    @Override
    public void redirectToLogin(Bundle extra) {
        Intent intent = new Intent(getContext(), EnterActivity.class);

        if (extra != null) {
            intent.putExtras(extra);
        }

        getContext().startActivity(intent);
        view.get().finish();
    }

    @Override
    public void redirectToHome() {
        Intent intent = new Intent(getContext(), HomeActivity.class);
        getContext().startActivity(intent);
        view.get().finish();
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

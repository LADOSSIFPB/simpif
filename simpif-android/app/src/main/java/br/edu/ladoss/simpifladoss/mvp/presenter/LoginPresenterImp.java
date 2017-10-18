package br.edu.ladoss.simpifladoss.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import br.edu.ladoss.simpifladoss.models.User;
import br.edu.ladoss.simpifladoss.mvp.LoginMVP;
import br.edu.ladoss.simpifladoss.mvp.model.LoginModelImp;
import br.edu.ladoss.simpifladoss.view.activities.EnterActivity;

/**
 * Created by Rennan on 18/10/17.
 */

public class LoginPresenterImp implements LoginMVP.Presenter{

    private LoginMVP.View view;
    private LoginMVP.Model model;

    public LoginPresenterImp(LoginMVP.View view) {
        this.view = view;
        model = new LoginModelImp(this);
    }

    @Override
    public void doLogin(Bundle extra) {
        if (extra != null && !extra.isEmpty()) {
            User user = new User();
            user.setEmail(extra.getString("email"));
            user.setSenha(extra.getString("senha"));

            if (!(user.getEmail() == null || user.getEmail().isEmpty())){
                model.doLogin(user, extra);
                return;
            }
        }

        Intent intent = new Intent(getContext(), EnterActivity.class);
        intent.putExtras(extra);
        getContext().startActivity(intent);
        finishActivity();
    }

    @Override
    public void finishActivity() {
        view.get().finish();
    }

    @Override
    public Context getContext() {
        return view.getContext();
    }

    @Override
    public void onDestroy() {
        model = null;
        view = null;
    }
}

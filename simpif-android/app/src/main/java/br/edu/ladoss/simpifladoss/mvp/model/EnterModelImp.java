package br.edu.ladoss.simpifladoss.mvp.model;

import android.content.Intent;
import android.os.Bundle;

import br.edu.ladoss.simpifladoss.models.User;
import br.edu.ladoss.simpifladoss.mvp.EnterMVP;
import br.edu.ladoss.simpifladoss.view.activities.LoginActivity;

/**
 * Created by Rennan on 07/09/2017.
 */

public class EnterModelImp implements EnterMVP.Model {

    private transient EnterMVP.Presenter presenter;

    @Override
    public void login(User user) {
        Intent intent = new Intent(presenter.getContext(), LoginActivity.class);
        Bundle bundle = new Bundle();

        bundle.putString("email", user.getEmail());
        bundle.putString("senha", user.getSenha());
        intent.putExtras(bundle);

        presenter.redirectToLoginActivity(intent);
    }

    public EnterModelImp(EnterMVP.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onDestroy() {
        presenter = null;
    }
}

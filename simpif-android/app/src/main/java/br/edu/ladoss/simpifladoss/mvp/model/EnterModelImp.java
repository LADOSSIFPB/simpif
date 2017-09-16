package br.edu.ladoss.simpifladoss.mvp.model;

import android.content.Intent;

import br.edu.ladoss.simpifladoss.mvp.EnterMVP;
import br.edu.ladoss.simpifladoss.view.activities.HomeActivity;

/**
 * Created by Rennan on 07/09/2017.
 */

public class EnterModelImp implements EnterMVP.Model {

    private EnterMVP.Presenter presenter;

    @Override
    public void doLogin() {
        presenter.getContext().startActivity(new Intent(presenter.getContext(), HomeActivity.class));

    }

    public EnterModelImp(EnterMVP.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onDestroy() {
        presenter = null;
    }
}

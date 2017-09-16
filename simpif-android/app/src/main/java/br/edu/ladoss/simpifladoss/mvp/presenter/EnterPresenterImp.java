package br.edu.ladoss.simpifladoss.mvp.presenter;

import android.content.Context;

import br.edu.ladoss.simpifladoss.mvp.EnterMVP;
import br.edu.ladoss.simpifladoss.mvp.model.EnterModelImp;

/**
 * Created by Rennan on 07/09/2017.
 */

public class EnterPresenterImp implements EnterMVP.Presenter {

    private EnterMVP.Model model;
    private EnterMVP.View view;

    public EnterPresenterImp(EnterMVP.View view) {
        this.view = view;
        this.model = new EnterModelImp(this);
    }

    @Override
    public void doLogin() {
        model.doLogin();
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

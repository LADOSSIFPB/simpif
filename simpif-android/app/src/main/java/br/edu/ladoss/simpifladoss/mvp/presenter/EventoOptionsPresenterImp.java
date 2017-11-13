package br.edu.ladoss.simpifladoss.mvp.presenter;

import android.content.Context;

import br.edu.ladoss.simpifladoss.mvp.EventoOptionsMVP;
import br.edu.ladoss.simpifladoss.mvp.model.EventoOptionsModelImp;

/**
 * Created by Rennan on 13/11/17.
 */

public class EventoOptionsPresenterImp implements EventoOptionsMVP.Presenter{

    private transient EventoOptionsMVP.View view;
    private EventoOptionsMVP.Model model;

    public EventoOptionsPresenterImp(EventoOptionsMVP.View view) {
        this.view = view;
        model = new EventoOptionsModelImp(this);
    }

    @Override
    public void onClickItem(String option) {
        model.redirectTo(option);
    }

    @Override
    public void showMessage(String msg) {
        view.showMessage(msg);
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

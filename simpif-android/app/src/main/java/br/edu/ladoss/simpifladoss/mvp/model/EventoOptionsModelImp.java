package br.edu.ladoss.simpifladoss.mvp.model;

import br.edu.ladoss.simpifladoss.mvp.EventoOptionsMVP;

/**
 * Created by Rennan on 13/11/17.
 */

public class EventoOptionsModelImp implements EventoOptionsMVP.Model{

    private transient EventoOptionsMVP.Presenter presenter;

    public EventoOptionsModelImp(EventoOptionsMVP.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void redirectTo(String option) {

    }

    @Override
    public void onDestroy() {
        presenter = null;
    }

}

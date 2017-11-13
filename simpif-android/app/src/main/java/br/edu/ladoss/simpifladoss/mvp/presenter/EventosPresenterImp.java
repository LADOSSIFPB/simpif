package br.edu.ladoss.simpifladoss.mvp.presenter;

import android.content.Context;

import java.util.List;

import br.edu.ladoss.simpifladoss.models.Evento;
import br.edu.ladoss.simpifladoss.mvp.EventosMVP;
import br.edu.ladoss.simpifladoss.mvp.model.EventosModelImp;

/**
 * Created by root on 13/11/17.
 */

public class EventosPresenterImp implements EventosMVP.Presenter{

    private transient EventosMVP.View view;
    private EventosMVP.Model model;

    public EventosPresenterImp(EventosMVP.View view) {
        this.view = view;
        model = new EventosModelImp(this);
    }

    @Override
    public void onClickEvent(Evento evento) {
        model.redirectToEventOptions(evento);
    }

    @Override
    public void requestEvents() {
        model.requestEvents();
    }

    @Override
    public void onSendSuccess(List<Evento> eventos) {
        view.updateEvents(eventos);
    }

    @Override
    public void onSendError(String msg) {
        view.showMessage(msg);
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

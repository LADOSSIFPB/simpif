package br.edu.ladoss.simpifladoss.mvp.presenter;

import android.content.Context;

import java.util.List;

import br.edu.ladoss.simpifladoss.models.Apresentacao;
import br.edu.ladoss.simpifladoss.models.Cronograma;
import br.edu.ladoss.simpifladoss.models.Evento;
import br.edu.ladoss.simpifladoss.mvp.EventoApresentacoesMVP;
import br.edu.ladoss.simpifladoss.mvp.model.EventoApresentacoesModelImp;

/**
 * Created by Rennan on 13/11/17.
 */

public class EventoApresentacoesPresenterImp implements EventoApresentacoesMVP.Presenter{

    private transient EventoApresentacoesMVP.View view;
    private EventoApresentacoesMVP.Model model;

    public EventoApresentacoesPresenterImp(EventoApresentacoesMVP.View view) {
        this.view = view;
        model = new EventoApresentacoesModelImp(this);
    }

    @Override
    public void requestApresentacoes(Evento evento) {
        model.requestApresentacoes(evento);
    }

    @Override
    public void onSendSuccess(List<Apresentacao> apresentacoes) {
        view.updateApresentacoes(apresentacoes);
    }

    @Override
    public void onSendError(String msg) {
        showMessage(msg);
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

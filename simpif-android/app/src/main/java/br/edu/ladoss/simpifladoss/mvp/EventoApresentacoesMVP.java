package br.edu.ladoss.simpifladoss.mvp;

import java.util.List;

import br.edu.ladoss.simpifladoss.MVPApp;
import br.edu.ladoss.simpifladoss.models.Apresentacao;
import br.edu.ladoss.simpifladoss.models.Cronograma;
import br.edu.ladoss.simpifladoss.models.Evento;

/**
 * Created by Rennan on 13/11/17.
 */

public interface EventoApresentacoesMVP {

    interface Model extends MVPApp.Model {
        void requestApresentacoes(Evento evento);
    }

    interface View extends MVPApp.View {
        void showMessage(String msg);
        void updateApresentacoes(List<Apresentacao> apresentacoes);
    }

    interface Presenter extends MVPApp.Presenter {
        void requestApresentacoes(Evento evento);
        void showMessage(String msg);
        void onSendSuccess(List<Apresentacao> apresentacoes);
        void onSendError(String msg);
    }

}

package br.edu.ladoss.simpifladoss.mvp;

import java.util.List;

import br.edu.ladoss.simpifladoss.MVPApp;
import br.edu.ladoss.simpifladoss.models.Evento;

/**
 * Created by Rennan on 13/11/17.
 */

public interface EventosMVP{

    interface Model extends MVPApp.Model {
        void requestEvents();
        void redirectToEventOptions(Evento evento);
    }

    interface View extends MVPApp.View {
        void showMessage(String msg);
        void updateEvents(List<Evento> eventos);
    }

    interface Presenter extends MVPApp.Presenter {
        void showMessage(String msg);
        void requestEvents();
        void onSendSuccess(List<Evento> eventos);
        void onSendError(String msg);
        void onClickEvent(Evento evento);
    }

}

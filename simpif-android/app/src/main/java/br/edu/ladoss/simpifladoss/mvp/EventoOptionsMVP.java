package br.edu.ladoss.simpifladoss.mvp;

import br.edu.ladoss.simpifladoss.MVPApp;

/**
 * Created by Rennan on 13/11/17.
 */

public interface EventoOptionsMVP {

    interface Model extends MVPApp.Model {
        void redirectTo(String option);
    }

    interface View extends MVPApp.View {
        void showMessage(String msg);
    }

    interface Presenter extends MVPApp.Presenter {
        void showMessage(String msg);
        void onClickItem(String option);
    }

}

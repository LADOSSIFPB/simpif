package br.edu.ladoss.simpifladoss.mvp;

import br.edu.ladoss.simpifladoss.MVPApp;

/**
 * Created by Rennan on 06/09/2017.
 */

public interface EnterMVP {

    interface Model extends MVPApp.Model {
        void doLogin();
    }

    interface View extends MVPApp.View {

    }

    interface Presenter extends MVPApp.Presenter {
        void doLogin();
    }
}

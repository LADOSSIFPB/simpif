package br.edu.ladoss.simpifladoss.mvp;

import android.app.Activity;
import android.view.MenuItem;

import br.edu.ladoss.simpifladoss.MVPApp;

/**
 * Created by Rennan on 06/09/2017.
 */

public interface HomeMVP {

    interface Model extends MVPApp.Model {
        void openScanner(Activity activity);
        void quit(Activity activity);
    }

    interface View extends MVPApp.View {

    }

    interface Presenter extends MVPApp.Presenter {
        void showMessage(String msg);
        void openScanner();
        void quit();
        void selectedItem(MenuItem item);
    }

}

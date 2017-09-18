package br.edu.ladoss.simpifladoss.mvp;

import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;

import br.edu.ladoss.simpifladoss.MVPApp;

/**
 * Created by Rennan on 06/09/2017.
 */

public interface HomeMVP {

    interface Model extends MVPApp.Model {
        void openScanner();
        void quit();
        void sendCodeToServer(String code);
    }

    interface View extends MVPApp.View {
        void showMessage(String msg);
        void setLoading(boolean isLoading);
    }

    interface Presenter extends MVPApp.Presenter {
        void showMessage(String msg);
        void onActivityResult(int requestCode, int resultCode, Intent data);
        void openScanner();
        Activity getActivity();
        void selectedItem(MenuItem item);
        void onSendError(String message);
        void onSendSucess();
    }

}

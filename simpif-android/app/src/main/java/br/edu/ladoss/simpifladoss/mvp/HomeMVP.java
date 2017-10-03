package br.edu.ladoss.simpifladoss.mvp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import br.edu.ladoss.simpifladoss.MVPApp;

/**
 * Created by Rennan on 06/09/2017.
 */

public interface HomeMVP {
    final String BUNDLE = "bundle";

    interface Model extends MVPApp.Model {
        void openScanner();
        void sendCodeToServer(String code);
    }

    interface View extends MVPApp.View {
        void showMessage(String msg);
        void setLoading(boolean isLoading);
        void showDialogExit(DialogInterface.OnClickListener listenerNeutral, DialogInterface.OnClickListener listenerNegative);
    }

    interface Presenter extends MVPApp.Presenter {
        void showMessage(String msg);
        void exit();
        void onActivityResult(int requestCode, int resultCode, Intent data);
        void openScanner();
        Activity getActivity();
        void selectedItem(MenuItem item);
        void onSendError(String message);

        void onCreateOptionsMenu(Menu menu);

        void onSendSucess();
        void openManualCheckin();
    }

}

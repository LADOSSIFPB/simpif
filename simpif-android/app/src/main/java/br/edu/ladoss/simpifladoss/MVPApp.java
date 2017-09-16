package br.edu.ladoss.simpifladoss;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Rennan on 06/09/2017.
 */

public interface MVPApp {

    interface Model {
        void onDestroy();
    }

    interface View {
        Context getContext();
        Context getAppContext();
        AppCompatActivity get();
    }

    interface Presenter {
        Context getContext();
        void onDestroy();
    }

}

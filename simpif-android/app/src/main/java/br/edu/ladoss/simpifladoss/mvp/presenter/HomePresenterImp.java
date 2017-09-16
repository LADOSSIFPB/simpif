package br.edu.ladoss.simpifladoss.mvp.presenter;

import android.content.Context;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;

import br.edu.ladoss.simpifladoss.R;
import br.edu.ladoss.simpifladoss.mvp.HomeMVP;
import br.edu.ladoss.simpifladoss.mvp.model.HomeModelImp;

/**
 * Created by Rennan on 07/09/2017.
 */

public class HomePresenterImp implements HomeMVP.Presenter{

    private HomeMVP.Model model;
    private HomeMVP.View view;

    public HomePresenterImp(HomeMVP.View view) {
        this.view = view;
        this.model = new HomeModelImp(this);
    }

    @Override
    public void openScanner() {
        model.openScanner(view.get());
    }

    @Override
    public void selectedItem(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.sair) {
            quit();
        }
    }

    @Override
    public void quit() {
        model.quit(view.get());
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
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

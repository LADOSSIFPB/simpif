package br.edu.ladoss.simpifladoss.mvp.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.IOException;
import java.io.Serializable;

import br.edu.ladoss.simpifladoss.R;
import br.edu.ladoss.simpifladoss.mvp.HomeMVP;
import br.edu.ladoss.simpifladoss.mvp.model.HomeModelImp;
import br.edu.ladoss.simpifladoss.network.ConnectionServer;
import br.edu.ladoss.simpifladoss.view.activities.EnterActivity;
import retrofit.Call;
import retrofit.Response;

/**
 * Created by Rennan on 07/09/2017.
 */

public class HomePresenterImp implements HomeMVP.Presenter{

    private HomeMVP.Model model;
    private transient HomeMVP.View view;

    public HomePresenterImp(HomeMVP.View view) {
        this.view = view;
        this.model = new HomeModelImp(this);
    }

    @Override
    public void openScanner() {
        model.openScanner();
    }

    @Override
    public Activity getActivity() {
        return view.get();
    }

    @Override
    public void selectedItem(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.sair) {
            exit();
        }
    }

    @Override
    public void onSendError(String message) {
        view.setLoading(false);
    }

    @Override
    public void onSendSucess() {
        view.setLoading(false);
    }

    @Override
    public void showMessage(String msg) {
        view.showMessage(msg);
    }

    @Override
    public void exit() {
        DialogInterface.OnClickListener neutral = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                getContext().startActivity(new Intent(getContext(), EnterActivity.class));
                getActivity().finish();
            }
        };

        DialogInterface.OnClickListener dismiss = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        };


        view.showDialogExit(neutral, dismiss);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if(result != null) {
            String content = result.getContents();
            if(content != null) {
                view.setLoading(true);
                model.sendCodeToServer(content);
            } else {
                view.showMessage(getContext().getString(R.string.canceled));
            }
        }
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

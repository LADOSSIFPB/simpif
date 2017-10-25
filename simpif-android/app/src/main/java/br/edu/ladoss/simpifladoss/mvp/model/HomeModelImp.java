package br.edu.ladoss.simpifladoss.mvp.model;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;

import com.google.zxing.integration.android.IntentIntegrator;

import br.edu.ladoss.simpifladoss.R;
import br.edu.ladoss.simpifladoss.mvp.HomeMVP;
import br.edu.ladoss.simpifladoss.network.ConnectionServer;
import br.edu.ladoss.simpifladoss.util.PreferencesUtils;
import br.edu.ladoss.simpifladoss.util.StringUtil;
import br.edu.ladoss.simpifladoss.view.activities.EnterActivity;
import br.edu.ladoss.simpifladoss.view.activities.SearchActivity;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

import static br.edu.ladoss.simpifladoss.util.PreferencesUtils.getAccessKeyOnSharedPreferences;

/**
 * Created by Rennan on 07/09/2017.
 */

public class HomeModelImp implements HomeMVP.Model {

    private transient HomeMVP.Presenter presenter;

    public HomeModelImp(HomeMVP.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void openScanner() {
        IntentIntegrator integrator = new IntentIntegrator(presenter.getActivity());
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt(presenter.getContext().getString(R.string.scanning));
        integrator.setCameraId(0);
        integrator.initiateScan();
    }

    @Override
    public void sendCodeToServer(String code) {
        ConnectionServer.getInstance().updateServiceAdress();

        String key = getAccessKeyOnSharedPreferences(presenter.getContext());

        Call<Void> call = ConnectionServer.getInstance().getService().checkin("Basic " + key, code);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Response<Void> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    presenter.onSendSucess();
                } else{
                    presenter.onSendError(presenter.getContext().getString(R.string.on_code_error));
                }

            }

            @Override
            public void onFailure(Throwable t) {
                presenter.onSendError(presenter.getContext().getString(R.string.on_code_error));
            }
        });
    }

    @Override
    public void onDestroy() {
        presenter = null;
    }
}

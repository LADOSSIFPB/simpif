package br.edu.ladoss.simpifladoss.mvp.model;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;

import com.google.zxing.integration.android.IntentIntegrator;

import br.edu.ladoss.simpifladoss.R;
import br.edu.ladoss.simpifladoss.mvp.HomeMVP;
import br.edu.ladoss.simpifladoss.network.ConnectionServer;
import br.edu.ladoss.simpifladoss.view.activities.EnterActivity;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Rennan on 07/09/2017.
 */

public class HomeModelImp implements HomeMVP.Model {

    private HomeMVP.Presenter presenter;

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

    public void quit() {
        AlertDialog alertDialog = new AlertDialog.Builder(presenter.getContext()).create();
        alertDialog.setTitle(presenter.getContext().getString(R.string.quit));
        alertDialog.setMessage(presenter.getContext().getString(R.string.quit_confirmation));
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, presenter.getContext().getString(R.string.quit),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        //
                        presenter.getContext().startActivity(new Intent(presenter.getContext(), EnterActivity.class));
                        presenter.getActivity().finish();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, presenter.getContext().getString(R.string.no),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    @Override
    public void sendCodeToServer(String code) {
        ConnectionServer.getInstance().updateServiceAdress();

        Call<Void> call = ConnectionServer.getInstance().getService().checkin(code);

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

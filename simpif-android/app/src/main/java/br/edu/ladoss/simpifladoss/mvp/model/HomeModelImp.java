package br.edu.ladoss.simpifladoss.mvp.model;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;

import com.google.zxing.integration.android.IntentIntegrator;

import br.edu.ladoss.simpifladoss.R;
import br.edu.ladoss.simpifladoss.mvp.HomeMVP;
import br.edu.ladoss.simpifladoss.view.activities.EnterActivity;
import br.edu.ladoss.simpifladoss.view.activities.HomeActivity;

/**
 * Created by Rennan on 07/09/2017.
 */

public class HomeModelImp implements HomeMVP.Model{

    private HomeMVP.Presenter presenter;

    public HomeModelImp(HomeMVP.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void openScanner(Activity activity) {
        IntentIntegrator integrator = new IntentIntegrator(activity);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt(presenter.getContext().getString(R.string.scanning));
        integrator.setCameraId(0);
        integrator.initiateScan();
    }

    public void quit(final Activity activity) {
        AlertDialog alertDialog = new AlertDialog.Builder(presenter.getContext()).create();
        alertDialog.setTitle(presenter.getContext().getString(R.string.sair));
        alertDialog.setMessage(presenter.getContext().getString(R.string.sairconfirmation));
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, presenter.getContext().getString(R.string.sair),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        //
                        presenter.getContext().startActivity(new Intent(presenter.getContext(), EnterActivity.class));
                        activity.finish();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, presenter.getContext().getString(R.string.nao),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    @Override
    public void onDestroy() {
        presenter = null;
    }
}

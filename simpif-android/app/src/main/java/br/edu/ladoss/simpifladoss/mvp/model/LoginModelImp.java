package br.edu.ladoss.simpifladoss.mvp.model;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

import br.edu.ladoss.simpifladoss.R;
import br.edu.ladoss.simpifladoss.models.User;
import br.edu.ladoss.simpifladoss.mvp.LoginMVP;
import br.edu.ladoss.simpifladoss.network.ConnectionServer;
import br.edu.ladoss.simpifladoss.util.PreferencesUtils;
import br.edu.ladoss.simpifladoss.view.activities.EnterActivity;
import br.edu.ladoss.simpifladoss.view.activities.HomeActivity;
import retrofit.Call;
import retrofit.Response;

/**
 * Created by root on 18/10/17.
 */

public class LoginModelImp implements LoginMVP.Model{

    private LoginMVP.Presenter presenter;

    public LoginModelImp(LoginMVP.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void doLogin(final User userReferencial, final Bundle extra) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ConnectionServer.getInstance().updateServiceAdress();

                String acessKey = PreferencesUtils.getAccessKeyOnSharedPreferences(presenter.getContext());

                User user = userReferencial;

                if (acessKey == null || acessKey.isEmpty()) {

                    acessKey = receiveAcessKey(user);

                    //Cria e testa se a chave é valida
                    if ((acessKey == null) || acessKey.isEmpty()) {
                        Intent intent = new Intent(presenter.getContext(), EnterActivity.class);
                        intent.putExtras(extra);
                        presenter.getContext().startActivity(intent);
                        presenter.finishActivity();
                        return;
                    }
                    PreferencesUtils.setAccessKeyOnSharedPreferences(presenter.getContext(), acessKey);
                }

                presenter.getContext().startActivity(new Intent(presenter.getContext(), HomeActivity.class));
                presenter.finishActivity();
            }
        }).start();
    }

    @Override
    public String receiveAcessKey(User user) {
        Log.i(presenter.getContext().getString(R.string.app_name), " solicitando uma chave ao servidor");

        Call<String> call = ConnectionServer.getInstance().getService().login(user);

        try {
            Response<String> response = call.execute();

            if (response.isSuccess()) {
                Log.i(presenter.getContext().getString(R.string.app_name), " chave recuperada com sucesso");
                return response.body();

            } else {
                Log.i(presenter.getContext().getString(R.string.app_name), " a chave não foi recuperada com êxito");
            }
        } catch (IOException e) {
            Log.e(presenter.getContext().getString(R.string.app_name), e.getMessage());
        }

        return null;
    }

    @Override
    public void redirectLogin(Bundle extra) {

    }

    @Override
    public void onDestroy() {
        presenter = null;
    }
}

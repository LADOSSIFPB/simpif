package br.edu.ladoss.simpifladoss.mvp.model;

import android.util.Log;

import br.edu.ladoss.simpifladoss.R;
import br.edu.ladoss.simpifladoss.exception.FormLoginException;
import br.edu.ladoss.simpifladoss.models.User;
import br.edu.ladoss.simpifladoss.mvp.LoginMVP;
import br.edu.ladoss.simpifladoss.network.ConnectionServer;
import br.edu.ladoss.simpifladoss.util.PreferencesUtils;
import br.edu.ladoss.simpifladoss.util.StringUtil;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by root on 18/10/17.
 */

public class LoginModelImp implements LoginMVP.Model {

    private LoginMVP.Presenter presenter;

    public LoginModelImp(LoginMVP.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void tryLoginUser(User userReferencial) {
        if (userReferencial != null){
            String accessKey = PreferencesUtils.getAccessKeyOnSharedPreferences(presenter.getContext());

            if (accessKey != null && !accessKey.isEmpty()) {
                presenter.onSuccessLogin();
            } else {
                Log.d(LoginModelImp.class.getName(), "AcessKey inexistente");
                getAccessKeyFromServer(userReferencial);
            }
        }
    }


    private void getAccessKeyFromServer(final User user) {
        Log.i(this.getClass().getName(), " solicitando uma chave ao servidor");

        Call<String> call = ConnectionServer.getInstance().getService().login(user);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Response<String> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    Log.i(this.getClass().getName(), " chave recuperada com sucesso");
                    PreferencesUtils.setAccessKeyOnSharedPreferences(presenter.getContext(), StringUtil.criptografarBase64(response.body() + ":unused").replace("\n", ""));
                    tryLoginUser(user);
                } else {
                    Log.i(this.getClass().getName(), " a chave não foi recuperada com êxito");
                    presenter.onFailureLogin(new FormLoginException("Usuário inválido"), user);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e(this.getClass().getName(), t.getMessage());
                presenter.onFailureLogin(new RuntimeException(t), user);
            }
        });
    }

    @Override
    public void onDestroy() {
        presenter = null;
    }
}

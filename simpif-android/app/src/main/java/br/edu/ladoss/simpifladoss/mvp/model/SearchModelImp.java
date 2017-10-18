package br.edu.ladoss.simpifladoss.mvp.model;

import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import br.edu.ladoss.simpifladoss.R;
import br.edu.ladoss.simpifladoss.models.Attendee;
import br.edu.ladoss.simpifladoss.mvp.SearchMVP;
import br.edu.ladoss.simpifladoss.network.ConnectionServer;
import br.edu.ladoss.simpifladoss.util.PreferencesUtils;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Rennan on 28/09/17.
 */

public class SearchModelImp implements SearchMVP.Model{

    private SearchMVP.Presenter presenter;

    public SearchModelImp(SearchMVP.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void requestOrderAttendees(String orderRef) {
        ConnectionServer.getInstance().updateServiceAdress();

        Call<List<Attendee>> call = ConnectionServer.getInstance().getService().order(orderRef, PreferencesUtils.getAccessKeyOnSharedPreferences(presenter.getContext()));

        call.enqueue(new Callback<List<Attendee>>() {
            @Override
            public void onResponse(Response<List<Attendee>> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    if(!response.body().isEmpty()) {
                        presenter.updateAttendees(response.body());
                    }
                    else
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
    public void sendCodeToServer(String code) {
        ConnectionServer.getInstance().updateServiceAdress();

        Call<Void> call = ConnectionServer.getInstance().getService().checkin(code, PreferencesUtils.getAccessKeyOnSharedPreferences(presenter.getContext()));

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

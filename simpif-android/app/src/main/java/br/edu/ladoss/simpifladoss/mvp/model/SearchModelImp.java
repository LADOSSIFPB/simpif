package br.edu.ladoss.simpifladoss.mvp.model;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.edu.ladoss.simpifladoss.R;
import br.edu.ladoss.simpifladoss.models.Attendee;
import br.edu.ladoss.simpifladoss.mvp.SearchMVP;
import br.edu.ladoss.simpifladoss.network.ConnectionServer;
import br.edu.ladoss.simpifladoss.util.PreferencesUtils;
import br.edu.ladoss.simpifladoss.util.StringUtil;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

import static br.edu.ladoss.simpifladoss.util.PreferencesUtils.*;

/**
 * Created by Rennan on 28/09/17.
 */

public class SearchModelImp implements SearchMVP.Model{

    private transient SearchMVP.Presenter presenter;

    public SearchModelImp(SearchMVP.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void requestOrderAttendees(String orderRef) {
        ConnectionServer.getInstance().updateServiceAdress();

        String key = getAccessKeyOnSharedPreferences(presenter.getContext());

        Call<List<Attendee>> call = ConnectionServer.getInstance().getService().order("Basic " + key, orderRef);

        Log.i("simpif",PreferencesUtils.getAccessKeyOnSharedPreferences(presenter.getContext()));

        call.enqueue(new Callback<List<Attendee>>() {
            @Override
            public void onResponse(Response<List<Attendee>> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    if(!response.body().isEmpty()) {
                        presenter.updateAttendees(response.body());
                    }
                    else
                        presenter.onSendError(presenter.getContext().getString(R.string.code_not_found));
                }else
                    presenter.onSendError(presenter.getContext().getString(R.string.on_code_error));
            }

            @Override
            public void onFailure(Throwable t) {
                presenter.onSendError(presenter.getContext().getString(R.string.fail_connect_server));
            }
        });
    }

    @Override
    public void sendCodeToServer(String code) {
        ConnectionServer.getInstance().updateServiceAdress();

        String key = getAccessKeyOnSharedPreferences(presenter.getContext());

        Call<String> call = ConnectionServer.getInstance().getService().checkin("Basic " + key, code);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Response<String> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    if(response.code() == 200)
                        presenter.onSendSucess();
                    else
                        presenter.onSendError(response.body());
                } else{
                    presenter.onSendError(response.body());
                }

            }

            @Override
            public void onFailure(Throwable t) {
                presenter.onSendError(presenter.getContext().getString(R.string.fail_connect_server));
            }
        });
    }

    @Override
    public void onDestroy() {
        presenter = null;
    }
}

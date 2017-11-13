package br.edu.ladoss.simpifladoss.mvp.model;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

import br.edu.ladoss.simpifladoss.R;
import br.edu.ladoss.simpifladoss.models.Evento;
import br.edu.ladoss.simpifladoss.mvp.EventosMVP;
import br.edu.ladoss.simpifladoss.network.ConnectionServer;
import br.edu.ladoss.simpifladoss.view.activities.EventoOptionsActivity;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Rennan on 13/11/17.
 */

public class EventosModelImp implements EventosMVP.Model{

    private transient EventosMVP.Presenter presenter;

    public EventosModelImp(EventosMVP.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void requestEvents() {
        ConnectionServer.getInstance().updateServiceAdress();

        Call<List<Evento>> call = ConnectionServer.getInstance().getService().eventos();

        call.enqueue(new Callback<List<Evento>>() {
            @Override
            public void onResponse(Response<List<Evento>> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    if(!response.body().isEmpty()) {
                        presenter.onSendSuccess(response.body());
                    }
                    else
                        presenter.onSendError(presenter.getContext().getString(R.string.no_events));
                }else
                    presenter.onSendError(presenter.getContext().getString(R.string.fail_connect_server));
            }

            @Override
            public void onFailure(Throwable t) {
                presenter.onSendError(presenter.getContext().getString(R.string.fail_connect_server));
            }
        });
    }

    @Override
    public void redirectToEventOptions(Evento evento) {
        Intent intent = new Intent(presenter.getContext(), EventoOptionsActivity.class);

        intent.putExtra(Evento.BUNDLE, evento);

        presenter.getContext().startActivity(intent);
    }

    @Override
    public void onDestroy() {
        presenter = null;
    }

}

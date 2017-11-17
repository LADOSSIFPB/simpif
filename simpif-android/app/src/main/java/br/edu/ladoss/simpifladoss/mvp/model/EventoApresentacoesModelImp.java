package br.edu.ladoss.simpifladoss.mvp.model;

import java.util.List;

import br.edu.ladoss.simpifladoss.R;
import br.edu.ladoss.simpifladoss.models.Apresentacao;
import br.edu.ladoss.simpifladoss.models.Cronograma;
import br.edu.ladoss.simpifladoss.models.Evento;
import br.edu.ladoss.simpifladoss.mvp.EventoApresentacoesMVP;
import br.edu.ladoss.simpifladoss.network.ConnectionServer;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Rennan on 13/11/17.
 */

public class EventoApresentacoesModelImp implements EventoApresentacoesMVP.Model{

    private transient EventoApresentacoesMVP.Presenter presenter;

    public EventoApresentacoesModelImp(EventoApresentacoesMVP.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void requestApresentacoes(Evento evento) {
        ConnectionServer.getInstance().updateServiceAdress();

        Call<List<Apresentacao>> call = ConnectionServer.getInstance().getService().apresentacoes(evento.getId());

        call.enqueue(new Callback<List<Apresentacao>>() {
            @Override
            public void onResponse(Response<List<Apresentacao>> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    if(!response.body().isEmpty()) {
                        presenter.onSendSuccess(response.body());
                    }
                    else
                        presenter.onSendError(presenter.getContext().getString(R.string.no_presentations));
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
    public void onDestroy() {
        presenter = null;
    }

}

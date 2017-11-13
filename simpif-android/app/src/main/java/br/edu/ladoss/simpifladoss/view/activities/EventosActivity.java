package br.edu.ladoss.simpifladoss.view.activities;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import br.edu.ladoss.simpifladoss.R;
import br.edu.ladoss.simpifladoss.models.Evento;
import br.edu.ladoss.simpifladoss.mvp.EventosMVP;
import br.edu.ladoss.simpifladoss.mvp.presenter.EventosPresenterImp;
import br.edu.ladoss.simpifladoss.view.adapters.EventoAdapter;
import br.edu.ladoss.simpifladoss.view.callback.RecycleButtonClicked;
import butterknife.BindView;
import butterknife.ButterKnife;

public class EventosActivity extends AppCompatActivity implements EventosMVP.View{

    @BindView(R.id.eventosRecycler)
    RecyclerView recycler;

    private EventosMVP.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventos_layout);

        ButterKnife.bind(this);

        this.presenter = new EventosPresenterImp(this);

        presenter.requestEvents();
    }

    @Override
    public void updateEvents(List<Evento> eventos) {
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(getContext());
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recycler.setLayoutManager(gridLayoutManager);
        recycler.setAdapter(new EventoAdapter(getContext(), eventos, new RecycleButtonClicked<Evento>() {
            @Override
            public void onClickCallback(Evento evento) {
                presenter.onClickEvent(evento);
            }
        }));
        recycler.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMessage(String msg) {
        Snackbar.make(this.findViewById(android.R.id.content), msg, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public Context getAppContext() {
        return getApplicationContext();
    }

    @Override
    public AppCompatActivity get() {
        return this;
    }
}

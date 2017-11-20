package br.edu.ladoss.simpifladoss.view.activities;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import java.util.List;

import br.edu.ladoss.simpifladoss.R;
import br.edu.ladoss.simpifladoss.models.Apresentacao;
import br.edu.ladoss.simpifladoss.models.Evento;
import br.edu.ladoss.simpifladoss.mvp.EventoApresentacoesMVP;
import br.edu.ladoss.simpifladoss.mvp.presenter.EventoApresentacoesPresenterImp;
import br.edu.ladoss.simpifladoss.view.adapters.CronogramaAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class EventoApresentacoesActivity extends AppCompatActivity implements EventoApresentacoesMVP.View {

    private Evento evento;

    @BindView(R.id.recyclerApresentacao)
    RecyclerView recycler;

    @BindView(R.id.loading)
    LinearLayout loadingLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_apresentacoes);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        if(getIntent().getExtras() != null){
            Evento e = (Evento) getIntent().getExtras().getSerializable(Evento.BUNDLE);
            if(e != null){
                evento = e;
            } else {
                finish();
            }
        }

        EventoApresentacoesMVP.Presenter presenter = new EventoApresentacoesPresenterImp(this);

        presenter.requestApresentacoes(evento);

    }

    @Override
    public void updateCronogramas(List<Apresentacao> apresentacoes) {
        loadingLayout.setVisibility(View.GONE);
        recycler.setVisibility(View.VISIBLE);

        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(getContext());
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recycler = findViewById(R.id.recyclerApresentacao);

        LinearSnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recycler);
        recycler.setLayoutManager(gridLayoutManager);
        recycler.setHasFixedSize(true);
        recycler.setAdapter(new CronogramaAdapter(getContext(), apresentacoes));
        recycler.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMessage(String msg) {
        loadingLayout.setVisibility(View.GONE);
        recycler.setVisibility(View.VISIBLE);
        Snackbar.make(this.findViewById(android.R.id.content), msg, Snackbar.LENGTH_INDEFINITE).show();
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

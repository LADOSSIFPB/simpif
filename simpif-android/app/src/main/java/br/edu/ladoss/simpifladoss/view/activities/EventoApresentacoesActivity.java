package br.edu.ladoss.simpifladoss.view.activities;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import br.edu.ladoss.simpifladoss.R;
import br.edu.ladoss.simpifladoss.models.Apresentacao;
import br.edu.ladoss.simpifladoss.models.Evento;
import br.edu.ladoss.simpifladoss.mvp.EventoApresentacoesMVP;
import br.edu.ladoss.simpifladoss.mvp.presenter.EventoApresentacoesPresenterImp;
import br.edu.ladoss.simpifladoss.view.adapters.ApresentacaoAdapter;
import br.edu.ladoss.simpifladoss.view.adapters.CronogramaAdapter;
import butterknife.BindView;

public class EventoApresentacoesActivity extends AppCompatActivity implements EventoApresentacoesMVP.View {

    private Evento evento;

    @BindView(R.id.recyclerApresentacao)
    RecyclerView recycler;

    private EventoApresentacoesMVP.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_apresentacoes);

        if(getIntent().getExtras() != null){
            Evento e = (Evento) getIntent().getExtras().getSerializable(Evento.BUNDLE);
            if(e != null){
                evento = e;
            } else {
                finish();
            }
        }

        this.presenter = new EventoApresentacoesPresenterImp(this);

        presenter.requestApresentacoes(evento);

    }

    @Override
    public void updateCronogramas(List<Apresentacao> apresentacoes) {
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(getContext());
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recycler = (RecyclerView) findViewById(R.id.recyclerApresentacao);

        LinearSnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recycler);
        recycler.setLayoutManager(gridLayoutManager);
        recycler.setHasFixedSize(false);
        recycler.setAdapter(new CronogramaAdapter(getContext(), apresentacoes));
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

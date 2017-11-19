package br.edu.ladoss.simpifladoss.view.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        this.presenter = new EventosPresenterImp(this);

        presenter.requestEvents();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.sair) {
            finish();
        }else if(id == R.id.sobre) {
            startActivity(new Intent(getContext(), AboutActivity.class));
        }else if(id == R.id.privacitypolicy) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/LADOSSIFPB/simpif/wiki/Privacy-Policy")));
        }
        return super.onOptionsItemSelected(item);
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

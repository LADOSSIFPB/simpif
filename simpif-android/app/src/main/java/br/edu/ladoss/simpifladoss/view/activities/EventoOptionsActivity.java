package br.edu.ladoss.simpifladoss.view.activities;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.edu.ladoss.simpifladoss.R;
import br.edu.ladoss.simpifladoss.mvp.EventoOptionsMVP;

public class EventoOptionsActivity extends AppCompatActivity implements EventoOptionsMVP.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_options);
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

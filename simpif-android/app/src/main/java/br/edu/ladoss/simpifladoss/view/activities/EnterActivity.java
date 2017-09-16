package br.edu.ladoss.simpifladoss.view.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import br.edu.ladoss.simpifladoss.R;
import br.edu.ladoss.simpifladoss.mvp.EnterMVP;
import br.edu.ladoss.simpifladoss.mvp.presenter.EnterPresenterImp;

public class EnterActivity extends AppCompatActivity implements EnterMVP.View{

    private EnterMVP.Presenter presenter;

    EditText userEditText;
    EditText passEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        presenter = new EnterPresenterImp(this);
    }

    public void doLogin(View view) {
        presenter.doLogin();
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

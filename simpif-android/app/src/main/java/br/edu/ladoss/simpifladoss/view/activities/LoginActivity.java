package br.edu.ladoss.simpifladoss.view.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.edu.ladoss.simpifladoss.R;
import br.edu.ladoss.simpifladoss.mvp.LoginMVP;
import br.edu.ladoss.simpifladoss.mvp.presenter.LoginPresenterImp;

public class LoginActivity extends AppCompatActivity implements LoginMVP.View{

    private LoginMVP.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        presenter = new LoginPresenterImp(this);

        presenter.doLogin(getIntent().getExtras());
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

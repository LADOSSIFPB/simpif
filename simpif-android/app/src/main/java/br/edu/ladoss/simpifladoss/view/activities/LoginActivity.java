package br.edu.ladoss.simpifladoss.view.activities;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import br.edu.ladoss.simpifladoss.R;
import br.edu.ladoss.simpifladoss.exception.FormLoginException;
import br.edu.ladoss.simpifladoss.mvp.LoginMVP;
import br.edu.ladoss.simpifladoss.mvp.presenter.LoginPresenterImp;


public class LoginActivity extends AppCompatActivity implements LoginMVP.View{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        startAnimation(findViewById(R.id.logo_simpif));
       // startAnimation(findViewById(R.id.logo_ladoss));
       // startAnimation(findViewById(R.id.logo_if));

        LoginMVP.Presenter presenter = new LoginPresenterImp(this);
        presenter.doLogin(getIntent().getExtras());
    }

    public void startAnimation(View view){
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(this,
                R.animator.animation_splash);
        set.setTarget(view);
        set.start();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public Context getAppContext() {
        return this;
    }

    @Override
    public AppCompatActivity get() {
        return this;
    }

    @Override
    public void onError() {

    }

    @Override
    public void onFailure(FormLoginException e) {

    }

    @Override
    public void onSuccess() {

    }
}

package br.edu.ladoss.simpifladoss.view.activities;


import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import br.edu.ladoss.simpifladoss.R;


public class SplashActivity extends AppCompatActivity {

    private final int TIME_ANIMATION = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        startAnimation(findViewById(R.id.logo_simpif));
        startAnimation(findViewById(R.id.logo_ladoss));
        startAnimation(findViewById(R.id.logo_if));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, EnterActivity.class));
                finish();
            }
        }, TIME_ANIMATION * 1000);
    }

    public void startAnimation(View view){
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(this,
                R.animator.animation_splash);
        set.setTarget(view);
        set.start();
    }

}

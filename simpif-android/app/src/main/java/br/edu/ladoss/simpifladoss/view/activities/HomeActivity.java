package br.edu.ladoss.simpifladoss.view.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.PersistableBundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import br.edu.ladoss.simpifladoss.R;
import br.edu.ladoss.simpifladoss.mvp.HomeMVP;
import br.edu.ladoss.simpifladoss.mvp.presenter.HomePresenterImp;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity implements HomeMVP.View{

    private HomeMVP.Presenter presenter;
    @BindView(R.id.btnCheckin)
    Button btnCheckin;
    @BindView(R.id.content_layout)
    ConstraintLayout content_layout;
    @BindView(R.id.loading_layout)
    LinearLayout loading_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);



        if(savedInstanceState != null){
            HomeMVP.Presenter auxPresenter = (HomeMVP.Presenter) savedInstanceState.getSerializable(HomeMVP.BUNDLE);
            if(auxPresenter != null){
                presenter = auxPresenter;
            }
        }

        if(presenter == null){
            presenter = new HomePresenterImp(this);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(HomeMVP.BUNDLE, presenter);

    }

    @OnClick(R.id.btnCheckin)
    public void openScanner(View view) {
        presenter.openScanner();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        presenter.selectedItem(item);
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(requestCode, resultCode, data);
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

    @Override
    public void showMessage(String msg) {
        Snackbar.make(this.findViewById(android.R.id.content), msg, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void setLoading(boolean isLoading) {
        content_layout.setVisibility(isLoading ? View.GONE: View.VISIBLE);
        loading_layout.setVisibility(isLoading ? View.VISIBLE: View.GONE);
    }

    @Override
    public void showDialogExit(DialogInterface.OnClickListener listenerNeutral, DialogInterface.OnClickListener listenerNegative) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(this.getString(R.string.quit));
        alertDialog.setMessage(presenter.getContext().getString(R.string.quit_confirmation));
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, presenter.getContext().getString(R.string.quit), listenerNeutral);

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, presenter.getContext().getString(R.string.no), listenerNegative);
        alertDialog.show();
    }

}

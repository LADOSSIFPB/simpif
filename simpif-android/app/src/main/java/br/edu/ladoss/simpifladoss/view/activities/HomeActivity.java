package br.edu.ladoss.simpifladoss.view.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.IOException;

import br.edu.ladoss.simpifladoss.R;
import br.edu.ladoss.simpifladoss.mvp.HomeMVP;
import br.edu.ladoss.simpifladoss.mvp.presenter.HomePresenterImp;
import br.edu.ladoss.simpifladoss.network.ConnectionServer;
import retrofit.Call;
import retrofit.Response;

public class HomeActivity extends AppCompatActivity implements HomeMVP.View{

    private HomeMVP.Presenter presenter;

    Button btnCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        presenter = new HomePresenterImp(this);

        btnCheck = (Button) findViewById(R.id.btnCheckin);
    }

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
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if(result != null) {
            if(result.getContents() != null) {
                presenter.showMessage(result.getContents());

                ConnectionServer.getInstance().updateServiceAdress();

                Call<Void> call = ConnectionServer.getInstance().getService().checkin(result.getContents());
                try {
                    Response<Void> response = call.execute();

                    if (response.isSuccess())
                        presenter.showMessage("Checkin realizado com sucesso.");
                    else
                        presenter.showMessage("Checkin falhou.");

                } catch (IOException e) {
                    presenter.showMessage("Erro de conexão");
                }
            }else {
                presenter.showMessage(getString(R.string.canceled));
            };
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
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

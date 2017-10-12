package br.edu.ladoss.simpifladoss.view.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.edu.ladoss.simpifladoss.R;
import br.edu.ladoss.simpifladoss.models.Attendee;
import br.edu.ladoss.simpifladoss.models.Order;
import br.edu.ladoss.simpifladoss.mvp.SearchMVP;
import br.edu.ladoss.simpifladoss.mvp.presenter.SearchPresenterImp;
import br.edu.ladoss.simpifladoss.view.adapters.AttendeeAdapter;
import br.edu.ladoss.simpifladoss.view.callback.RecycleButtonClicked;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity implements SearchMVP.View{

    private SearchMVP.Presenter presenter;

    @BindView(R.id.code_list)
    RecyclerView recycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ButterKnife.bind(this);

        this.presenter = new SearchPresenterImp(this);
    }

    @Override
    public void showMessage(String msg) {
        Snackbar.make(this.findViewById(android.R.id.content), msg, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showDialogConfirmation(DialogInterface.OnClickListener listenerNeutral, DialogInterface.OnClickListener listenerNegative) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(this.getString(R.string.checkin));
        alertDialog.setMessage(presenter.getContext().getString(R.string.checkin_confirmation));
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, getString(R.string.confirm), listenerNeutral);

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, getString(R.string.no), listenerNegative);
        alertDialog.show();
    }

    @Override
    public void updateAttendees(final List<Attendee> attendees) {
        final Context context = getContext();
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                LinearLayoutManager gridLayoutManager = new LinearLayoutManager(getContext());
                gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

                recycle.setLayoutManager(gridLayoutManager);
                recycle.setAdapter(new AttendeeAdapter(context, attendees, new RecycleButtonClicked() {
                    @Override
                    public void onClickCallback(Attendee attendee) {
                        presenter.sendCodeToServer(Integer.toString(attendee.getPrivateRefNum()));
                    }
                }));
                recycle.setVisibility(View.VISIBLE);
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleSearch(intent);
    }

    public void handleSearch(Intent intent){
        if(Intent.ACTION_SEARCH.equalsIgnoreCase(intent.getAction())){
            String query = intent.getStringExtra(SearchManager.QUERY);
            presenter.requestOrderAttendees(query);
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public Context getAppContext() {
        return this.getAppContext();
    }

    @Override
    public AppCompatActivity get() {
        return this;
    }
}

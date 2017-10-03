package br.edu.ladoss.simpifladoss.view.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;

import br.edu.ladoss.simpifladoss.R;
import br.edu.ladoss.simpifladoss.mvp.SearchMVP;
import br.edu.ladoss.simpifladoss.mvp.presenter.SearchPresenterImp;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity implements SearchMVP.View{

    private SearchMVP.Presenter presenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ButterKnife.bind(this);
        toolbar.setTitle(R.string.app_name);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.presenter = new SearchPresenterImp(this);
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
            toolbar.setTitle(query);
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

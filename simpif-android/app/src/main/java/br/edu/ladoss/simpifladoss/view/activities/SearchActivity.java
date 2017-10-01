package br.edu.ladoss.simpifladoss.view.activities;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import br.edu.ladoss.simpifladoss.R;
import br.edu.ladoss.simpifladoss.mvp.SearchMVP;
import br.edu.ladoss.simpifladoss.mvp.presenter.SearchPresenterImp;
import butterknife.BindView;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity implements SearchMVP.View{

    private SearchMVP.Presenter presenter;

    @BindView(R.id.btnSearch)
    Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        this.presenter = new SearchPresenterImp(this);
    }

    @OnClick(R.id.btnSearch)
    public void search(){

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

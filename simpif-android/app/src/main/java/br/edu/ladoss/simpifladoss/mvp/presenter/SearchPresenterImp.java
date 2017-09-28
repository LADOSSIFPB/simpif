package br.edu.ladoss.simpifladoss.mvp.presenter;

import android.content.Context;
import android.widget.Button;

import br.edu.ladoss.simpifladoss.R;
import br.edu.ladoss.simpifladoss.mvp.SearchMVP;
import br.edu.ladoss.simpifladoss.mvp.model.SearchModelImp;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Rennan on 28/09/17.
 */

public class SearchPresenterImp implements SearchMVP.Presenter{

    private SearchMVP.View view;
    private SearchMVP.Model model;

    @BindView(R.id.btnSearch)
    Button btnSearch;

    public SearchPresenterImp(SearchMVP.View view) {
        this.view = view;
        this.model = new SearchModelImp(this);
    }

    @Override
    public Context getContext() {
        return view.getContext();
    }

    @Override
    public void onDestroy() {
        model.onDestroy();
        model = null;
        view = null;
    }

    @OnClick(R.id.btnSearch)
    public void search(){

    }
}

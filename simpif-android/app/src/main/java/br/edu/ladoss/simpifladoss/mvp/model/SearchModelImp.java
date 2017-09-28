package br.edu.ladoss.simpifladoss.mvp.model;

import android.content.Intent;

import br.edu.ladoss.simpifladoss.mvp.SearchMVP;

/**
 * Created by Rennan on 28/09/17.
 */

public class SearchModelImp implements SearchMVP.Model{

    private SearchMVP.Presenter presenter;

    public SearchModelImp(SearchMVP.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onDestroy() {
        presenter = null;
    }
}

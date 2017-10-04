package br.edu.ladoss.simpifladoss.mvp.presenter;

import android.content.Context;
import android.widget.Button;

import java.util.List;

import br.edu.ladoss.simpifladoss.R;
import br.edu.ladoss.simpifladoss.models.Attendee;
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

    public SearchPresenterImp(SearchMVP.View view) {
        this.view = view;
        this.model = new SearchModelImp(this);
    }

    @Override
    public void sendCodeToServer(String code) {
        model.sendCodeToServer(code);
    }

    @Override
    public void onSendError(String message) {
        view.showMessage(message);
    }

    @Override
    public void onSendSucess() {
        view.showMessage(getContext().getString(R.string.success_msg));
    }

    @Override
    public void requestOrderAttendees(String orderRef) {
        model.requestOrderAttendees(orderRef);
    }

    @Override
    public void updateAttendees(List<Attendee> attendees) {
        view.updateAttendees(attendees);
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
}

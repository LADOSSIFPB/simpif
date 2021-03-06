package br.edu.ladoss.simpifladoss.mvp.presenter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Button;

import java.util.List;

import br.edu.ladoss.simpifladoss.R;
import br.edu.ladoss.simpifladoss.models.Attendee;
import br.edu.ladoss.simpifladoss.mvp.SearchMVP;
import br.edu.ladoss.simpifladoss.mvp.model.SearchModelImp;
import br.edu.ladoss.simpifladoss.view.activities.EnterActivity;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Rennan on 28/09/17.
 */

public class SearchPresenterImp implements SearchMVP.Presenter{

    private transient SearchMVP.View view;
    private SearchMVP.Model model;

    public SearchPresenterImp(SearchMVP.View view) {
        this.view = view;
        this.model = new SearchModelImp(this);
    }

    @Override
    public void onSendError(String message) {
        view.showMessage(message);
    }

    @Override
    public void onSendSucess() {
        view.get().finish();
        onDestroy();
    }

    @Override
    public void onClickAttendee(final Attendee attendee) {
        if(attendee.hasArrived()) {
            view.showMessage(getContext().getString(R.string.already_checked));
        }else if(attendee.isCancelled()){
            view.showMessage(getContext().getString(R.string.attendee_cancelled));
        }else{
            final String code = Integer.toString(attendee.getPrivateRefNum());
            DialogInterface.OnClickListener neutral = new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    model.sendCodeToServer(code);
                }
            };

            DialogInterface.OnClickListener dismiss = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            };

            view.showDialogConfirmation(neutral, dismiss);
        }
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

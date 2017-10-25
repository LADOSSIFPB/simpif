package br.edu.ladoss.simpifladoss.mvp;

import android.content.DialogInterface;

import java.util.List;

import br.edu.ladoss.simpifladoss.MVPApp;
import br.edu.ladoss.simpifladoss.models.Attendee;

/**
 * Created by Rennan on 28/09/17.
 */

public interface SearchMVP extends MVPApp {

    interface Model extends MVPApp.Model {
        void sendCodeToServer(String code);
        void requestOrderAttendees(String orderRef);
    }

    interface View extends MVPApp.View {
        void showMessage(String msg);
        void updateAttendees(List<Attendee> attendees);
        void showDialogConfirmation(DialogInterface.OnClickListener listenerNeutral, DialogInterface.OnClickListener listenerNegative);
    }

    interface Presenter extends MVPApp.Presenter {

        void onSendError(String message);
        void onSendSucess();

        void requestOrderAttendees(String orderRef);

        void updateAttendees(List<Attendee> attendees);

        void onClickAttendee(Attendee attendee);
    }

}

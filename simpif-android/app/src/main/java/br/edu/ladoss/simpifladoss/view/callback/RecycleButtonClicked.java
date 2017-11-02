package br.edu.ladoss.simpifladoss.view.callback;

import br.edu.ladoss.simpifladoss.models.Attendee;

/**
 * Created by root on 04/10/17.
 */

public interface RecycleButtonClicked<T> {
    void onClickCallback(T thing);
}
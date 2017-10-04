package br.edu.ladoss.simpifladoss.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.edu.ladoss.simpifladoss.R;
import br.edu.ladoss.simpifladoss.models.Attendee;
import br.edu.ladoss.simpifladoss.view.callback.RecycleButtonClicked;

/**
 * Created by Rennan on 04/10/17.
 */

public class AttendeeAdapter extends RecyclerView.Adapter<AttendeeAdapter.RoomViewHolder>{

    private List<Attendee> attendees;
    private LayoutInflater inflater;
    private RecycleButtonClicked view;

    public AttendeeAdapter(Context context, List<Attendee> myList, RecycleButtonClicked view) {
        if (myList == null) {
            this.attendees = new ArrayList<>();
        } else {
            this.attendees = myList;
        }
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.view = view;
    }

    @Override
    public RoomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_attendee, parent, false);
        RoomViewHolder roomViewHolder = new RoomViewHolder(v);

        return roomViewHolder;
    }

    public void removeAll() {
        int tam = attendees.size();
        for (int i = tam - 1; i >= 0; i--) {
            attendees.remove(i);
        }
        if (tam > 0)
            notifyItemRangeRemoved(0, tam);
    }

    @Override
    public void onBindViewHolder(RoomViewHolder holder, int position) {
        holder.nome.setText(attendees.get(position).getFirstName() + " " + attendees.get(position).getLastName());
        holder.refNumber.setText(Integer.toString(attendees.get(position).getPrivateRefNum()));
    }

    @Override
    public int getItemCount() {
        return attendees.size();
    }

    public class RoomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nome, refNumber;

        public RoomViewHolder(View item) {
            super(item);
            nome = (TextView) item.findViewById(R.id.nomeAttendee);
            refNumber = (TextView) item.findViewById(R.id.privateNumber);
            item.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            view.onClickCallback(attendees.get(getAdapterPosition()));
        }
    }

}

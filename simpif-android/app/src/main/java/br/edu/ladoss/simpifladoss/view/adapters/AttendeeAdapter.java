package br.edu.ladoss.simpifladoss.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.MessageFormat;
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
    private RecycleButtonClicked<Attendee> view;

    public AttendeeAdapter(Context context, List<Attendee> myList, RecycleButtonClicked<Attendee> view) {
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

        return new RoomViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RoomViewHolder holder, int position) {
        holder.nome.setText(MessageFormat.format("{0} {1}", attendees.get(position).getFirstName(), attendees.get(position).getLastName()));
        holder.ticketTitle.setText(attendees.get(position).getTicketTitle());
        if(attendees.get(position).hasArrived())
            holder.checked.setChecked(true);
    }

    @Override
    public int getItemCount() {
        return attendees.size();
    }

    public class RoomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nome, ticketTitle;
        CheckBox checked;

        RoomViewHolder(View item) {
            super(item);
            nome = item.findViewById(R.id.opcaoDia);
            ticketTitle = item.findViewById(R.id.ticketTitle);
            checked = item.findViewById(R.id.checked);
            item.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            view.onClickCallback(attendees.get(getAdapterPosition()));
        }
    }

}

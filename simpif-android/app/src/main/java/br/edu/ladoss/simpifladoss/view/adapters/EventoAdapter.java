package br.edu.ladoss.simpifladoss.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.edu.ladoss.simpifladoss.R;
import br.edu.ladoss.simpifladoss.models.Evento;
import br.edu.ladoss.simpifladoss.view.callback.RecycleButtonClicked;

/**
 * Created by Rennan on 13/11/17.
 */

public class EventoAdapter extends RecyclerView.Adapter<EventoAdapter.RoomViewHolder>{

    private List<Evento> eventos;
    private LayoutInflater inflater;
    private RecycleButtonClicked<Evento> view;

    public EventoAdapter(Context context, List<Evento> myList, RecycleButtonClicked view) {
        if (myList == null) {
            this.eventos = new ArrayList<>();
        } else {
            this.eventos = myList;
        }
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.view = view;
    }

    @Override
    public RoomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_event, parent, false);

        return new RoomViewHolder(v);
    }

    public void removeAll() {
        int tam = eventos.size();
        for (int i = tam - 1; i >= 0; i--) {
            eventos.remove(i);
        }
        if (tam > 0)
            notifyItemRangeRemoved(0, tam);
    }

    @Override
    public void onBindViewHolder(RoomViewHolder holder, int position) {
        SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");

        Evento evento = eventos.get(position);
        holder.nome.setText(evento.getNome());
        holder.periodo.setText(MessageFormat.format("De {0} até {1}", dt.format(evento.getInicio()), dt.format(evento.getFim())));
    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }

    public class RoomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nome, periodo;

        public RoomViewHolder(View item) {
            super(item);
            nome = (TextView) item.findViewById(R.id.nomeEvento);
            periodo = (TextView) item.findViewById(R.id.inicioEvento);
            item.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            view.onClickCallback(eventos.get(getAdapterPosition()));
        }
    }

}
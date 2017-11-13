package br.edu.ladoss.simpifladoss.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        RoomViewHolder roomViewHolder = new RoomViewHolder(v);

        return roomViewHolder;
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
        SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        holder.nome.setText(eventos.get(position).getNome());
        holder.inicio.setText("Começa em: " + dt.format(eventos.get(position).getInicio()));
        holder.fim.setText("Termina em: " + dt.format(eventos.get(position).getFim()));
    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }

    public class RoomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nome, inicio, fim;

        public RoomViewHolder(View item) {
            super(item);
            nome = (TextView) item.findViewById(R.id.nomeEvento);
            inicio = (TextView) item.findViewById(R.id.inicioEvento);
            fim = (TextView) item.findViewById(R.id.fimEvento);
            item.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            view.onClickCallback(eventos.get(getAdapterPosition()));
        }
    }

}
package br.edu.ladoss.simpifladoss.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import br.edu.ladoss.simpifladoss.R;
import br.edu.ladoss.simpifladoss.models.Apresentacao;

/**
 * Created by Rennan on 17/11/17.
 */

public class ApresentacaoAdapter extends RecyclerView.Adapter<ApresentacaoAdapter.RoomViewHolder>{

    private List<Apresentacao> apresentacoes;
    private LayoutInflater inflater;

    ApresentacaoAdapter(Context context, List<Apresentacao> myList) {
        if (myList == null) {
            this.apresentacoes = new ArrayList<>();
        } else {
            this.apresentacoes = myList;
        }
        Collections.sort(apresentacoes);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public RoomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_apresentacao, parent, false);

        return new RoomViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RoomViewHolder holder, int position) {
        Apresentacao apresentacao = apresentacoes.get(position);
        holder.titulo.setText(apresentacoes.get(position).getTitulo());
        holder.autor.setText((apresentacao.getAutores().size() > 0 ? apresentacao.getAutores().get(0).getNome() : "Sem Autor"));
        holder.trilha.setText(apresentacao.getTrilha().getNome());
        holder.local.setText("Sala: " + (apresentacao.getSala() != null ? apresentacao.getSala().getNome() : "Sem Sala"));
        try {
            DateFormat formatter = new SimpleDateFormat("HH:mm");
            Date inicio = (Date)formatter.parse(apresentacao.getHoraInicio());
            Date fim = (Date)formatter.parse(apresentacao.getHoraFim());
            holder.periodo.setText(MessageFormat.format("{0} - {1}", formatter.format(inicio), formatter.format(fim)));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return apresentacoes.size();
    }

    class RoomViewHolder extends RecyclerView.ViewHolder {
        TextView titulo, autor, trilha, local, periodo;

        RoomViewHolder(View item) {
            super(item);
            titulo = item.findViewById(R.id.tituloApresentacao);
            autor = item.findViewById(R.id.autor);
            trilha = item.findViewById(R.id.trilha);
            local = item.findViewById(R.id.local);
            periodo = item.findViewById(R.id.periodo);
        }
    }

}
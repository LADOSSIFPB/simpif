package br.edu.ladoss.simpifladoss.view.adapters;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import br.edu.ladoss.simpifladoss.R;
import br.edu.ladoss.simpifladoss.models.Apresentacao;
import br.edu.ladoss.simpifladoss.models.Cronograma;

/**
 * Created by juan on 18/11/17.
 */

public class CronogramaAdapter extends RecyclerView.Adapter<CronogramaAdapter.CronogramaViewHolder>{

    private List<List<Apresentacao>> listCrono;
    private LayoutInflater inflater;

    public CronogramaAdapter(Context context, List<Apresentacao> myList) {
        this.listCrono = new ArrayList<>();
        if (myList != null) {
            setUpList(myList);
        }
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    private void setUpList(List<Apresentacao> myList) {
        Hashtable<Cronograma, List<Apresentacao>> hashtable = new Hashtable<>();
        for (Apresentacao apresentacao : myList) {
            if (hashtable.containsKey(apresentacao.getCronograma())){
                hashtable.get(apresentacao.getCronograma()).add(apresentacao);
            } else {
                LinkedList<Apresentacao> list = new LinkedList<>();
                list.add(apresentacao);
                hashtable.put(apresentacao.getCronograma(), list);
            }
        }
        for (Cronograma cronograma: hashtable.keySet()){
            listCrono.add(hashtable.get(cronograma));
        }
        Collections.sort(listCrono, new Comparator<List<Apresentacao>>() {
            @Override
            public int compare(List<Apresentacao> o1, List<Apresentacao> o2) {
                return o1.get(0).getCronograma().compareTo(o2.get(0).getCronograma());
            }
        });
    }

    @Override
    public CronogramaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_cronograma, parent, false);
        return new CronogramaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CronogramaViewHolder holder, int position) {
        Cronograma key = listCrono.get(position).get(0).getCronograma();
        holder.name.setText(key.getNome());
        holder.list_presentations.setAdapter(new ApresentacaoAdapter(inflater.getContext(), listCrono.get(position)));
    }

    @Override
    public int getItemCount() {
        return listCrono.size();
    }

    class CronogramaViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        RecyclerView list_presentations;

        CronogramaViewHolder(View item) {
            super(item);
            name = item.findViewById(R.id.name_field);
            list_presentations = item.findViewById(R.id.crono_list);
            LinearSnapHelper snapHelper = new LinearSnapHelper();
            snapHelper.attachToRecyclerView(list_presentations);
            list_presentations.setLayoutManager(
                    new GridLayoutManager(item.getContext(), 1, LinearLayoutManager.HORIZONTAL, false)
            );
        }
    }
}

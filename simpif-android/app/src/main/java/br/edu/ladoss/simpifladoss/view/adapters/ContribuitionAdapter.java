package br.edu.ladoss.simpifladoss.view.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import br.edu.ladoss.simpifladoss.R;
import br.edu.ladoss.simpifladoss.models.Contribuitor;
import br.edu.ladoss.simpifladoss.view.callback.RecycleButtonClicked;
import de.hdodenhof.circleimageview.CircleImageView;;

/**
 * Created by juan on 01/11/17.
 */

public class ContribuitionAdapter extends RecyclerView.Adapter<ContribuitionAdapter.ContribuitorViewHolder>{

    private ArrayList<Contribuitor> contribuitors;
    private LayoutInflater inflater;
    private RecycleButtonClicked<Contribuitor> recycleButtonClicked;

    public ContribuitionAdapter(Context context, RecycleButtonClicked<Contribuitor> recycleButtonClicked) {
        contribuitors = new ArrayList<>();
        this.recycleButtonClicked = recycleButtonClicked;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        contribuitors.add(new Contribuitor(
                "Rennan R. de Freitas",
                "https://github.com/RRFreitas",
                "Desenvolvedor Android",
                "https://avatars1.githubusercontent.com/u/15524562?s=400&v=4"));
        contribuitors.add(new Contribuitor(
                "Juan V. L. B. e Barros",
                "https://github.com/JuanBarros2",
                "Desenvolvedor Android",
                "https://avatars0.githubusercontent.com/u/11728655?s=460&v=4"));
        contribuitors.add(new Contribuitor(
                "Marcos V. Santos Silva",
                "https://github.com/marcossilvaxx",
                "Desenvolvedor Back-End",
                "https://avatars2.githubusercontent.com/u/20626761?s=400&v=4"));
        contribuitors.add(new Contribuitor(
                "Lucas Dantas de Araújo",
                "https://github.com/lucasdantas2014",
                "Desenvolvedor Back-End",
                "https://avatars3.githubusercontent.com/u/21109930?s=400&v=4"));
        contribuitors.add(new Contribuitor(
                "Rhavy Maia Guedes",
                "https://github.com/rhavymaia",
                "Orientador",
                "https://avatars0.githubusercontent.com/u/1872194?s=400&v=4"));

        Collections.sort(contribuitors);
    }

    @Override
    public ContribuitorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_contribuitor, parent, false);
        ContribuitorViewHolder roomViewHolder = new ContribuitorViewHolder(v);

        return roomViewHolder;
    }

    @Override
    public void onBindViewHolder(ContribuitorViewHolder holder, int position) {
        Contribuitor contribuitor = contribuitors.get(position);
        holder.name.setText(contribuitor.getName());
        holder.github.setText(contribuitor.getGithub());
        holder.function.setText(contribuitor.getFunction());
        Picasso.with(inflater.getContext()).load(contribuitor.getProfile_image())
                .placeholder(R.drawable.ic_mood_black_24dp)
                .error(R.drawable.ic_mood_black_24dp)
                .into(holder.profile);

    }

    @Override
    public int getItemCount() {
        return contribuitors.size();
    }

    public class ContribuitorViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView github;
        TextView function;
        CircleImageView profile;

        public ContribuitorViewHolder(View item) {
            super(item);
            name = (TextView) item.findViewById(R.id.name);
            github = (TextView) item.findViewById(R.id.github_link);
            github.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recycleButtonClicked.onClickCallback(contribuitors.get(getAdapterPosition()));
                }

            });
            function = (TextView) item.findViewById(R.id.function);
            profile = (CircleImageView) item.findViewById(R.id.profile_image);
        }
    }

}

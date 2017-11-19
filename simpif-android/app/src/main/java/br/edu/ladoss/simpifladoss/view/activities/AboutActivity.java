package br.edu.ladoss.simpifladoss.view.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;

import br.edu.ladoss.simpifladoss.R;
import br.edu.ladoss.simpifladoss.models.Contribuitor;
import br.edu.ladoss.simpifladoss.view.adapters.ContribuitionAdapter;
import br.edu.ladoss.simpifladoss.view.callback.RecycleButtonClicked;

public class AboutActivity extends AppCompatActivity implements RecycleButtonClicked<Contribuitor> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        RecyclerView recyclerView = findViewById(R.id.contribuitors);
        ContribuitionAdapter adapter = new ContribuitionAdapter(this, this);
        LinearSnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClickCallback(Contribuitor thing) {
        Intent browser= new Intent(Intent.ACTION_VIEW, Uri.parse(thing.getGithub()));
        startActivity(browser);
    }
}

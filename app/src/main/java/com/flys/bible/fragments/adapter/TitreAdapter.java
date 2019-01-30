package com.flys.bible.fragments.adapter;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flys.bible.R;
import com.flys.bible.entities.Titre;
import com.flys.bible.entities.Verset;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by AMADOU BAKARI on 12/09/2018.
 */

public class TitreAdapter extends RecyclerView.Adapter<TitreAdapter.Holderview> {
    private List<Titre> listModels;
    private Context context;
    private int resourceId;
    private VersetsAdapter versetsAdapter;

    public TitreAdapter(List<Titre> listModels, Context context) {
        this.listModels = listModels;
        this.context = context;
        this.resourceId = resourceId;

    }

    @Override
    public Holderview onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.titre_item, parent, false);

        return new Holderview(layout);
    }

    @Override
    public void onBindViewHolder(Holderview holder, final int position) {

        Titre titre = listModels.iterator().next();
        RecyclerView recyclerView = holder.recyclerView;
        holder.nom.setText(titre.getNom());


        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);

        recyclerView.setLayoutManager(linearLayoutManager);
        versetsAdapter = new VersetsAdapter(new ArrayList(titre.getVersets()) , context);
        recyclerView.setAdapter(versetsAdapter);

    }

    @Override
    public int getItemCount() {
        int result = 0;
        if (!listModels.isEmpty()) {
            result = listModels.size();
        }
        return result;
    }


    public void setFilter(List<Titre> titres) {
        listModels = new ArrayList<>();
        listModels.addAll(titres);
        notifyDataSetChanged();

    }


    class Holderview extends RecyclerView.ViewHolder {

        TextView nom;
        RecyclerView recyclerView;

        public Holderview(View itemView) {
            super(itemView);

            nom = itemView.findViewById(R.id.titre_nom);
            recyclerView = itemView.findViewById(R.id.recyclerview);

        }
    }
}

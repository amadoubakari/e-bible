package com.flys.bible.fragments.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flys.bible.R;
import com.flys.bible.entities.Verset;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by AMADOU BAKARI on 09/09/2018.
 */

public class VersetsAdapter extends RecyclerView.Adapter<VersetsAdapter.Holderview> {
    private List<Verset> listModels;
    private Context context;
    private int resourceId;

    private Calendar calendar;


    public VersetsAdapter(List<Verset> listModels, Context context) {
        this.listModels = listModels;
        this.context = context;
        this.resourceId = resourceId;
    }

    @Override
    public Holderview onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.verset_item, parent, false);

        return new Holderview(layout);
    }

    @Override
    public void onBindViewHolder(Holderview holder, final int position) {
        Verset verset=listModels.get(position);
        holder.number.setText(""+verset.getNumero());
        holder.description.setText(verset.getDescription());


    }

    @Override
    public int getItemCount() {
        return listModels.size();
    }


    public void setFilter(List<Verset> versets) {
        listModels = new ArrayList<>();
        listModels.addAll(versets);
        notifyDataSetChanged();

    }


    class Holderview extends RecyclerView.ViewHolder {

        TextView number;
        TextView description;

        public Holderview(View itemView) {
            super(itemView);

            number = itemView.findViewById(R.id.number);
            description = itemView.findViewById(R.id.description);

        }
    }
}

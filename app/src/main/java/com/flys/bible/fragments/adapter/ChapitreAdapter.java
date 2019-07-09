package com.flys.bible.fragments.adapter;

import android.content.Context;
import androidx.viewpager.widget.PagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flys.bible.R;
import com.flys.bible.entities.Chapitre;
import com.flys.bible.entities.Titre;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by User on 21/09/2018.
 */

public class ChapitreAdapter extends PagerAdapter {

    private Context context;
    private List<Chapitre> chapitres;

    public ChapitreAdapter(Context context, List<Chapitre> chapitres) {
        this.context = context;
        this.chapitres = chapitres;
    }


    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        Chapitre chapitre = chapitres.get(position);
        View myImageLayout = LayoutInflater.from(context).inflate(R.layout.chapitre_slider_item, view, false);
        TextView chapitreName=myImageLayout.findViewById(R.id.chapitre);
        chapitreName.setText(chapitre.getNom());
        RecyclerView recyclerView = myImageLayout.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        TitreAdapter titreAdapter = new TitreAdapter(new ArrayList(chapitre.getTitres()) , context);
        recyclerView.setAdapter(titreAdapter);
        view.addView(myImageLayout, 0);
        return myImageLayout;
    }


    @Override
    public int getCount() {
        int result = 0;
        if (!chapitres.isEmpty()) {
            result = chapitres.size();
        }
        return result;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public void setFilter(List<Chapitre> listModelsTasks) {
        chapitres = new ArrayList<>();
        chapitres.addAll(listModelsTasks);
        notifyDataSetChanged();

    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}

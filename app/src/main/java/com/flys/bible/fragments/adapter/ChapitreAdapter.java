package com.flys.bible.fragments.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flys.bible.R;
import com.flys.bible.entities.Chapitre;

import java.util.List;

/**
 * Created by User on 21/09/2018.
 */

public class ChapitreAdapter extends PagerAdapter {

    private Context context;
    private List<Chapitre> chapitres;
    private TitreAdapter titreAdapter;

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
        titreAdapter = new TitreAdapter(chapitre.getTitres(), context);
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

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}

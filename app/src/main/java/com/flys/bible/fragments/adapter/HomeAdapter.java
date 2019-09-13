package com.flys.bible.fragments.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.flys.bible.R;
import com.flys.bible.entities.DailyVerset;
import com.flys.bible.utils.FileUtils;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.Holderview> {

    private List<DailyVerset> dailyVersets;
    private Context context;

    public HomeAdapter(List<DailyVerset> dailyVersets, Context context) {
        this.dailyVersets = dailyVersets;
        this.context = context;
    }

    @NonNull
    @Override
    public Holderview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item, parent, false);

        return new Holderview(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull Holderview holder, int position) {
        DailyVerset dailyVerset = dailyVersets.get(position);
        holder.title.setText(dailyVerset.getVerse().getHuman_reference());
        holder.message.setText(dailyVerset.getVerse().getText());
        holder.image.setImageDrawable(FileUtils.loadImageFromStorage("bible",dailyVerset.getVerse().getHuman_reference()+".png",context));
    }

    @Override
    public int getItemCount() {
        if(!dailyVersets.isEmpty()){
            return dailyVersets.size();
        }
        return 0;
    }


    class Holderview extends RecyclerView.ViewHolder {

        TextView title;
        TextView message;
        ImageView icon;
        ImageView image;
        Button btn;

        public Holderview(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            message = itemView.findViewById(R.id.message);
            icon = itemView.findViewById(R.id.icon);
            image = itemView.findViewById(R.id.image);
            btn = itemView.findViewById(R.id.button);
        }
    }

}

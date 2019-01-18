package com.flys.bible.fragments.adapter;

import android.content.Context;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.flys.bible.R;
import com.flys.bible.Utils;
import com.flys.bible.entities.Verset;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

/**
 * Created by AMADOU BAKARI on 09/09/2018.
 */

public class VersetsAdapter extends RecyclerView.Adapter<VersetsAdapter.Holderview> {
    private Collection<Verset> listModels;
    private Context context;
    private int resourceId;

    private Calendar calendar;


    public VersetsAdapter(Collection<Verset> listModels, Context context) {
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
        Verset verset = listModels.iterator().next();
        holder.number.setText("" + verset.getNumero());
        holder.description.setText(verset.getDescription());

        holder.menu.setOnClickListener(view -> {
            Context wrapper = new ContextThemeWrapper(context, R.style.popupmenu);
            PopupMenu popupMenu = new PopupMenu(wrapper, holder.menu);
            popupMenu.setGravity(10);
            popupMenu.inflate(R.menu.option_menu);
            popupMenu.setOnMenuItemClickListener(menuItem -> {
                switch (menuItem.getItemId()) {
                    case R.id.option_menu_share:
                        Utils.shareText(context,"Bible",verset.getDescription(),"Titre");
                        break;
                    case R.id.option_menu_mark:
                        Toast.makeText(context, "Marquer", Toast.LENGTH_LONG).show();
                        break;
                    default:
                        break;
                }
                return false;
            });
            popupMenu.show();
        });


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
        ImageView menu;

        public Holderview(View itemView) {
            super(itemView);

            number = itemView.findViewById(R.id.number);
            description = itemView.findViewById(R.id.description);
            menu = itemView.findViewById(R.id.option_menu_verset);
        }
    }
}

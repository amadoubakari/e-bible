package com.flys.bible.fragments.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.flys.bible.R;
import com.flys.bible.Utils;
import com.flys.bible.entities.Verset;
import com.flys.bible.utils.CustomTypefaceSpan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AMADOU BAKARI on 09/09/2018.
 */

public class VersetsAdapter extends RecyclerView.Adapter<VersetsAdapter.Holderview> {
    private List<Verset> listModels;
    private Context context;


    public VersetsAdapter(List<Verset> listModels, Context context) {
        this.listModels = listModels;
        this.context = context;
    }

    @Override
    public Holderview onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.verset_item, parent, false);

        return new Holderview(layout);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onBindViewHolder(Holderview holder, final int position) {
        Verset verset = listModels.get(position);
        holder.number.setText("" + verset.getNumero());
        holder.description.setText(verset.getDescription());

        holder.menu.setOnClickListener(view -> {
            Context wrapper = new ContextThemeWrapper(context, R.style.popupmenu);
            PopupMenu popupMenu = new PopupMenu(wrapper, holder.menu);
            popupMenu.setGravity(10);
            popupMenu.inflate(R.menu.option_menu);
            applyFontToMenu(popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(menuItem -> {
                switch (menuItem.getItemId()) {
                    case R.id.option_menu_share:
                        Utils.shareText(context,"ƁIMUTOHO MIPAL",verset.getTitre().getChapitre().getNom().substring(0,3)+" "+verset.getTitre().getChapitre().getNumero()+":"+verset.getNumero()+"\n"+verset.getDescription(),"ƁIMUTOHO MIPAL");
                        break;
                    case R.id.option_menu_mark:
                        Toast.makeText(context, "Marquer", Toast.LENGTH_LONG).show();
                        break;
                    default:
                        break;
                }
                return false;
            });
            @SuppressLint("RestrictedApi") MenuPopupHelper menuHelper = new MenuPopupHelper(wrapper, (MenuBuilder) popupMenu.getMenu(), holder.menu);
            menuHelper.setForceShowIcon(true);
            menuHelper.show();
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

    private void applyFontToMenu(Menu menu) {
        for (int i=0;i<menu.size();i++){
            MenuItem menuItem = menu.getItem(i);

            //for aapplying a font to subMenu ...
            SubMenu subMenu = menuItem.getSubMenu();
            if (subMenu!=null && subMenu.size() >0 ) {
                for (int j=0; j <subMenu.size();j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                    applyFontToMenuItem(subMenuItem);
                }
            }
            applyFontToMenuItem(menuItem);
        }
    }

    private void applyFontToMenuItem(MenuItem mi) {
        Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/poppins_light.ttf");
        SpannableString mNewTitle = new SpannableString(mi.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan("", font), 0, mNewTitle.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);
    }


}

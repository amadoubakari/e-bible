package com.flys.bible.fragments.behavior;

import android.graphics.Rect;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flys.bible.R;
import com.flys.bible.Utils;
import com.flys.bible.architecture.core.AbstractFragment;
import com.flys.bible.architecture.custom.CoreState;
import com.flys.bible.entities.Titre;
import com.flys.bible.entities.Verset;
import com.flys.bible.fragments.adapter.TitreAdapter;
import com.flys.bible.fragments.adapter.VersetsAdapter;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.OptionsMenuItem;
import org.androidannotations.annotations.ViewById;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AMADOU BAKARI on 09/09/2018.
 */

@EFragment(R.layout.fragment_main_layout)
@OptionsMenu(R.menu.menu_main)
public class MainFragment extends AbstractFragment {

    @ViewById(R.id.recyclerview)
    protected RecyclerView recyclerview;

    //private VersetsAdapter versetsAdapter;
    private TitreAdapter titreAdapter;

    private List<Titre> listModels;

    SearchView searchView;

    @OptionsMenuItem(R.id.search)
    protected MenuItem menuItem;

    @Override
    public CoreState saveFragment() {
        return new CoreState();
    }

    @Override
    protected int getNumView() {
        return 0;
    }

    @Override
    protected void initFragment(CoreState previousState) {
        listModels=new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        String jsonInput = Utils.loadJSONFromAsset(activity, "chapitre.json");
        try {
            List<Titre> data = mapper.readValue(jsonInput, new TypeReference<List<Titre>>(){});
           listModels=data;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void initView(CoreState previousState) {
        recyclerview.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        recyclerview.setLayoutManager(linearLayoutManager);
        titreAdapter = new TitreAdapter(listModels, activity);
        recyclerview.setAdapter(titreAdapter);
    }

    @Override
    protected void updateOnSubmit(CoreState previousState) {

    }

    @Override
    protected void updateOnRestore(CoreState previousState) {

    }

    @Override
    protected void notifyEndOfUpdates() {

    }

    @Override
    protected void notifyEndOfTasks(boolean runningTasksHaveBeenCanceled) {

    }


    @OptionsItem(R.id.search)
    protected void doRechercher() {
        // on récupère le client choisi
        searchView = (SearchView) menuItem.getActionView();
        changeSearchTextColor(searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!searchView.isIconified()) {
                    searchView.setIconified(true);
                }
                menuItem.collapseActionView();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return true;
            }
        });

    }

    /**
     *
     * @param view
     */
    private void changeSearchTextColor(View view) {
        if (view != null) {
            if (view instanceof TextView) {
                ((TextView) view).setTextColor(getResources().getColor(R.color.grey_600));
                ((TextView) view).setTextSize(14);
                ((TextView) view).setBackgroundColor(getResources().getColor(R.color.white));
            } else if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    changeSearchTextColor(viewGroup.getChildAt(i));
                }
            }
        }
    }
}

package com.flys.bible.fragments.behavior;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flys.bible.R;
import com.flys.bible.Utils;
import com.flys.bible.architecture.core.AbstractFragment;
import com.flys.bible.architecture.custom.CoreState;
import com.flys.bible.entities.Chapitre;
import com.flys.bible.fragments.adapter.ChapitreAdapter;
import com.flys.bible.utils.DepthPageTransformer;

import org.androidannotations.annotations.Click;
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
public class MainFragment extends AbstractFragment{

    @ViewById(R.id.viewPager)
    protected ViewPager viewPager;

    @ViewById(R.id.next)
    protected ImageView next;

    @ViewById(R.id.previous)
    protected ImageView previous;

    @ViewById(R.id.chapitre)
    protected TextView chapitre;

    @OptionsMenuItem(R.id.search)
    protected MenuItem menuItem;

    private List<Chapitre> listModels;

    private ChapitreAdapter chapitreAdapter;

    SearchView searchView;
    private static int currentItem;


    @Click(R.id.next)
    protected void nextChapitre() {
        Log.e(getClass().getSimpleName(),"next");
        currentItem=viewPager.getCurrentItem();
        if(currentItem<=listModels.size()-1){
            viewPager.setCurrentItem(currentItem++);
            chapitre.setText(listModels.get(viewPager.getCurrentItem()).getNom());

        }


    }

    @Click(R.id.previous)
    protected void previousChapitre() {
        Log.e(getClass().getSimpleName(),"previous");
        currentItem=viewPager.getCurrentItem();
        if(currentItem>0){
            viewPager.setCurrentItem(currentItem--);
            chapitre.setText(listModels.get(viewPager.getCurrentItem()).getNom());

        }

    }

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
        listModels = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        String jsonInput = Utils.loadJSONFromAsset(activity, "chapitre.json");
        try {
            listModels = mapper.readValue(jsonInput, new TypeReference<List<Chapitre>>() {
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void initView(CoreState previousState) {
        chapitreAdapter = new ChapitreAdapter(activity, listModels);
        viewPager.setAdapter(chapitreAdapter);
        viewPager.setPageTransformer(true, new DepthPageTransformer());
        Log.e(getClass().getSimpleName(), "CurrentItem : "+viewPager.getCurrentItem());

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

package com.flys.bible.fragments.behavior;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.widget.SearchView;

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
import com.flys.bible.config.InitApp;
import com.flys.bible.dao.db.ifaces.TitreDao;
import com.flys.bible.dao.db.impl.TitreDaoImpl;
import com.flys.bible.entities.AppConfig;
import com.flys.bible.dao.db.ifaces.AppConfigDao;
import com.flys.bible.dao.db.ifaces.ChapitreDao;
import com.flys.bible.dao.db.impl.AppConfigDaoImpl;
import com.flys.bible.dao.db.impl.ChapitreDaoImpl;
import com.flys.bible.entities.Chapitre;
import com.flys.bible.entities.Livre;
import com.flys.bible.entities.Titre;
import com.flys.bible.entities.Verset;
import com.flys.bible.fragments.adapter.ChapitreAdapter;
import com.flys.bible.utils.DepthPageTransformer;
import com.flys.generictools.dao.daoException.DaoException;
import com.j256.ormlite.dao.ForeignCollection;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.OptionsMenuItem;
import org.androidannotations.annotations.ViewById;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Created by AMADOU BAKARI on 09/09/2018.
 */

@EFragment(R.layout.fragment_main_layout)
@OptionsMenu(R.menu.menu_main)
public class MainFragment extends AbstractFragment {

    @ViewById(R.id.viewPager)
    protected ViewPager viewPager;

    @ViewById(R.id.title)
    protected TextView title;

    @ViewById(R.id.next)
    protected ImageView next;

    @ViewById(R.id.previous)
    protected ImageView previous;

    @ViewById(R.id.chapitre)
    protected TextView chapitre;

    @OptionsMenuItem(R.id.search)
    protected MenuItem menuItem;

    private List<Chapitre> listModels;

    private static List<Chapitre> chapitres;

    private ChapitreAdapter chapitreAdapter;

    @Bean(ChapitreDaoImpl.class)
    protected ChapitreDao chapitreDao;

    @Bean(TitreDaoImpl.class)
    protected TitreDao titreDao;

    @Bean(InitApp.class)
    protected InitApp initApp;


    SearchView searchView;


    @Click(R.id.next)
    protected void nextChapitre() {
        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
    }

    @Click(R.id.previous)
    protected void previousChapitre() {
        viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
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
        ((AppCompatActivity) mainActivity).getSupportActionBar().show();
        listModels = new ArrayList<>();
    }

    @Override
    protected void initView(CoreState previousState) {

        //If it's the first time to come to the application install it
        if (!initApp.init()) {
            Log.e(getClass().getSimpleName(), "Not installed updateOnRestore !");
            //Installation
            initApp.install();

        }

        //Récuperation des chapitres

        if (session.getChapitres() != null && session.getChapitres().isEmpty()) {

        } else {
            try {
                chapitres = listModels = chapitreDao.getAll();
            } catch (DaoException e) {
                e.printStackTrace();
            }
        }

        chapitreAdapter = new ChapitreAdapter(activity, listModels);
        viewPager.setAdapter(chapitreAdapter);
        viewPager.setPageTransformer(true, new DepthPageTransformer());
        title.setText(listModels.get(viewPager.getCurrentItem()).getNom());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {
            }

            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            public void onPageSelected(int position) {
                title.setText(listModels.get(position).getNom());
            }
        });


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
                chapitreAdapter.setFilter(filter(listModels, newText));
                viewPager.setAdapter(chapitreAdapter);
                return true;
            }


        });


        searchView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {

            }

            @Override
            public void onViewDetachedFromWindow(View v) {
                try {
                    chapitreAdapter = new ChapitreAdapter(activity, chapitreDao.getAll());
                } catch (DaoException e) {
                    e.printStackTrace();
                }
                viewPager.setAdapter(chapitreAdapter);
                viewPager.setPageTransformer(true, new DepthPageTransformer());
            }
        });

    }

    /**
     * @param view
     */
    private void changeSearchTextColor(View view) {
        if (view != null) {
            if (view instanceof TextView) {
                ((TextView) view).setTextColor(ContextCompat.getColor(activity, R.color.grey_600));
                ((TextView) view).setTextSize(14);
                view.setBackgroundColor(ContextCompat.getColor(activity, R.color.white));
            } else if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    changeSearchTextColor(viewGroup.getChildAt(i));
                }
            }
        }
    }

    /**
     * @param chapitres
     * @param query
     * @return
     */
    private List<Chapitre> filter(Collection<Chapitre> chapitres, final String query) {

        final SortedSet<Chapitre> chapitreList = new TreeSet<>();

        if (!query.isEmpty()) {
            chapitres.forEach(chapitre -> {
                Chapitre chapitre1 = chapitre;
                List<Titre> titres = new ArrayList<>();
                chapitre.getTitres().forEach(titre -> {
                    Set<Verset> versets = titre.getVersets()
                            .stream()
                            .filter(verset -> verset.getDescription().toLowerCase().startsWith(query.toLowerCase()) || verset.getDescription().toLowerCase().contains(query.toLowerCase()))
                            .collect(Collectors.toSet());
                    if (!versets.isEmpty()) {
                        Titre titre1 = titre;
                        titre1.setVersets(versets);
                        titres.add(titre1);
                        chapitre1.setTitres(titres);
                        chapitreList.add(chapitre1);
                    }
                });
            });

            return chapitreList.stream()
                    .sorted(Comparator.reverseOrder())
                    .peek(chapitre1 -> Log.e(getClass().getSimpleName(), "----------------------- chapitre peek :" + chapitre1.getNumero()))
                    .collect(Collectors.toList());
        } else {
            return chapitres.stream().collect(Collectors.toList());
        }

    }


}

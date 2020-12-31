package com.flys.bible.fragments.behavior;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.widget.SearchView;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
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
import com.flys.bible.utils.EApplicationContext;
import com.flys.bible.utils.RxSearchObservable;
import com.flys.generictools.dao.daoException.DaoException;
import com.j256.ormlite.dao.ForeignCollection;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.OptionsMenuItem;
import org.androidannotations.annotations.ViewById;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.core.Scheduler;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

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
        //Installation de l'application
        if (!session.isInstalled()) {
            install();
        }
        //Récuperation des chapitres
        if (!session.getChapitres().isEmpty()) {
            chapitres = listModels = session.getChapitres();
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

    public void install() {
        //Chargement de la base de données
        ObjectMapper mapper = new ObjectMapper();
        Livre livre = null;
        try {
            livre = mapper.readValue(activity.getAssets().open("new_testament/mathieu.json"), new TypeReference<Livre>() {
            });
            session.setChapitres(livre.getChapitres());
            session.setInstalled(true);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initSearchFeatureNew() {
        AtomicReference<String> queryWords = new AtomicReference<>();
        //Launch the loader
        beginRunningTasks(1);
        RxSearchObservable.fromSearchView(searchView)
                .debounce(1500, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .switchMap((Func1<String, Observable<List<Chapitre>>>) query -> null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(chapters -> {

                });
    }

    List<Chapitre> searchChapters(List<Chapitre> chapitres,String words) {
        List<Chapitre> result=new ArrayList<>();
        chapitres.parallelStream()
                .forEach(chapitre -> {
                    Chapitre chapterLocal = new Chapitre();
                    chapterLocal.setNumero(chapitre.getNumero());
                    chapterLocal.setNom(chapitre.getNom());
                    chapitre.getTitres().forEach(titre -> {
                        List<Verset> versets=new ArrayList<>();
                        titre.getVersets().stream().forEach(verset -> {
                            if(true){
                                versets.add(verset);
                            }
                        });
                        if(!versets.isEmpty()){
                            titre.setVersets(versets);
                            chapitre.getTitres().add(titre);
                            result.add(chapitre);
                        }
                    });
                });
        return null;
    }
}

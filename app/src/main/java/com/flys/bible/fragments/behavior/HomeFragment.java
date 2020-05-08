package com.flys.bible.fragments.behavior;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.flys.bible.R;
import com.flys.bible.Utils;
import com.flys.bible.architecture.core.AbstractFragment;
import com.flys.bible.architecture.custom.CoreState;
import com.flys.bible.dao.db.ifaces.DailyVersetContentDao;
import com.flys.bible.dao.db.ifaces.DailyVersetDao;
import com.flys.bible.dao.db.ifaces.DailyVersetImageDao;
import com.flys.bible.dao.db.impl.DailyVersetContentDaoImpl;
import com.flys.bible.dao.db.impl.DailyVersetDaoImpl;
import com.flys.bible.dao.db.impl.DailyVersetImageDaoImpl;
import com.flys.bible.entities.DailyVerset;
import com.flys.bible.fragments.adapter.HomeAdapter;
import com.flys.bible.utils.FileUtils;
import com.flys.generictools.dao.daoException.DaoException;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@OptionsMenu(R.menu.menu_main)
@EFragment(R.layout.fragment_home_layout)
public class HomeFragment extends AbstractFragment {

    @ViewById(R.id.recyclerview)
    protected RecyclerView recyclerView;

    @ViewById(R.id.progressBar)
    protected ProgressBar progressBar;

    private HomeAdapter homeAdapter;
    private List<DailyVerset> listmodels;
    boolean isScrolling = false;
    int currentItems, totalItems, scrollOutItems;
    LinearLayoutManager linearLayoutManager;
    final static int page = 2;
    static int lastPosition = 0;

    @Bean(DailyVersetDaoImpl.class)
    protected DailyVersetDao dailyVersetDao;

    @Bean(DailyVersetImageDaoImpl.class)
    protected DailyVersetImageDao dailyVersetImageDao;

    @Bean(DailyVersetContentDaoImpl.class)
    protected DailyVersetContentDao dailyVersetContentDao;

    @Override
    public CoreState saveFragment() {
        return new CoreState();
    }

    @Override
    protected int getNumView() {
        return 4;
    }

    @Override
    protected void initFragment(CoreState previousState) {
        //Récupération des dailyversets de la base de données
        try {
            listmodels = dailyVersetDao.getAll();
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initView(CoreState previousState) {
        //day number in the year
        int day = Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
        //int day = 113;
        Log.e(getTag(), "Day number ::::: " + day);
        linearLayoutManager = new LinearLayoutManager(activity);
        //Daily verset is downloaded?
        if (FileUtils.fileExist("bible", day + ".png", activity)) {
            //listmodels.addAll(session.getDailyVersets());
            homeAdapter = new HomeAdapter(listmodels, activity);
            recyclerView.setAdapter(homeAdapter);
            recyclerView.setLayoutManager(linearLayoutManager);
        } else {
            //Le nombre de fois que nous partirons sur le serveur
            numberOfRunningTasks = 2;
            beginRunningTasks(numberOfRunningTasks);
            mainActivity.setAuthorization(true);
            mainActivity.setUrlServiceWebJson("https://developers.youversionapi.com/1.0");
            executeInBackground(mainActivity.getDailyVerset(day, 1), result -> {
                DailyVerset response = Utils.dtoToDailyVerset(result);
                homeAdapter = new HomeAdapter(listmodels, activity);
                recyclerView.setAdapter(homeAdapter);
                recyclerView.setLayoutManager(linearLayoutManager);
                mainActivity.setUrlServiceWebJson("http:" + response.getImage().getUrl().replace("{width}", "500").replace("{height}", "500"));
                mainActivity.setAuthorization(false);
                executeInBackground(mainActivity.getDailyVersetImage(), res -> {
                    FileUtils.saveToInternalStorage(res, "bible", response.getDay() + ".png", activity);
                    listmodels.add(response);
                    homeAdapter.notifyDataSetChanged();

                    try {
                        DailyVerset dailyVerset = new DailyVerset();
                        dailyVerset.setDay(response.getDay());
                        dailyVerset.setImage(dailyVersetImageDao.save(response.getImage()));
                        dailyVerset.setVerse(dailyVersetContentDao.save(response.getVerse()));
                        dailyVersetDao.save(dailyVerset);
                        //Update session
                        //session.addDailyVerset(dailyVerset);
                    } catch (DaoException e) {
                        e.printStackTrace();
                    }
                });

            });
        }
    }

    /**
     * @param dailyVersets
     * @param Ppage
     * @return
     */
    private List<DailyVerset> fetchDate(List<DailyVerset> dailyVersets, int Ppage) {
        progressBar.setVisibility(View.VISIBLE);
        List<DailyVerset> result = new ArrayList<>();
        for (int i = lastPosition; i < Ppage; i++) {
            //result.add(dailyVersets.get(i));
            //beginRunningTasks(Ppage);
            mainActivity.setUrlServiceWebJson("http:" + dailyVersets.get(i).getImage().getUrl().replace("{width}", "500").replace("{height}", "500"));
            mainActivity.setAuthorization(false);
            int finalI = i;
            executeInBackground(mainActivity.getDailyVersetImage(), res -> {
                Log.e(getClass().getSimpleName(), "***********  first image name length*****************" + res.length);
                FileUtils.saveToInternalStorage(res, "bible", dailyVersets.get(finalI).getVerse().getHuman_reference() + ".png", activity);
                listmodels.add(dailyVersets.get(finalI));
                homeAdapter.notifyDataSetChanged();
            });
            lastPosition++;
        }
        progressBar.setVisibility(View.GONE);
        return result;

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
}

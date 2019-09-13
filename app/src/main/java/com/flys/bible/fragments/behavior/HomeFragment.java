package com.flys.bible.fragments.behavior;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.flys.bible.R;
import com.flys.bible.architecture.core.AbstractFragment;
import com.flys.bible.architecture.custom.CoreState;
import com.flys.bible.entities.DailyVerset;
import com.flys.bible.fragments.adapter.HomeAdapter;
import com.flys.bible.utils.FileUtils;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
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

    }

    @Override
    protected void initView(CoreState previousState) {
        listmodels = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(activity);
        numberOfRunningTasks = 1;
        beginRunningTasks(numberOfRunningTasks);
        mainActivity.setAuthorization(true);
        mainActivity.setUrlServiceWebJson("https://developers.youversionapi.com/1.0");
        executeInBackground(mainActivity.getDailyVersets(1), response -> {
            // exploitation de la réponse
            homeAdapter = new HomeAdapter(listmodels, activity);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(homeAdapter);
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                        isScrolling = true;
                    }
                }

                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    currentItems = linearLayoutManager.getChildCount();
                    totalItems = linearLayoutManager.getItemCount();
                    scrollOutItems = linearLayoutManager.findFirstVisibleItemPosition();

                    if (isScrolling && (currentItems + scrollOutItems == totalItems)) {
                        isScrolling = false;
                        progressBar.setVisibility(View.VISIBLE);

                        //fetch data
                       listmodels.addAll(fetchDate(response.getData(), page, lastPosition));
                       homeAdapter.notifyDataSetChanged();
                    }
                }
            });

     /*  response.getData().stream().forEach(dailyVerset -> {
                Log.e(getClass().getSimpleName(), "------------------     image url : " + dailyVerset.getImage().getUrl());
                numberOfRunningTasks = listmodels.size();
                beginRunningTasks(numberOfRunningTasks);
                mainActivity.setUrlServiceWebJson("http:" + dailyVerset.getImage().getUrl().replace("{width}", "500").replace("{height}", "500"));
                mainActivity.setAuthorization(false);
                executeInBackground(mainActivity.getDailyVersetImage(), res -> {
                    FileUtils.saveToInternalStorage(res, "bible", dailyVerset.getVerse().getHuman_reference() + ".png", activity);
                    listmodels.add(dailyVerset);
                    homeAdapter.notifyDataSetChanged();
                });

            });*/


        });
    }

    private List<DailyVerset> fetchDate(List<DailyVerset> listmodels, int Ppage, int PlastPosition) {
        List<DailyVerset> result = new ArrayList<>();

        for (int i = PlastPosition; i < Ppage; i++) {
            result.add(listmodels.get(i));
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

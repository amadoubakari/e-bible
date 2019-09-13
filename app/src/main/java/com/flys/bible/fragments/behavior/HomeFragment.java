package com.flys.bible.fragments.behavior;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

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

    private HomeAdapter homeAdapter;
    private List<DailyVerset> listmodels;

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
        numberOfRunningTasks = 1;
        beginRunningTasks(numberOfRunningTasks);
        mainActivity.setAuthorization(true);
        mainActivity.setUrlServiceWebJson("https://developers.youversionapi.com/1.0");
        executeInBackground(mainActivity.getDailyVersets(1), response -> {
            // exploitation de la réponse
            homeAdapter = new HomeAdapter(listmodels, activity);
            recyclerView.setLayoutManager(new LinearLayoutManager(activity));
            recyclerView.setAdapter(homeAdapter);

            response.getData().stream().forEach(dailyVerset -> {
                Log.e(getClass().getSimpleName(), "------------------     image url : " + dailyVerset.getImage().getUrl());
                numberOfRunningTasks = listmodels.size();
                beginRunningTasks(numberOfRunningTasks);
                mainActivity.setUrlServiceWebJson("http:"+dailyVerset.getImage().getUrl().replace("{width}","500").replace("{height}","500"));
                mainActivity.setAuthorization(false);
                executeInBackground(mainActivity.getDailyVersetImage(), res -> {
                    FileUtils.saveToInternalStorage(res,"bible",dailyVerset.getVerse().getHuman_reference()+".png",activity);
                    listmodels.add(dailyVerset);
                    homeAdapter.notifyDataSetChanged();
                });

            });


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
}

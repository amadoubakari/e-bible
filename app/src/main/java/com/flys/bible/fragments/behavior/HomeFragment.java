package com.flys.bible.fragments.behavior;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.flys.bible.R;
import com.flys.bible.architecture.core.AbstractFragment;
import com.flys.bible.architecture.custom.CoreState;
import com.flys.bible.entities.DailyVerset;
import com.flys.bible.fragments.adapter.HomeAdapter;

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
        listmodels.add(new DailyVerset(new DailyVerset.DailyVersetImage("",""),1,new DailyVerset.DailyVersetContent("","Mat 1:1","",null,"Daily message 1")));
        listmodels.add(new DailyVerset(new DailyVerset.DailyVersetImage("",""),1,new DailyVerset.DailyVersetContent("","Mat 1:2","",null,"Daily message 1")));
        homeAdapter=new HomeAdapter(listmodels,activity);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(homeAdapter);
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

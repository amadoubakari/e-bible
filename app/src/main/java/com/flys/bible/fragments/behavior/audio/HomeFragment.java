package com.flys.bible.fragments.behavior.audio;

import com.flys.bible.R;
import com.flys.bible.architecture.core.AbstractFragment;
import com.flys.bible.architecture.custom.CoreState;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsMenu;

/**
 * Created by User on 17/10/2018.
 */
@EFragment(R.layout.test_layout)
@OptionsMenu(R.menu.menu_vide)
public class HomeFragment extends AbstractFragment {

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

    }

    @Override
    protected void initView(CoreState previousState) {

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

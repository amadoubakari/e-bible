package com.flys.bible.fragments.behavior;

import com.flys.bible.R;
import com.flys.bible.architecture.core.AbstractFragment;
import com.flys.bible.architecture.core.ISession;
import com.flys.bible.architecture.custom.CoreState;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsMenu;

/**
 * Created by User on 17/09/2018.
 */

@EFragment(R.layout.fragment_settings_layout)
@OptionsMenu(R.menu.menu_vide)
public class SettingsFragment extends AbstractFragment {

    @Click(R.id.settings_account_layout)
    protected void accountSettings(){
        mainActivity.navigateToView(3, ISession.Action.SUBMIT);
    }
    @Override
    public CoreState saveFragment() {
        return new CoreState();
    }

    @Override
    protected int getNumView() {
        return 2;
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

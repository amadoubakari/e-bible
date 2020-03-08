package com.flys.bible.fragments.behavior;

import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;


import com.flys.bible.R;
import com.flys.bible.architecture.core.AbstractFragment;
import com.flys.bible.architecture.core.ISession;
import com.flys.bible.architecture.custom.CoreState;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsMenu;

@EFragment(R.layout.fragment_splash_screen)
@OptionsMenu(R.menu.menu_vide)
public class SplashScreenFragment extends AbstractFragment {
    @Override
    public CoreState saveFragment() {
        return new CoreState();
    }

    @Override
    protected int getNumView() {
        return 1;
    }

    @Override
    protected void initFragment(CoreState previousState) {
        ((AppCompatActivity) mainActivity).getSupportActionBar().hide();
    }

    @Override
    protected void initView(CoreState previousState) {
        new Handler().postDelayed(() -> mainActivity.navigateToView(1, ISession.Action.SUBMIT), 10000);
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

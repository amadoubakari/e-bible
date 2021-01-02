package com.flys.bible.fragments.behavior;

import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.flys.bible.R;
import com.flys.bible.architecture.core.AbstractFragment;
import com.flys.bible.architecture.core.ISession;
import com.flys.bible.architecture.custom.CoreState;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_splash_screen)
@OptionsMenu(R.menu.menu_vide)
public class SplashScreenFragment extends AbstractFragment {

    @ViewById (R.id.app_name)
    protected TextView appName;
    @ViewById (R.id.app_version)
    protected TextView appVersion;
    @ViewById (R.id.logo)
    protected ImageView logo;
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
        appName.setText(getString(R.string.app_name));
        appVersion.setText(getString(R.string.app_version));
        logo.setImageDrawable(activity.getDrawable(R.drawable.logo));
        new Handler().postDelayed(() -> mainActivity.navigateToView(1, ISession.Action.SUBMIT), 2000);
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

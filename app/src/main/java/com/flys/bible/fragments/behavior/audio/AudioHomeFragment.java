package com.flys.bible.fragments.behavior.audio;

import android.widget.TextView;
import android.widget.Toast;

import com.flys.bible.R;
import com.flys.bible.architecture.core.AbstractFragment;
import com.flys.bible.architecture.custom.CoreState;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

/**
 * Created by User on 17/10/2018.
 */
@EFragment(R.layout.audio_home_fragment_layout)
@OptionsMenu(R.menu.menu_vide)
public class AudioHomeFragment extends AbstractFragment {

    @ViewById(R.id.helloworld)
    protected TextView helloworld;

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
        Toast.makeText(activity,"Je suis lancé",Toast.LENGTH_LONG).show();
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

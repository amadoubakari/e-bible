package com.flys.bible.activity;

import android.os.Bundle;
import android.view.View;

import com.flys.bible.R;
import com.flys.bible.architecture.core.AbstractActivity;
import com.flys.bible.architecture.core.AbstractFragment;
import com.flys.bible.dao.service.IDao;
import com.flys.bible.fragments.behavior.audio.HomeFragment_;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;

/**
 * Created by User on 17/10/2018.
 */
@EActivity
@OptionsMenu(R.menu.menu_vide)
public class AudioActivity extends AbstractActivity {

    @Override
    public void settings() {

    }

    @Override
    public void home() {

    }

    @Override
    public void bible() {

    }

    @Override
    protected void onCreateActivity() {

    }

    @Override
    protected IDao getDao() {
        return null;
    }

    @Override
    protected AbstractFragment[] getFragments() {
        return new AbstractFragment[]{new HomeFragment_()};
    }

    @Override
    protected CharSequence getFragmentTitle(int position) {
        return null;
    }

    @Override
    protected void navigateOnTabSelected(int position) {

    }

    @Override
    protected int getFirstView() {
        return 0;
    }

    @Override
    protected boolean isCollapse() {
        return true;
    }


}

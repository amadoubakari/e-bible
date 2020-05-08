package com.flys.bible.activity;

import com.flys.bible.R;
import com.flys.bible.architecture.core.AbstractActivity;
import com.flys.bible.architecture.core.AbstractFragment;
import com.flys.bible.dao.service.IDao;
import com.flys.bible.dto.DailyVersetDto;
import com.flys.bible.entities.DailyVersetData;
import com.flys.bible.fragments.behavior.audio.AudioHomeFragment_;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;

import rx.Observable;

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
        return new AbstractFragment[]{new AudioHomeFragment_()};
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


    @Override
    public void setAuthorization(boolean authorization) {

    }

    @Override
    public Observable<DailyVersetDto> getDailyVerset(int element, int version) {
        return null;
    }

    @Override
    public Observable<DailyVersetData> getDailyVersets(int version) {
        return null;
    }

    @Override
    public Observable<byte[]> getDailyVersetImage() {
        return null;
    }
}

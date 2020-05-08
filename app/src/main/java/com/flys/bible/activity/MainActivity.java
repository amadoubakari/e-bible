package com.flys.bible.activity;

import android.util.Log;

import com.flys.bible.R;
import com.flys.bible.architecture.core.AbstractActivity;
import com.flys.bible.architecture.core.AbstractFragment;
import com.flys.bible.architecture.core.ISession;
import com.flys.bible.architecture.custom.Session;
import com.flys.bible.dao.service.Dao;
import com.flys.bible.dao.service.IDao;
import com.flys.bible.dto.DailyVersetDto;
import com.flys.bible.entities.DailyVersetData;
import com.flys.bible.fragments.behavior.GeneralSettingsFragment_;
import com.flys.bible.fragments.behavior.HomeFragment_;
import com.flys.bible.fragments.behavior.MainFragment_;
import com.flys.bible.fragments.behavior.SettingsFragment_;
import com.flys.bible.fragments.behavior.SplashScreenFragment_;
import com.flys.bible.fragments.behavior.audio.AudioHomeFragment_;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;

import rx.Observable;


@EActivity
@OptionsMenu(R.menu.menu_vide)
public class MainActivity extends AbstractActivity {

    // couche [DAO]
    @Bean(Dao.class)
    protected IDao dao;
    // session
    private Session session;

    // méthodes classe parent -----------------------
    @Override
    protected void onCreateActivity() {
        // log
        if (IS_DEBUG_ENABLED) {
            Log.d(className, "onCreateActivity");
        }
        // session
        this.session = (Session) super.session;
        // todo : on continue les initialisations commencées par la classe parent
    }

    @Override
    protected IDao getDao() {
        return dao;
    }

    @Override
    protected AbstractFragment[] getFragments() {
        // todo : définir les fragments ici
        return new AbstractFragment[]{new SplashScreenFragment_(), new MainFragment_(),
                new SettingsFragment_(), new GeneralSettingsFragment_(), new HomeFragment_(),
                new AudioHomeFragment_()};
    }


    @Override
    protected CharSequence getFragmentTitle(int position) {
        // todo : définir les titres des fragments ici
        return null;
    }

    @Override
    protected void navigateOnTabSelected(int position) {
        // todo : navigation par onglets - définir la vue à afficher lorsque l'onglet n° [position] est sélectionné
    }

    @Override
    protected int getFirstView() {
        // todo : définir le n° de la première vue (fragment) à afficher
        return 0;
    }

    @Override
    protected boolean isCollapse() {
        return false;
    }

    @Override
    public boolean onSupportNavigateUp() {
        return true;
    }

    //Navigation
    @Override
    public void onBackPressed() {
        navigateToView(session.getPreviousView(), ISession.Action.SUBMIT);
    }

    @Override
    public void settings() {
        navigateToView(2, ISession.Action.SUBMIT);
    }

    @Override
    public void home() {
        navigateToView(4, ISession.Action.SUBMIT);
    }

    @Override
    public void bible() {
        navigateToView(1, ISession.Action.SUBMIT);
    }

    @Override
    public void setAuthorization(boolean authorization) {
        dao.setAuthorization(authorization);
    }

    @Override
    public Observable<DailyVersetDto> getDailyVerset(int element, int version) {
        return dao.getDailyVerset(element, version);
    }

    @Override
    public Observable<DailyVersetData> getDailyVersets(int version) {
        return dao.getDailyVersets(version);
    }

    @Override
    public Observable<byte[]> getDailyVersetImage() {
        return dao.getDailyVersetImage();
    }
}


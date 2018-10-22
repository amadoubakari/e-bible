package com.flys.bible.architecture.core;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flys.bible.R;
import com.flys.bible.activity.AudioActivity_;
import com.flys.bible.architecture.custom.CustomTabLayout;
import com.flys.bible.architecture.custom.IMainActivity;
import com.flys.bible.architecture.custom.Session;
import com.flys.bible.dao.service.IDao;

import java.io.IOException;


public abstract class AbstractActivity extends AppCompatActivity implements IMainActivity {
    // couche [DAO]
    private IDao dao;
    // la session
    protected ISession session;

    // le conteneur des fragments
    protected MyPager mViewPager;
    // la barre d'outils
    private Toolbar toolbar;
    // l'image d'attente
    private ProgressBar loadingPanel;
    // barre d'onglets
    protected TabLayout tabLayout;

    // le gestionnaire de fragments ou sections
    private FragmentPagerAdapter mSectionsPagerAdapter;
    // nom de la classe
    protected String className;
    // mappeur jSON
    private ObjectMapper jsonMapper;

    private DrawerLayout drawerLayout;

    private ActionBarDrawerToggle actionBarDrawerToggle;

    private CollapsingToolbarLayout collapsingToolbarLayout;

    // constructeur
    public AbstractActivity() {
        // nom de la classe
        className = getClass().getSimpleName();
        // log
        if (IS_DEBUG_ENABLED) {
            Log.d(className, "constructeur");
        }
        // jsonMapper
        jsonMapper = new ObjectMapper();
    }

    // implémentation IMainActivity --------------------------------------------------------------------
    @Override
    public ISession getSession() {
        return session;
    }

    @Override
    public void navigateToView(int position, ISession.Action action) {
        if (IS_DEBUG_ENABLED) {
            Log.d(className, String.format("navigation vers vue %s sur action %s", position, action));
        }
        // affichage nouveau fragment
        mViewPager.setCurrentItem(position);
        // on note l'action en cours lors de ce changement de vue
        session.setAction(action);
    }

    // gestion sauvegarde / restauration de l'activité ------------------------------------
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // parent
        super.onSaveInstanceState(outState);
        // sauvegarde session sous la forme d'une chaîne jSON
        try {
            outState.putString("session", jsonMapper.writeValueAsString(session));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        // log
        if (IS_DEBUG_ENABLED) {
            try {
                Log.d(className, String.format("onSaveInstanceState session=%s", jsonMapper.writeValueAsString(session)));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // parent
        super.onCreate(savedInstanceState);
        // log
        if (IS_DEBUG_ENABLED) {
            Log.d(className, "onCreate");
        }
        // qq chose à restaurer ?
        if (savedInstanceState != null) {
            // récupération session
            try {
                session = jsonMapper.readValue(savedInstanceState.getString("session"), new TypeReference<Session>() {
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
            // log
            if (IS_DEBUG_ENABLED) {
                try {
                    Log.d(className, String.format("onCreate session=%s", jsonMapper.writeValueAsString(session)));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        } else {
            // session
            session = new Session();
        }
        // couche [DAO]
        dao = getDao();
        if (dao != null) {
            // configuration de la couche [DAO]
            setDebugMode(IS_DEBUG_ENABLED);
            setTimeout(TIMEOUT);
            setDelay(DELAY);
            setBasicAuthentification(IS_BASIC_AUTHENTIFICATION_NEEDED);
        }
        // vue associée
        setContentView(R.layout.activity_main);
        // composants de la vue ---------------------
        // barre d'outils
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.main_content);

        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.Open, R.string.Close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // image d'attente ?
        if (IS_WAITING_ICON_NEEDED) {
            // on ajoute l'image d'attente
            if (IS_DEBUG_ENABLED) {
                Log.d(className, "adding loadingPanel");
            }
            // création ProgressBar
            loadingPanel = new ProgressBar(this);
            loadingPanel.setVisibility(View.INVISIBLE);
            // ajout du ProgressBar à la barre d'outils
            toolbar.addView(loadingPanel);
        }
        // barre d'onglets ?
        if (ARE_TABS_NEEDED) {
            // on ajoute la barre d'onglets
            if (IS_DEBUG_ENABLED) {
                Log.d(className, "adding tablayout");
            }
            // pas de navigation sur sélection jusqu'à l'affichage d'un fragment
            session.setNavigationOnTabSelectionNeeded(false);
            // création barre d'onglets
            tabLayout = new CustomTabLayout(this);
            tabLayout.setTabTextColors(ContextCompat.getColorStateList(this, R.color.tab_text));
            // ajout de la barre d'onglets à la barre d'application
            AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
            appBarLayout.addView(tabLayout);
            // gestionnaire d'évt de la barre d'onglets
            tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    // un onglet a été sélectionné
                    if (IS_DEBUG_ENABLED) {
                        Log.d(className, String.format("onTabSelected n° %s, action=%s, tabCount=%s isNavigationOnTabSelectionNeeded=%s",
                                tab.getPosition(), session.getAction(), tabLayout.getTabCount(), session.isNavigationOnTabSelectionNeeded()));
                    }
                    if (session.isNavigationOnTabSelectionNeeded()) {
                        // position de l'onglet
                        int position = tab.getPosition();
                        // mémoire
                        session.setPreviousTab(position);
                        // affichage fragment associé ?
                        navigateOnTabSelected(position);
                    }
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
        }
        // instanciation du gestionnaire de fragments
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        // le conteneur de fragments est associé au gestionnaire de fragments
        // ç-à-d que le fragment n° i du conteneur de fragments est le fragment n° i délivré par le gestionnaire de fragments
        mViewPager = (MyPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        // on inhibe le swipe entre fragments
        mViewPager.setSwipeEnabled(false);
        // adjacence des fragments
        mViewPager.setOffscreenPageLimit(OFF_SCREEN_PAGE_LIMIT);
        // qu'on associe à notre gestionnaire de fragments
        mViewPager.setAdapter(mSectionsPagerAdapter);
        // on affiche la 1ère vue
        if (session.getAction() == ISession.Action.NONE) {
            navigateToView(getFirstView(), ISession.Action.NONE);
        }

        //Navigation drawer
        NavigationView navigationView = findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(
                menuItem -> {
                    // set item as selected to persist highlight
                    menuItem.setChecked(true);
                    // close drawer when item is tapped
                    switch (menuItem.getItemId()) {
                        case R.id.menu_settings:
                            settings();
                            break;
                        case R.id.menu_home:
                            home();
                            break;
                        case R.id.menu_audio_bible:
                            startActivity(new Intent(AbstractActivity.this, AudioActivity_.class));
                            break;
                        default:
                            break;
                    }
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                });


        // on passe la main à l'activité fille
        onCreateActivity();
    }

    @Override
    public void onResume() {
        // parent
        super.onResume();
        if (IS_DEBUG_ENABLED) {
            Log.d(className, "onResume");
        }
        // si restauration, alors il faut restaurer le dernier onglet sélectionné
        if (ARE_TABS_NEEDED && session.getAction() == ISession.Action.RESTORE) {
            tabLayout.getTabAt(session.getPreviousTab()).select();
        }
    }

    // gestion de l'image d'attente ---------------------------------
    public void cancelWaiting() {
        if (loadingPanel != null) {
            loadingPanel.setVisibility(View.INVISIBLE);
        }
    }

    public void beginWaiting() {
        if (loadingPanel != null) {
            loadingPanel.setVisibility(View.VISIBLE);
        }
    }


    // interface IDao -----------------------------------------------------
    @Override
    public void setUrlServiceWebJson(String url) {
        dao.setUrlServiceWebJson(url);
    }

    @Override
    public void setUser(String user, String mdp) {
        dao.setUser(user, mdp);
    }

    @Override
    public void setTimeout(int timeout) {
        dao.setTimeout(timeout);
    }

    @Override
    public void setBasicAuthentification(boolean isBasicAuthentificationNeeded) {
        dao.setBasicAuthentification(isBasicAuthentificationNeeded);
    }

    @Override
    public void setDebugMode(boolean isDebugEnabled) {
        dao.setDebugMode(isDebugEnabled);
    }

    @Override
    public void setDelay(int delay) {
        dao.setDelay(delay);
    }

    //Gestion du menu principal
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    // le gestionnaire de fragments --------------------------------
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        AbstractFragment[] fragments;

        // constructeur
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
            // fragments de la classe fille
            fragments = getFragments();
        }

        // doit rendre le fragment n° i avec ses éventuels arguments
        @Override
        public AbstractFragment getItem(int position) {
            // on rend le fragment
            return fragments[position];
        }

        // rend le nombre de fragments à gérer
        @Override
        public int getCount() {
            return fragments.length;
        }


        // rend le titre du fragment n° position
        @Override
        public CharSequence getPageTitle(int position) {
            return getFragmentTitle(position);
        }
    }


    // classes filles
    protected abstract void onCreateActivity();

    protected abstract IDao getDao();

    protected abstract AbstractFragment[] getFragments();

    protected abstract CharSequence getFragmentTitle(int position);

    protected abstract void navigateOnTabSelected(int position);

    protected abstract int getFirstView();

    protected abstract boolean isCollapse();
}

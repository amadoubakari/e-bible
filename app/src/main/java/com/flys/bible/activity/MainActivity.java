package com.flys.bible.activity;

import android.util.Log;
import android.widget.Toast;

import com.flys.bible.architecture.core.AbstractActivity;
import com.flys.bible.architecture.core.AbstractFragment;
import com.flys.bible.architecture.core.ISession;
import com.flys.bible.architecture.custom.Session;
import com.flys.bible.dao.service.Dao;
import com.flys.bible.dao.service.IDao;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;
import com.flys.bible.R;
import com.flys.bible.fragments.behavior.DummyFragment_;
import com.flys.bible.fragments.behavior.GeneralSettingsFragment_;
import com.flys.bible.fragments.behavior.MainFragment_;
import com.flys.bible.fragments.behavior.SettingsFragment_;


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
    return new AbstractFragment[]{new MainFragment_(),new DummyFragment_(),new SettingsFragment_(),new GeneralSettingsFragment_()};
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
    Toast.makeText(this,"En cours ...",Toast.LENGTH_LONG).show();
  }

  @Override
  public void bible()  {
    navigateToView(0, ISession.Action.SUBMIT);
  }
}


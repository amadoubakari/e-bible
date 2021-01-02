package com.flys.bible.architecture.custom;

import com.flys.bible.architecture.core.ISession;
import com.flys.bible.dao.service.IDao;

public interface IMainActivity extends IDao {

  // accès à la session
  ISession getSession();

  // changement de vue
  void navigateToView(int position, ISession.Action action);

  // gestion de l'attente
  void beginWaiting();

  void cancelWaiting();

  // constantes de l'application (à modifier) -------------------------------------

  // mode debug
  boolean IS_DEBUG_ENABLED = true;

  // délai maximal d'attente de la réponse du serveur
  int TIMEOUT = 1000000;

  // délai d'attente avant exécution de la requête client
  int DELAY = 2000;

  // authentification basique
  boolean IS_BASIC_AUTHENTIFICATION_NEEDED = false;

  // adjacence des fragments
  int OFF_SCREEN_PAGE_LIMIT = 1;

  // barre d'onglets
  boolean ARE_TABS_NEEDED = false;

  // image d'attente
  boolean IS_WAITING_ICON_NEEDED = true;

  // nombre de fragments de l'application
  int FRAGMENTS_COUNT = 6;

  // todo ajoutez ici vos constantes et autres méthodes
  void settings();

  void home();

  void bible();
}

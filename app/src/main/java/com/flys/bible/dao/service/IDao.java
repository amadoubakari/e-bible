package com.flys.bible.dao.service;

import com.flys.bible.entities.DailyVerset;
import com.flys.bible.entities.DailyVersetData;

import rx.Observable;

public interface IDao {
    // Url du service web
    void setUrlServiceWebJson(String url);

    // utilisateur
    void setUser(String user, String mdp);

    // timeout du client
    void setTimeout(int timeout);

    // authentification basique
    void setBasicAuthentification(boolean isBasicAuthentificationNeeded);

    //
    void setAuthorization(boolean authorization);

    // mode debug
    void setDebugMode(boolean isDebugEnabled);

    // délai d'attente en millisecondes du client avant requête
    void setDelay(int delay);

    //Daily verset list
    Observable<DailyVerset> getDailyVerset(int element, int version);

    //Daily data list
    Observable<DailyVersetData> getDailyVersets(int version);

    //Upload file from server
    Observable<byte[]> getDailyVersetImage();
}

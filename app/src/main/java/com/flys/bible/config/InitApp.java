package com.flys.bible.config;

import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flys.bible.Utils;
import com.flys.bible.dao.db.ifaces.AppConfigDao;
import com.flys.bible.dao.db.ifaces.ChapitreDao;
import com.flys.bible.dao.db.ifaces.TitreDao;
import com.flys.bible.dao.db.ifaces.VersetDao;
import com.flys.bible.dao.db.impl.AppConfigDaoImpl;
import com.flys.bible.dao.db.impl.ChapitreDaoImpl;
import com.flys.bible.dao.db.impl.TitreDaoImpl;
import com.flys.bible.dao.db.impl.VersetDaoImpl;
import com.flys.bible.entities.AppConfig;
import com.flys.bible.entities.Livre;
import com.flys.bible.utils.EApplicationContext;
import com.flys.generictools.dao.daoException.DaoException;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;

@EBean(scope = EBean.Scope.Singleton)
public class InitApp implements Serializable {

    @Bean(AppConfigDaoImpl.class)
    protected AppConfigDao appConfigDao;

    @Bean(ChapitreDaoImpl.class)
    protected ChapitreDao chapitreDao;

    @Bean(TitreDaoImpl.class)
    protected TitreDao titreDao;

    @Bean(VersetDaoImpl.class)
    protected VersetDao versetDao;

    /**
     * @return
     */
    public boolean init() {
        boolean result = false;
        List<AppConfig> appConfigs = null;
        try {
            appConfigs = appConfigDao.findByPropertyName("installed", true);
            if (!appConfigs.isEmpty()) {
                result = true;
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }

        return result;
    }

    public Observable initialize() {
        return Observable.create((ObservableOnSubscribe<Boolean>) emitter -> {
            //AppConfig config = null;
            try {
                //Chargement de la base de données
                ObjectMapper mapper = new ObjectMapper();
                String jsonInput = Utils.loadJSONFromAsset(EApplicationContext.getContext(), "new_testament/mathieu.json");

                Livre livre = mapper.readValue(jsonInput, new TypeReference<Livre>() {
                });

                livre.getChapitres().forEach(chapitre -> {
                    try {
                        chapitreDao.save(chapitre).getTitres().forEach(titre -> {
                            titre.setChapitre(chapitre);
                            try {
                                titreDao.save(titre).getVersets().forEach(verset -> {
                                    verset.setTitre(titre);
                                    try {
                                        versetDao.save(verset);
                                        emitter.onNext(true);
                                    } catch (DaoException e) {
                                        emitter.onError(e);
                                        e.printStackTrace();
                                    }
                                });
                            } catch (DaoException e) {
                                emitter.onError(e);
                                e.printStackTrace();
                            }
                        });
                    } catch (DaoException e) {
                        emitter.onError(e);
                        e.printStackTrace();
                    }
                });
                //config = appConfigDao.save(new AppConfig(true));
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                emitter.onError(e);
                e.printStackTrace();
            }
            emitter.onComplete();
        });
    }

    /**
     * @return Elle permet d'installer notre application.
     */
    public boolean install() {
        AppConfig config = null;
        try {
            //Chargement de la base de données
            ObjectMapper mapper = new ObjectMapper();
            //String jsonInput = Utils.loadJSONFromAsset(EApplicationContext.getContext(), "new_testament/mathieu.json");

            Livre livre = mapper.readValue(EApplicationContext.getContext().getAssets().open("new_testament/mathieu.json"), new TypeReference<Livre>() {
            });

            livre.getChapitres().forEach(chapitre -> {
                try {
                    chapitreDao.save(chapitre).getTitres().forEach(titre -> {
                        titre.setChapitre(chapitre);
                        try {
                            titreDao.save(titre).getVersets().forEach(verset -> {
                                verset.setTitre(titre);
                                try {
                                    versetDao.save(verset);
                                } catch (DaoException e) {
                                    e.printStackTrace();
                                }
                            });
                        } catch (DaoException e) {
                            e.printStackTrace();
                        }
                    });
                } catch (DaoException e) {
                    e.printStackTrace();
                }
            });


            config = appConfigDao.save(new AppConfig(true));
        } catch (DaoException e) {
            Log.e(getClass().getSimpleName(), "dao exception" + e.getMessage());
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return config.isInstalled();
    }


}

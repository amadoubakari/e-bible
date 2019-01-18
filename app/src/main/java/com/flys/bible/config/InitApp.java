package com.flys.bible.config;

import android.util.Log;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JsonParseException;
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
import com.flys.bible.entities.Chapitre;
import com.flys.bible.entities.Livre;
import com.flys.bible.entities.Titre;
import com.flys.bible.entities.Verset;
import com.flys.bible.utils.EApplicationContext;
import com.flys.generictools.dao.daoException.DaoException;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

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

    /**
     * @return
     */
    public boolean install() {
        AppConfig config = null;
        try {
            //Chargement de la base de données
            ObjectMapper mapper = new ObjectMapper();
            String jsonInput = Utils.loadJSONFromAsset(EApplicationContext.getContext(), "new_testament/mathieu.json");

            Livre livre = mapper.readValue(jsonInput, new TypeReference<Livre>() {
            });
            List<Chapitre> listModels = livre.getChapitres();

            for (Chapitre chapitre: listModels
                 ) {
                Chapitre chapitre1=chapitreDao.save(chapitre);
                for (Titre titre:chapitre1.getTitres()
                     ) {
                    titre.setChapitre(chapitre1);
                    Titre titre1=titreDao.save(titre);
                    for (Verset verset: titre1.getVersets()
                         ) {
                        verset.setTitre(titre1);
                        versetDao.save(verset);
                    }
                }
            }
            config = appConfigDao.save(new AppConfig(true));
        } catch (DaoException e) {
            Log.e(getClass().getSimpleName(), "dao exception" + e.getMessage());
        }catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return config.isInstalled();
    }
}

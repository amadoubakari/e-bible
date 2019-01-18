package com.flys.bible.service;

import com.flys.bible.dao.db.ifaces.ChapitreDao;
import com.flys.bible.dao.db.ifaces.TitreDao;
import com.flys.bible.dao.db.impl.ChapitreDaoImpl;
import com.flys.bible.dao.db.impl.TitreDaoImpl;
import com.flys.bible.entities.Chapitre;
import com.flys.bible.entities.Titre;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.io.Serializable;
import java.util.Collection;

@EBean(scope = EBean.Scope.Singleton)
public class IServiceImpl implements Serializable, IServices {

    @Bean(ChapitreDaoImpl.class)
    protected ChapitreDao chapitreDao;

    @Bean(TitreDaoImpl.class)
    protected TitreDao titreDao;

    @Override
    public Collection<Titre> getTitlesOfChapter(Chapitre chapitre) {
        return null;
    }
}

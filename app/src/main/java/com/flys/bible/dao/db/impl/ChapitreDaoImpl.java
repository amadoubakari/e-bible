package com.flys.bible.dao.db.impl;
import com.flys.bible.R;
import com.flys.bible.dao.db.ifaces.ChapitreDao;
import com.flys.bible.entities.Chapitre;
import com.flys.bible.utils.EApplicationContext;
import com.flys.generictools.dao.daoImpl.GenericDaoImpl;
import com.flys.generictools.dao.db.DatabaseHelper;
import com.flys.generictools.dao.db.PersistenceFile;
import com.j256.ormlite.dao.Dao;

import org.androidannotations.annotations.EBean;

import java.sql.SQLException;

/**
 * Created by User on 23/10/2018.
 */

@EBean(scope = EBean.Scope.Singleton)
public class ChapitreDaoImpl extends GenericDaoImpl<Chapitre,Long> implements ChapitreDao{

    DatabaseHelper<Chapitre,Long> databaseHelper;

    @Override
    public Dao<Chapitre, Long> getDao() {
        databaseHelper = new DatabaseHelper(EApplicationContext.getContext(), R.raw.ormlite_config, PersistenceFile.getInstance(EApplicationContext.getContext()).getEntityClasses());
        try {
            return (Dao<Chapitre, Long>) databaseHelper.getDao(getEntityClassManaged());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    @Override
    public void flush() {
        if(databaseHelper.isOpen()){
            databaseHelper.close();
        }
    }

    @Override
    public Class<Chapitre> getEntityClassManaged() {
         return Chapitre.class;
    }


}


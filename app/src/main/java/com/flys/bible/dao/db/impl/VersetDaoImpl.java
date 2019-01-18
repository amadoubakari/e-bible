package com.flys.bible.dao.db.impl;

import com.flys.bible.R;
import com.flys.bible.dao.db.ifaces.VersetDao;
import com.flys.bible.entities.Verset;
import com.flys.bible.utils.EApplicationContext;
import com.flys.generictools.dao.daoImpl.GenericDaoImpl;
import com.flys.generictools.dao.db.DatabaseHelper;
import com.flys.generictools.dao.db.PersistenceFile;
import com.j256.ormlite.dao.Dao;

import org.androidannotations.annotations.EBean;

import java.sql.SQLException;

@EBean(scope = EBean.Scope.Singleton)
public class VersetDaoImpl extends GenericDaoImpl<Verset, Long> implements VersetDao {

    DatabaseHelper<Verset, Long> databaseHelper;

    @Override
    public Dao<Verset, Long> getDao() {
        databaseHelper = new DatabaseHelper(EApplicationContext.getContext(), R.raw.ormlite_config, PersistenceFile.getInstance(EApplicationContext.getContext()).getEntityClasses());
        try {
            return (Dao<Verset, Long>) databaseHelper.getDao(getEntityClassManaged());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void flush() {

    }

    @Override
    public Class<Verset> getEntityClassManaged() {
        return Verset.class;
    }
}

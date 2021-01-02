package com.flys.bible.dao.db.impl;

import com.flys.bible.R;
import com.flys.bible.dao.db.ifaces.DailyVersetContentDao;
import com.flys.bible.entities.DailyVerset;
import com.flys.bible.entities.DailyVersetContent;
import com.flys.bible.utils.EApplicationContext;
import com.flys.generictools.dao.daoImpl.GenericDaoImpl;
import com.flys.generictools.dao.db.DatabaseHelper;
import com.flys.generictools.dao.db.PersistenceFile;
import com.j256.ormlite.dao.Dao;

import org.androidannotations.annotations.EBean;

import java.sql.SQLException;

@EBean(scope = EBean.Scope.Singleton)
public class DailyVersetContentDaoImpl extends GenericDaoImpl<DailyVersetContent, Long> implements DailyVersetContentDao {

    DatabaseHelper<DailyVersetContent, Long> databaseHelper;

    @Override
    public Dao<DailyVersetContent, Long> getDao() {
        databaseHelper = new DatabaseHelper(EApplicationContext.getContext(), R.raw.ormlite_config, PersistenceFile.getInstance(EApplicationContext.getContext()).getEntityClasses());
        try {
            return (Dao<DailyVersetContent, Long>) databaseHelper.getDao(getEntityClassManaged());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void flush() {
        if (databaseHelper.isOpen()) {
            databaseHelper.close();
        }
    }

    @Override
    public Class<DailyVersetContent> getEntityClassManaged() {
        return DailyVersetContent.class;
    }
}

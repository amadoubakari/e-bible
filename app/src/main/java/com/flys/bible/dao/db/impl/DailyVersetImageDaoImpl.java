package com.flys.bible.dao.db.impl;

import com.flys.bible.dao.db.ifaces.DailyVersetImageDao;
import com.flys.bible.entities.DailyVersetImage;
import com.flys.bible.utils.EApplicationContext;
import com.flys.generictools.dao.daoImpl.GenericDaoImpl;
import com.flys.generictools.dao.db.DatabaseHelper;
import com.flys.generictools.dao.db.PersistenceFile;
import com.j256.ormlite.dao.Dao;
import com.flys.bible.R;

import org.androidannotations.annotations.EBean;

import java.sql.SQLException;

@EBean(scope = EBean.Scope.Singleton)
public class DailyVersetImageDaoImpl extends GenericDaoImpl<DailyVersetImage, Long> implements DailyVersetImageDao {

    DatabaseHelper<DailyVersetImage, Long> databaseHelper;

    @Override
    public Dao<DailyVersetImage, Long> getDao() {
        databaseHelper = new DatabaseHelper(EApplicationContext.getContext(), R.raw.ormlite_config, PersistenceFile.getInstance(EApplicationContext.getContext()).getEntityClasses());
        try {
            return (Dao<DailyVersetImage, Long>) databaseHelper.getDao(getEntityClassManaged());
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
    public Class<DailyVersetImage> getEntityClassManaged() {
        return DailyVersetImage.class;
    }
}

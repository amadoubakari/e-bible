package com.flys.bible.dao.db.impl;

import com.flys.bible.R;
import com.flys.bible.entities.AppConfig;
import com.flys.bible.dao.db.ifaces.AppConfigDao;
import com.flys.bible.entities.Chapitre;
import com.flys.bible.utils.EApplicationContext;
import com.flys.generictools.dao.daoImpl.GenericDaoImpl;
import com.flys.generictools.dao.db.DatabaseHelper;
import com.flys.generictools.dao.db.Persistence;
import com.j256.ormlite.dao.Dao;

import org.androidannotations.annotations.EBean;

import java.sql.SQLException;

@EBean(scope = EBean.Scope.Singleton)
public class AppConfigDaoImpl extends GenericDaoImpl<AppConfig,Long> implements AppConfigDao {

    DatabaseHelper<Chapitre,Long> databaseHelper;

    @Override
    public Dao<AppConfig, Long> getDao() {
        databaseHelper = new DatabaseHelper(EApplicationContext.getContext(), R.raw.ormlite_config, Persistence.getInstance(EApplicationContext.getContext()).getEntityClasses());
        try {
            return (Dao<AppConfig, Long>) databaseHelper.getDao(getEntityClassManaged());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void flush() {

    }

    @Override
    public Class<AppConfig> getEntityClassManaged() {
        return AppConfig.class;
    }

    @Override
    public AppConfig getAppConfig() {
        AppConfig appConfig=null;
        try {
            appConfig= getDao().queryForId(new Long(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appConfig;
    }
}

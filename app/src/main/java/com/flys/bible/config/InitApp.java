package com.flys.bible.config;

import com.flys.bible.dao.db.ifaces.AppConfigDao;
import com.flys.bible.dao.db.impl.AppConfigDaoImpl;
import com.flys.bible.entities.AppConfig;
import com.flys.generictools.dao.daoException.DaoException;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.io.Serializable;
import java.util.List;

@EBean(scope = EBean.Scope.Singleton)
public class InitApp implements Serializable {

    @Bean(AppConfigDaoImpl.class)
    protected  AppConfigDao appConfigDao;

    /**
     *
     * @return
     */
    public boolean init() {
        boolean result=false;
        List<AppConfig> appConfigs = null;
        try {
            appConfigs = appConfigDao.findByPropertyName("installed",true);
            if (!appConfigs.isEmpty()){
                result=true;
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     *
     * @return
     */
    public boolean install(){
        AppConfig config = null;
        try {
             config=appConfigDao.save(new AppConfig(true));
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return  config.isInstalled();
    }
}

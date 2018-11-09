package com.flys.bible.dao.db.ifaces;

import com.flys.bible.entities.AppConfig;
import com.flys.generictools.dao.IDao.GenericDao;

public interface AppConfigDao extends GenericDao<AppConfig,Long> {
     public AppConfig getAppConfig();
}

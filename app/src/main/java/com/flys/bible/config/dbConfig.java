package com.flys.bible.config;

import com.flys.bible.entities.Chapitre;
import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.Serializable;

/**
 * Created by User on 23/10/2018.
 */

public class dbConfig  extends OrmLiteConfigUtil implements Serializable {

    private static final Class<?>[] classes = new Class[] {
            Chapitre.class
    };
    public static void main(String[] args) throws Exception {
        writeConfigFile("ormlite_config.txt", classes);
    }
}
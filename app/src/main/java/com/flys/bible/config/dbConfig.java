package com.flys.bible.config;

import com.flys.bible.entities.Chapitre;
import com.flys.bible.entities.DailyVerset;
import com.flys.bible.entities.DailyVersetContent;
import com.flys.bible.entities.DailyVersetImage;
import com.flys.bible.entities.Livre;
import com.flys.bible.entities.Testament;
import com.flys.bible.entities.Titre;
import com.flys.bible.entities.Verset;
import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.Serializable;

/**
 * Created by User on 23/10/2018.
 */

public class dbConfig  extends OrmLiteConfigUtil implements Serializable {

    private static final Class<?>[] classes = new Class[] {
            Testament.class,Chapitre.class,Titre.class,Verset.class,Livre.class, DailyVerset.class, DailyVersetContent.class, DailyVersetImage.class
    };
    public static void main(String[] args) throws Exception {
        writeConfigFile("ormlite_config.txt", classes);
    }
}
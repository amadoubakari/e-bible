package com.flys.bible;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;


/**
 * Created by User on 07/09/2018.
 */

public class Utils implements Serializable {

    /**
     *
     * @param context
     * @return
     */
    public static String loadJSONFromAsset(Context context,String fileName) {

        String json = null;
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}

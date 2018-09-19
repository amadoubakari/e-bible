package com.flys.bible;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;


/**
 * Created by User on 07/09/2018.
 */

public class Utils implements Serializable {

    /**
     * @param context
     * @return
     */
    public static String loadJSONFromAsset(Context context, String fileName) {

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

    /**
     * @param context
     * @param subject object d'envoie du fichier
     * @param text    le texte à envoyer et ça peut être une URL
     * @param title   le titre de la fenêtre qui va s'afficher
     */
    public static void shareText(Context context, String subject, String text, String title) {
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);

        // Add data to the intent, the receiving app will decide
        // what to do with it.
        share.putExtra(Intent.EXTRA_SUBJECT, subject);
        share.putExtra(Intent.EXTRA_TEXT, text);
        context.startActivity(Intent.createChooser(share, title));
    }

    //Elle permet de partager un fichier
    public static void shareFile(Context context) {
        Intent share = new Intent(Intent.ACTION_SEND);

        // If you want to share a png image only, you can do:
        // setType("image/png"); OR for jpeg: setType("image/jpeg");
        share.setType("image/*");

        // Make sure you put example png image named myImage.png in your
        // directory
        String imagePath = Environment.getExternalStorageDirectory()
                + "/myImage.png";

        File imageFileToShare = new File(imagePath);

        Uri uri = Uri.fromFile(imageFileToShare);
        share.putExtra(Intent.EXTRA_STREAM, uri);

        context.startActivity(Intent.createChooser(share, "Share Image!"));
    }

}

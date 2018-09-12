package com.flys.bible.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.flys.bible.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * @author AMADOU BAKARI
 * @version 1.0.0
 * @todo écran d'accueil de l'application.
 * @since 23/02/2018
 */

@EActivity(R.layout.fragment_splash_screen)
public class SplashScreenActivity extends AppCompatActivity {

    @ViewById(R.id.bible_logo)
    protected ImageView logo;
    static final int SPLASHSCREEN_TIMEOUT = 1000;

    @AfterViews
    protected void afterViews() {
        new Handler().postDelayed(() -> {
            Intent i = new Intent(SplashScreenActivity.this, MainActivity_.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }, SPLASHSCREEN_TIMEOUT);
    }

}

package com.flys.bible.fragments.behavior;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.util.Log;

import com.flys.bible.R;
import com.flys.bible.architecture.core.AbstractFragment;
import com.flys.bible.architecture.custom.CoreState;
import com.flys.bible.utils.FileUtils;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;
import org.springframework.core.io.FileSystemResource;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by User on 18/09/2018.
 */

@EFragment(R.layout.fragment_general_settings)
@OptionsMenu(R.menu.menu_vide)
public class GeneralSettingsFragment extends AbstractFragment {

    @ViewById(R.id.profile_image)
    protected CircleImageView profile;

    private static final int READ_REQUEST_CODE = 42;

    @Click(R.id.fab)
    protected void profileImage() {
        if (activity.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // Explain to the user why we need to read the contacts
            }

            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    READ_REQUEST_CODE);

            // MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE is an
            // app-defined int constant that should be quite unique

            return;
        }
        performFileSearch();
    }


    @Override
    public CoreState saveFragment() {
        return new CoreState();
    }

    @Override
    protected int getNumView() {
        return 3;
    }

    @Override
    protected void initFragment(CoreState previousState) {

    }

    @Override
    protected void initView(CoreState previousState) {

    }

    @Override
    protected void updateOnSubmit(CoreState previousState) {

    }

    @Override
    protected void updateOnRestore(CoreState previousState) {

    }

    @Override
    protected void notifyEndOfUpdates() {

    }

    @Override
    protected void notifyEndOfTasks(boolean runningTasksHaveBeenCanceled) {

    }

    public void performFileSearch() {

        // ACTION_OPEN_DOCUMENT is the intent to choose a file via the system's file
        // browser.
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);

        // Filter to only show results that can be "opened", such as a
        // file (as opposed to a list of contacts or timezones)
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        // Filter to show only images, using the image MIME data type.
        // If one wanted to search for ogg vorbis files, the type would be "audio/ogg".
        // To search for all documents available via installed storage providers,
        // it would be "*/*".
        intent.setType("image/*");

        startActivityForResult(intent, READ_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData) {

        // The ACTION_OPEN_DOCUMENT intent was sent with the request code
        // READ_REQUEST_CODE. If the request code seen here doesn't match, it's the
        // response to some other intent, and the code below shouldn't run at all.
        if (resultCode != activity.RESULT_CANCELED) {
            if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
                // The document selected by the user won't be returned in the intent.
                // Instead, a URI to that document will be contained in the return intent
                // provided to this method as a parameter.
                // Pull that URI using resultData.getData().
                Uri uri = null;
                if (resultData != null) {
                    uri = resultData.getData();
                    FileUtils.dumpImageMetaData(activity, uri);
                    try {
                        profile.setImageDrawable(new BitmapDrawable(getResources(), FileUtils.getBitmapFromUri(uri, activity)));

                        FileUtils.saveToInternalStorage(FileUtils.getBitmapFromUri(uri, activity), "", "", activity);


                    } catch (IOException e) {
                        Log.e(getClass().getSimpleName(), e.getMessage());
                    }
                }
            }
        }

    }


}

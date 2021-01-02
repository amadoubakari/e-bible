package com.flys.bible.utils;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.flys.bible.R;

/**
 * Created by User on 19/10/2018.
 */

public class ConfigDialogFragment extends DialogFragment {

    private TextView mEditText;
    private ImageView save;

    public static ConfigDialogFragment newInstance(String title) {
        ConfigDialogFragment frag = new ConfigDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        //Put sérialization
        frag.setArguments(args);
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_config_fragment_layout, container);
    }

    @Override
    public void onResume() {
        // Get existing layout params for the window
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        // Assign window properties to fill the parent
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme);
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
        // Call super onResume after sizing
        super.onResume();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get field from view
        mEditText = view.findViewById(R.id.title);
        save = view.findViewById(R.id.save);
        // Fetch arguments from bundle and set title
        String title = getArguments().getString("title", "AMADOU BAKARI");
        mEditText.setText(title);
        getDialog().setTitle(title);
        // Show soft keyboard automatically and request focus to field
        mEditText.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        save.setOnClickListener(view1 -> dismiss());

    }

}

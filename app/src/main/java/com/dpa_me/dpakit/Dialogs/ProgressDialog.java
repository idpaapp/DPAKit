package com.dpa_me.dpakit.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.dpa_me.dpakit.R;


public class ProgressDialog extends Dialog {

    private String mMessage = "";
    private Activity activity;

    public ProgressDialog(Activity activity, String Message) {
        super(activity, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        this.activity = activity;
        mMessage = Message;
    }

    public ProgressDialog(Activity activity) {
        super(activity, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        this.activity = activity;
        mMessage = "";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.progressdialog);
        getWindow().setBackgroundDrawableResource(R.color.transBackColor);

        TextView txtMessage = findViewById(R.id.pd_txt_message);
        if (mMessage.equals(""))
            txtMessage.setVisibility(View.GONE);
        else
            txtMessage.setText(mMessage);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        activity.finish();
    }
}
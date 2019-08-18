package com.dpa_me.dpakit.Dialogs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.dpa_me.dpakit.R;


public class ConfirmDialog extends Dialog {

    private boolean avoidDismiss = false;
    private String mMessage;
    private String mYes;
    private String mNo;
    private AppCompatTextView qdTxtMessage;
    private AppCompatTextView qdBtnNo;
    private AppCompatTextView qdBtnYes;
    private AppCompatImageView qdImgLogo;

    public ConfirmDialog(@NonNull Context context) {
        super(context);
        mYes = getContext().getString(R.string.lblYes);
        mNo = getContext().getString(R.string.lblNo);
    }

    public ConfirmDialog(@NonNull Context context, String message) {
        super(context);
        mYes = getContext().getString(R.string.lblYes);
        mNo = getContext().getString(R.string.lblNo);
        this.mMessage = message;
    }

    public ConfirmDialog(@NonNull Context context, int message) {
        super(context);
        mYes = getContext().getString(R.string.lblYes);
        mNo = getContext().getString(R.string.lblNo);
        this.mMessage = getContext().getString(message);
    }

    private void initView() {
        qdTxtMessage = (AppCompatTextView) findViewById(R.id.qd_txt_message);
        qdBtnNo = (AppCompatTextView) findViewById(R.id.qd_btn_no);
        qdBtnYes = (AppCompatTextView) findViewById(R.id.qd_btn_yes);
        qdImgLogo = (AppCompatImageView) findViewById(R.id.qd_img_logo);
    }

    public interface IOpration {
        void onBtnYesClick();

        void onBtnNoClick();
    }

    public interface IYesOpration {
        void onBtnYesClick();
    }

    private IOpration onOpration = null;
    private IYesOpration onYesOpration = null;

    public ConfirmDialog setOnClickBtns(IOpration onOpration) {
        this.onOpration = onOpration;
        return this;
    }

    public ConfirmDialog setOnClickBtns(IYesOpration onOpration) {
        this.onYesOpration = onOpration;
        return this;
    }

    public ConfirmDialog setAvoidDismiss(boolean avoidDismiss) {
        this.avoidDismiss = avoidDismiss;
        return this;
    }

    public ConfirmDialog setMessage(String message) {
        this.mMessage = message;
        return this;
    }

    public ConfirmDialog setMessage(int message) {
        this.mMessage = getContext().getString(message);
        return this;
    }

    public ConfirmDialog setCaptions(String yesCaption, String noCaption) {
        mYes = yesCaption;
        mNo = noCaption;
        return this;
    }


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.questiondialog);
        getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCancelable(false);
        initView();

        qdBtnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onOpration != null)
                    onOpration.onBtnYesClick();

                if (onYesOpration != null)
                    onYesOpration.onBtnYesClick();

                if (!avoidDismiss)
                    dismiss();
            }
        });

        qdBtnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onOpration != null)
                    onOpration.onBtnNoClick();

                if (!avoidDismiss)
                    dismiss();
            }
        });
    }
}

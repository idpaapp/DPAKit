package com.dpa_me.dpakit.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.dpa_me.dpakit.R;

import static com.dpa_me.dpakit.Units.HandleUnit.HandleAnimations.JumpInAnimation;

public class MessageDialog extends Dialog {

    public static final int SUCCESS = 1;
    public static final int WARNING = 3;
    public static final int ERROR = 2;

    private boolean avoidDismiss = false;

    private String message;
    private String optionKeyCaption;
    private String altKeyCaption;
    private String okKeyCaption;

    private TextView mdBtnYes;
    private TextView mdBtnOption;
    private TextView mdBtnAlter;
    private TextView mdTxtMessage;
    private ImageView mdImgLogo;

    private int messageType;

    public MessageDialog(Context context) {
        super(context);
        message = context.getString(R.string.messOperationSucceed);
        okKeyCaption = getContext().getString(R.string.lblConfirm);
        messageType = SUCCESS;
        avoidDismiss = false;
    }

    public interface OnBtnClick {
        void onBtnClick();
    }

    private OnBtnClick OnYesClick = null;
    private OnBtnClick onOptionKeyClick = null;
    private OnBtnClick onAltKeyClick = null;

    public MessageDialog setOnClickBtns(OnBtnClick onClickBtns) {
        OnYesClick = onClickBtns;
        return this;
    }

    public MessageDialog setAvoidDismiss(boolean avoidDismiss) {
        this.avoidDismiss = avoidDismiss;
        return this;
    }

    public MessageDialog setBtnCaption(String btnCaption) {
        okKeyCaption = btnCaption;
        return this;
    }

    public MessageDialog setMessage(String message) {
        this.message = message;
        return this;
    }

    public MessageDialog setMessage(int message) {
        this.message = getContext().getString(message);
        return this;
    }

    public MessageDialog setOptionKey(String btnCaption, OnBtnClick onBtnClick) {
        optionKeyCaption = btnCaption;
        onOptionKeyClick = onBtnClick;

        return this;
    }

    public MessageDialog setAltKey(String btnCaption, OnBtnClick onBtnClick) {
        altKeyCaption = btnCaption;
        onAltKeyClick = onBtnClick;

        return this;
    }

    private void initViews() {
        mdBtnYes = findViewById(R.id.md_btn_yes);
        mdBtnAlter = findViewById(R.id.md_btn_alter);
        mdBtnOption = findViewById(R.id.md_btn_option);
        mdImgLogo = findViewById(R.id.md_img_logo);
        mdTxtMessage = findViewById(R.id.md_txt_message);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.showmessagedialog);
        getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setCancelable(avoidDismiss);

        initViews();

        mdTxtMessage.setText(message);

        mdBtnYes.setText(okKeyCaption);
        mdBtnAlter.setText(altKeyCaption);
        mdBtnOption.setText(optionKeyCaption);

        if (onAltKeyClick != null)
            mdBtnAlter.setVisibility(View.VISIBLE);

        if (onOptionKeyClick != null)
            mdBtnOption.setVisibility(View.VISIBLE);

        mdBtnOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onOptionKeyClick != null)
                    onOptionKeyClick.onBtnClick();

                if (!avoidDismiss)
                    dismiss();
            }
        });

        mdBtnAlter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onAltKeyClick != null)
                    onAltKeyClick.onBtnClick();

                if (!avoidDismiss)
                    dismiss();
            }
        });

        mdBtnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (OnYesClick != null)
                    OnYesClick.onBtnClick();

                if (!avoidDismiss)
                    dismiss();
            }
        });

        switch (messageType) {
            case SUCCESS: {
                mdBtnYes.setBackgroundResource(R.color.okColor);
                mdImgLogo.setImageResource(R.drawable.ic_success);
                break;
            }
            case ERROR: {
                mdBtnYes.setBackgroundResource(R.color.errorColor);
                mdImgLogo.setImageResource(R.drawable.ic_error);
                break;
            }
            case WARNING: {
                mdBtnYes.setBackgroundResource(R.color.warningColor);
                mdImgLogo.setImageResource(R.drawable.ic_warning);
                break;
            }
        }

        if (mdImgLogo != null)
            JumpInAnimation(getContext(), mdImgLogo, 300, 0);
    }
}

package com.dpa_me.dpakit.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.dpa_me.dpakit.R;
import com.dpa_me.dpakit.Activitys.CameraTempActivity;

import static com.dpa_me.dpakit.Units.HandleUnit.HandleApplication.CreateRandomNumber;

public class CameraOrGallryDialog extends Dialog {

    private Activity mContext;
    private Uri picUri;
    private LinearLayout imgTakeCam;
    private LinearLayout imgTakeTrash;
    private LinearLayout imgTakeGallery;
    private boolean mHasDelete = false;
    private int mX, mY;
    private boolean LockOpr;

    String mFileName = "temporary_holder";

    public CameraOrGallryDialog(@NonNull Activity context) {
        super(context);
        mContext = context;
    }

    public interface IOprationDone{
        void onPictureTaken(Uri imageUri);
        void onPictureDelete();
    }

    private IOprationDone onOprationDone = null;

    public CameraOrGallryDialog setOnOprationDone(IOprationDone onOprationDone){
        this.onOprationDone = onOprationDone;
        return this;
    }

    public CameraOrGallryDialog setHasDelete(boolean onHasDelete){
        this.mHasDelete = onHasDelete;
        return this;
    }

    public CameraOrGallryDialog setXY(int x, int y){
        this.mX = x;
        this.mY = y;
        return this;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LockOpr = false;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.cameraorgallerylayout);
        getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCancelable(false);

        imgTakeCam = findViewById(R.id.imgTakeCam);
        imgTakeGallery = findViewById(R.id.imgTakeGallery);
        imgTakeTrash = findViewById(R.id.imgTakeTrash);

        if (mHasDelete) imgTakeTrash.setVisibility(View.VISIBLE);

        imgTakeCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                camera();
                dismiss();
            }
        });

        imgTakeGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gallery();
                dismiss();
            }
        });
        imgTakeTrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onOprationDone != null)
                    onOprationDone.onPictureDelete();
                dismiss();
            }
        });
    }


    private void gallery() {
        if (!LockOpr) {
            LockOpr = true;
            new CameraTempActivity.PickerBuilder(mContext, CameraTempActivity.PickerBuilder.SELECT_FROM_GALLERY)
                    .setOnImageReceivedListener(new CameraTempActivity.PickerBuilder.onImageReceivedListener() {
                        @Override
                        public void onImageReceived(Uri imageUri) {
                            picUri = imageUri;

                            if (onOprationDone != null)
                                onOprationDone.onPictureTaken(imageUri);
                            dismiss();
                        }
                    })
                    .setMaxCropSize(mX, mY)
                    .setImageName(mContext.getString(R.string.app_name) + CreateRandomNumber())
                    .start();
            LockOpr = false;
        }
    }

    private void camera() {
        if (!LockOpr) {
            LockOpr = true;
            new CameraTempActivity.PickerBuilder(mContext, CameraTempActivity.PickerBuilder.SELECT_FROM_CAMERA)
                    .setOnImageReceivedListener(new CameraTempActivity.PickerBuilder.onImageReceivedListener() {
                        @Override
                        public void onImageReceived(Uri imageUri) {
                            picUri = imageUri;

                            if (onOprationDone != null)
                                onOprationDone.onPictureTaken(imageUri);
                            dismiss();
                        }
                    })

                    .setMaxCropSize(mX, mY)
                    .setImageName(mContext.getString(R.string.app_name) + CreateRandomNumber())
                    .start();
            LockOpr = false;
        }
    }
}
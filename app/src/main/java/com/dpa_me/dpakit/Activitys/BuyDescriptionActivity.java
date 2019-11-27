package com.dpa_me.dpakit.Activitys;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.dpa_me.dpakit.PersianDatePicker.util.PersianCalendar;
import com.dpa_me.dpakit.R;
import com.dpa_me.dpakit.Units.HandleUnit;
import com.dpa_me.dpakit.ZarinPal.OnCallbackVerificationPaymentListener;
import com.dpa_me.dpakit.ZarinPal.PaymentRequest;
import com.dpa_me.dpakit.ZarinPal.ZarinPal;
import com.marcinorlowski.fonty.Fonty;

import static com.dpa_me.dpakit.Units.HandleUnit.HandleApplication.SetActivityParams;
import static com.dpa_me.dpakit.Units.HandleUnit.HandleApplication.ShowProgressDialog;
import static com.dpa_me.dpakit.Units.HandleUnit.HandleString.SetThousandSeparator;

public class BuyDescriptionActivity extends AppCompatActivity {

    private AppCompatTextView abtTxtBuyDate;
    private AppCompatTextView abdTxtRefID;
    private AppCompatTextView abdTxtPrice;
    private AppCompatTextView abdTxtDesc;
    private AppCompatTextView abdTxtTitle;
    private AppCompatTextView abdBtnOk;
    private AppCompatImageView abdImgIcon;
    private RelativeLayout MainLayout;

    private void initViews(){
        abtTxtBuyDate = findViewById(R.id.abtTxtBuyDate);
        abdTxtRefID = findViewById(R.id.abdTxtRefID);
        abdTxtPrice = findViewById(R.id.abdTxtPrice);
        abdTxtDesc = findViewById(R.id.abdTxtDesc);
        abdTxtTitle = findViewById(R.id.abdTxtTitle);
        abdBtnOk = findViewById(R.id.abdBtnOk);
        abdImgIcon = findViewById(R.id.abdImgIcon);
        MainLayout = findViewById(R.id.MainLayout);
    }

    private void getZarrinResponse(){
        final Uri data = getIntent().getData();
        ZarinPal.getPurchase(BuyDescriptionActivity.this).verificationPayment(data, new OnCallbackVerificationPaymentListener() {
            @Override
            public void onCallbackResultVerificationPayment(boolean isPaymentSuccess, String refID, final PaymentRequest paymentRequest) {
                abtTxtBuyDate.setText(new PersianCalendar().getPersianLongDateAndTime());
                abdTxtRefID.setText(refID);
                abdTxtPrice.setText(SetThousandSeparator(String.valueOf(paymentRequest.getAmount())) + " " + getString(R.string.lblToman));
                abdTxtDesc.setText(paymentRequest.getDescription());

                if (isPaymentSuccess) {
                    HandleUnit.InvoiceNo = refID;
                    HandleUnit.Authority = paymentRequest.getAuthority();
                    ShowProgressDialog(BuyDescriptionActivity.this);
                    paymentRequest.getOpration().onBankResponsed();
                    HandleUnit.ResultOfPayment = 100;
                } else {
                    abdImgIcon.setImageResource(R.drawable.ic_error_white);
                    MainLayout.setBackgroundResource(R.color.errorColor);
                    abdTxtTitle.setText(R.string.messOperationFailedOnBuy);
                    abdBtnOk.setTextColor(getResources().getColor(R.color.errorColor));

                    HandleUnit.InvoiceNo = "0";
                }

                abdBtnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SetActivityParams(this, R.layout.activity_buy_description, false, "", false);

        initViews();
        getZarrinResponse();
    }
}

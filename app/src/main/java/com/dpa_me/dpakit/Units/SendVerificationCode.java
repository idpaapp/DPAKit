package com.dpa_me.dpakit.Units;

import android.app.Activity;

import com.dpa_me.dpakit.Models.SimpleResult;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.dpa_me.dpakit.Units.HandleUnit.HandleApplication.CloseProgressDialog;
import static com.dpa_me.dpakit.Units.HandleUnit.HandleApplication.ShowProgressDialog;
import static com.dpa_me.dpakit.Units.HandleUnit.HandleString.CreateInputJSON;

public class SendVerificationCode {

    private IOpration onOpration = null;
    private String Code;
    private String Mobile;
    private String Template;
    private String AppName;

    public interface IOpration {
        void onSuccess();

        void onFail();
    }

    public SendVerificationCode setOnCodeSentOpration(IOpration onOpration) {
        this.onOpration = onOpration;
        return this;
    }

    public SendVerificationCode(String mobile, String code, String Template, String AppName) {
        this.Mobile = mobile;
        this.Code = code;
        this.AppName = AppName;
        this.Template = Template;
    }

    public SendVerificationCode sendCode(Activity activity) {
        String input = CreateInputJSON(new String[]{"phone", "code", "app_name", "template"},
                new String[]{Mobile, Code, AppName, Template});

        ShowProgressDialog(activity);

        new Retrofit.Builder().baseUrl("http://restook.ir").
                addConverterFactory(ScalarsConverterFactory.create()).
                client(new OkHttpClient.Builder()
                        .connectTimeout(100, TimeUnit.SECONDS)
                        .readTimeout(100,TimeUnit.SECONDS).build()).
                addConverterFactory(GsonConverterFactory.create()).
                build().create(RetroInterface.class).sendCode(input).enqueue(new Callback<SimpleResult>() {
            @Override
            public void onResponse(Call<SimpleResult> call, Response<SimpleResult> response) {
                CloseProgressDialog();
                if (onOpration != null) {
                    if (response.body().Success)
                        onOpration.onSuccess();
                    else
                        onOpration.onFail();
                }
            }

            @Override
            public void onFailure(Call<SimpleResult> call, Throwable t) {
                CloseProgressDialog();
                if (onOpration != null)
                    onOpration.onFail();
            }
        });
        return this;
    }
}

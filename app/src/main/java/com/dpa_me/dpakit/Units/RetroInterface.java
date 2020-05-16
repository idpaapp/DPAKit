package com.dpa_me.dpakit.Units;

import com.dpa_me.dpakit.Models.AppAddressModel;
import com.dpa_me.dpakit.Models.AppSettings;
import com.dpa_me.dpakit.Models.Configs;
import com.dpa_me.dpakit.Models.SimpleResult;
import com.dpa_me.dpakit.Models.loginUnityConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface RetroInterface {
    @Headers({"Content-Type: application/json"})
    @POST("http://restook.ir/server_handling/api/routing.php")
    Call<AppAddressModel> getAddress_1(@Body String body);

    @Headers({"Content-Type: application/json"})
    @POST("http://footballica.ir/server_handling/api/routing.php")
    Call<AppAddressModel> getAddress_2(@Body String body);

    @Headers({"Content-Type: application/json"})
    @POST("http://volcan.ir/server_handling/api/routing.php")
    Call<AppAddressModel> getAddress_3(@Body String body);

    @Headers({"Content-Type: application/json"})
    @POST("public_apis/HandleSMS/send_sms.php")
    Call<SimpleResult> sendCode(@Body String body);

    @Headers({"Content-Type: application/json"})
    @POST("https://api.unity.com/v1/core/api/login")
    Call<loginUnityConfig> loginUnityConfig(@Body String body);

    @GET("https://remote-config-api.uca.cloud.unity3d.com/environments/{environmentId}/configs")
    Call<Configs> getConfigs(@Path("environmentId") String environmentId, @Query("projectId") String projectId);

    @GET("https://api.npoint.io/{AppId}")
    Call<AppSettings> getAppSettings(@Path("AppId") String AppId);
}

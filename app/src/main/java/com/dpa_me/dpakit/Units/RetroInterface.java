package com.dpa_me.dpakit.Units;

import com.dpa_me.dpakit.Models.AppAddressModel;
import com.dpa_me.dpakit.Models.SimpleResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;


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
    @POST("http://restook.ir/public_apis/HandleSMS/send_sms.php")
    Call<SimpleResult> sendCode(@Body String body);
}

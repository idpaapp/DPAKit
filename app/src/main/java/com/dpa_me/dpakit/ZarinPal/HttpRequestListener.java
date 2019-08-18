package com.dpa_me.dpakit.ZarinPal;

import org.json.JSONObject;

/**
 * Android ZarinPal In App Purchase SDK Library v0.0.2 Beta Project.
 * Created by ImanX on 12/22/16.
 * Copyright Alireza Tarazani All Rights Reserved.
 */
interface HttpRequestListener {
    void onSuccessResponse(JSONObject jsonObject, String contentResponse);
    void onFailureResponse(int httpStatusCode, String dataError);
}

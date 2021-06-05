package com.modo.modo.sportsapp.model.network;

import com.modo.modo.sportsapp.login.domain.AuthRequest;
import com.modo.modo.sportsapp.login.domain.AuthResponse;
import com.modo.modo.sportsapp.model.domain.network.ResponseDescription;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

public interface NetworkInterface {

    @GET("ping")
    Call<ResponseDescription> ping(@HeaderMap Map<String, String> headers);

    @POST("auth/login")
    Call<AuthResponse> auth(@Body AuthRequest authRequest);
}

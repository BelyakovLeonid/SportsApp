package com.modo.modo.sportsapp.login.data;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.modo.modo.sportsapp.login.domain.AuthRequest;
import com.modo.modo.sportsapp.login.domain.AuthResponse;
import com.modo.modo.sportsapp.model.domain.common.DataWrapper;
import com.modo.modo.sportsapp.model.domain.network.StatusCode;
import com.modo.modo.sportsapp.model.network.NetworkModule;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@NoArgsConstructor
public class LoginRepository {

    private static final String TAG = "LoginRepository";

    @Getter
    public final MutableLiveData<UUID> mldNetworkError = new MutableLiveData<>();
    @Getter
    public final MutableLiveData<DataWrapper<AuthResponse>> mldAuthData = new MutableLiveData<>();

    public void auth(String login, String pass) {
        Log.d(TAG, "auth: ");
        Call<AuthResponse> call = NetworkModule.getInstance().getApi().auth(
                new AuthRequest(login, pass));
        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(
                    @NonNull Call<AuthResponse> call,
                    @NonNull Response<AuthResponse> response) {
                Log.d(TAG, "onResponse: ");
                if (response.body() == null || response.body().getToken() == null) {
                    mldAuthData.setValue(new DataWrapper<>(StatusCode.AUTH_FAIL.getCode()));
                }
                mldAuthData.setValue(new DataWrapper<>(response.body()));
            }

            @Override
            public void onFailure(
                    @NonNull Call<AuthResponse> call,
                    @NonNull Throwable t) {
                Log.d(TAG, "onFailure: ");
                networkError();
            }
        });
    }

    private void networkError() {
        mldNetworkError.setValue(UUID.randomUUID());
    }
}
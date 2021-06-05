package com.modo.modo.sportsapp.feature1.data;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.modo.modo.sportsapp.model.domain.common.DataWrapper;
import com.modo.modo.sportsapp.model.domain.network.ResponseDescription;
import com.modo.modo.sportsapp.model.domain.network.StatusCode;
import com.modo.modo.sportsapp.model.network.GsonSerializer;
import com.modo.modo.sportsapp.model.network.NetworkModule;

import java.util.Map;
import java.util.UUID;

import lombok.Getter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Feature1Repository {

    private static final String TAG = "Feature1Repository";

    private final Gson gson = GsonSerializer.getInstance().getGson();

    @Getter
    private final MutableLiveData<UUID> mldNetworkError = new MutableLiveData<>();
    @Getter
    private final MutableLiveData<UUID> mldNetworkSuccess = new MutableLiveData<>();
    @Getter
    private final MutableLiveData<DataWrapper<UUID>> mldPing = new MutableLiveData<>();

    public void ping() {
        Log.d(TAG, "ping: ");
        Map<String, String> headers = NetworkModule.getInstance().proceedHeader("");
        Call<ResponseDescription> call = NetworkModule.getInstance().getApi().ping(headers);
        call.enqueue(new Callback<ResponseDescription>() {
            @Override
            public void onResponse(
                    @NonNull Call<ResponseDescription> call,
                    @NonNull Response<ResponseDescription> response) {
                Log.d(TAG, "onResponse: ");
                mldNetworkSuccess.setValue(UUID.randomUUID());
                ResponseDescription responseDescription = response.body();
                if (responseDescription == null) {
                    Log.e(TAG, "serverResponse == null");
                    return;
                }
                if (responseDescription.getStatusCode() == StatusCode.PING_SUCCESS.getCode()) {
                    mldPing.setValue(new DataWrapper<>(UUID.randomUUID()));
                } else {
                    mldPing.setValue(new DataWrapper<>(StatusCode.PING_FAIL.getCode()));
                }
            }

            @Override
            public void onFailure(
                    @NonNull Call<ResponseDescription> call,
                    @NonNull Throwable t) {
                Log.e(TAG, "onFailure: ");
                mldNetworkError.setValue(UUID.randomUUID());
            }
        });
    }
}
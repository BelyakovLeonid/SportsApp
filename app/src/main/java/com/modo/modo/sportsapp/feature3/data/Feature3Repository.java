package com.modo.modo.sportsapp.feature3.data;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.modo.modo.sportsapp.model.network.GsonSerializer;

import java.util.UUID;

import lombok.Getter;

public class Feature3Repository {

    private static final String TAG = "Feature3Repository";

    private Gson gson = GsonSerializer.getInstance().getGson();

    @Getter
    private MutableLiveData<UUID> mldNetworkError = new MutableLiveData<>();
    @Getter
    private MutableLiveData<UUID> mldNetworkSuccess = new MutableLiveData<>();
}
package com.modo.modo.sportsapp.login.presentation;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.modo.modo.sportsapp.login.data.LoginRepository;
import com.modo.modo.sportsapp.login.domain.AuthResponse;
import com.modo.modo.sportsapp.model.domain.common.DataWrapper;

public class ViewModel extends AndroidViewModel {

    private static final String TAG = "LoginViewModel";

    private final LoginRepository repository;

    public ViewModel(@NonNull Application application) {
        super(application);
        Log.d(TAG, "ViewModel: ");
        repository = new LoginRepository();
    }

    LiveData<DataWrapper<AuthResponse>> bindAuth() {
        Log.d(TAG, "bindAuth: ");
        return repository.getMldAuthData();
    }

    public void doLogin(String login, String pass) {
        Log.d(TAG, String.format("doLogin: login = %s, pass = %s", login, pass));
        repository.auth(login, pass);
    }
}

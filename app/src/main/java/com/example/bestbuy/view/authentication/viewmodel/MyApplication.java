package com.example.bestbuy.view.authentication.viewmodel;

import android.app.Application;

import androidx.lifecycle.ViewModelProvider;



public class MyApplication extends Application {
    private LoginViewModel loginViewModel;

    @Override
    public void onCreate() {
        super.onCreate();

        loginViewModel = new ViewModelProvider.AndroidViewModelFactory(this).create(LoginViewModel.class);

    }

    public LoginViewModel getAuthViewModel() {
        return loginViewModel;
    }
}
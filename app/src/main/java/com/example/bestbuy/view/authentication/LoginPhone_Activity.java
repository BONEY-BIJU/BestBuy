package com.example.bestbuy.view.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;


import com.example.bestbuy.R;
import com.example.bestbuy.view.authentication.viewmodel.LoginViewModel;
import com.example.bestbuy.view.authentication.viewmodel.MyApplication;
import com.hbb20.CountryCodePicker;

public class LoginPhone_Activity extends AppCompatActivity {
    private EditText phoneInput;
    private String phoneNumber;
    private CountryCodePicker countryCodePicker;
    private LoginViewModel loginViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_phone);

        countryCodePicker = findViewById(R.id.login_countrycode);
        phoneInput = findViewById(R.id.login_phone_number);
        Button continueButton = findViewById(R.id.send_otp_btn);
        MyApplication myApplication = (MyApplication) getApplication();
        loginViewModel = myApplication.getAuthViewModel();

        countryCodePicker.registerCarrierNumberEditText(phoneInput);
        continueButton.setOnClickListener((v) -> {

            if (!countryCodePicker.isValidFullNumber()) {
                phoneInput.setError("Phone number not valid");
                return;
            }
            phoneNumber = countryCodePicker.getFullNumberWithPlus();
            loginViewModel.setPhoneNumber(phoneNumber);


            Intent intent = new Intent(LoginPhone_Activity.this, LoginOTP_Activity.class);
            startActivity(intent);
        });

    }


}
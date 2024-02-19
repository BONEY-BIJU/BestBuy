package com.example.bestbuy.view.authentication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.bestbuy.view.activity.MainActivity;
import com.example.bestbuy.R;
import com.example.bestbuy.util.AndroidUtil;
import com.example.bestbuy.view.authentication.viewmodel.LoginViewModel;
import com.example.bestbuy.view.authentication.viewmodel.MyApplication;


public class RegisterUser_Activity extends AppCompatActivity {
    private EditText usernameInput, placeInput;
    private LoginViewModel loginViewModel;

//    private static final String PREF_NAME = "my_preference";
//    private static final String USERNAME = "user_name";
//    private static final String PHONE = "user_phone";
//    private static final String PLACE = "user_place";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        usernameInput = findViewById(R.id.login_username);
        placeInput = findViewById(R.id.login_place);
        Button continueButton = findViewById(R.id.signupbutton);

        MyApplication myApplication = (MyApplication) getApplication();
        loginViewModel = myApplication.getAuthViewModel();
        String phoneNumber = loginViewModel.getPhoneNumber();

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve input values
                String userName = usernameInput.getText().toString();
                String userPlace = placeInput.getText().toString();
                loginViewModel.addUser(userName, userPlace);
                loginViewModel.getUserAddedStatus().observe(RegisterUser_Activity.this, userAdded ->
                {
                    if (userAdded) {
                        Intent mainIntent = new Intent(RegisterUser_Activity.this, MainActivity.class);
                        startActivity(mainIntent);

                    } else {
                        AndroidUtil.showToast(RegisterUser_Activity.this, "NO DATA");
                    }
                });


            }
        });

    }
}
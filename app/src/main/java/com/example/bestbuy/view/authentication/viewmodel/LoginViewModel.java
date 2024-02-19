package com.example.bestbuy.view.authentication.viewmodel;


import android.app.Activity;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.bestbuy.view.authentication.model.User;
import com.example.bestbuy.repository.UserRepository;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class LoginViewModel extends ViewModel {
    String phoneNumber;

    private String otp ;

    private final MutableLiveData<Boolean> otpSentStatus = new MutableLiveData<>();
    final MutableLiveData<Boolean> userExistStatus = new MutableLiveData<>();
    final MutableLiveData<Boolean> userAddedStatus = new MutableLiveData<>();
    private final UserRepository userRepository = new UserRepository();
    private String verificationCode;

    private PhoneAuthProvider.ForceResendingToken resendingToken;

    public LiveData<Boolean> getOtpSentStatus() {
        return otpSentStatus;
    }

    public LiveData<Boolean> getUserAddedStatus() {
        return userAddedStatus;
    }

    public LiveData<Boolean> getUserExistStatus() {
        return userExistStatus;
    }


    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public void sendOtp(boolean isResend, Activity activity) {

        userRepository.sendOtp(phoneNumber, isResend, activity, (s, forceResendingToken) -> {

            verificationCode = s;
            resendingToken = forceResendingToken;
            otpSentStatus.postValue(true);
        }, e -> {
            otpSentStatus.postValue(false);

        });


    }
    public void signInWithOtp() {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCode, otp);

        userRepository.signIn(credential, task -> {
            if (task.isSuccessful()) {
                userRepository.checkUserExists(phoneNumber, queryTask -> {
                    if (queryTask.isSuccessful()) {
                        if (queryTask.getResult().isEmpty()) {
                            userExistStatus.postValue(false);

                            Log.d("UserExists", "User does not exist");
                        } else {

                            userExistStatus.postValue(true);

                            Log.d("UserExists", "User exists");
                        }
                    } else {
                        Log.d("Error","Bad query");

                    }
                });

            }});
    }
    public void addUser(String userName, String userPlace) {

        User.getInstance().setPhoneNumber(phoneNumber);
        User.getInstance().setName(userName);
        User.getInstance().setPlace(userPlace);
        userRepository.addUserToFirestore(task -> {
            if (task.isSuccessful()) {
                userAddedStatus.postValue(true);


            } else {

                userAddedStatus.postValue(false);
            }
        });
    }


}
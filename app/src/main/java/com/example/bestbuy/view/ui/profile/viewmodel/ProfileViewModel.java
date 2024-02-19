package com.example.bestbuy.view.ui.profile.viewmodel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.bestbuy.view.authentication.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileViewModel extends ViewModel {
    private final MutableLiveData<User> loggedInUser = new MutableLiveData<>();
    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();

    public LiveData<User> getLoggedInUser() {
        FirebaseUser currentUser = mAuth.getCurrentUser();


        if (currentUser != null) {
            User user = User.getInstance();
            loggedInUser.postValue(user);

            loggedInUser.postValue(user);
        } else {
            loggedInUser.postValue(null);
        }
        return loggedInUser;
    }

    public void logout() {
        FirebaseAuth.getInstance().signOut();
        // Clear the user details when logging out
        loggedInUser.postValue(null);
        User.getInstance().setPhoneNumber(null);
        User.getInstance().setName(null);
        User.getInstance().setPlace(null);
        loggedInUser.postValue(null);
    }
}
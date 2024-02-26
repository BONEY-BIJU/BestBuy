package com.example.bestbuy.view.ui.profile.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.bestbuy.view.activity.MainActivity;
import com.example.bestbuy.R;
import com.example.bestbuy.view.authentication.LoginPhone_Activity;
import com.example.bestbuy.view.ui.profile.viewmodel.ProfileViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;


public class ProfileFragment extends Fragment {

    private TextView nameText, logoutText,placetext,phonetext;
    private Button loginButton;
    private ConstraintLayout profileLayout;
    private ConstraintLayout signInLayout;
    private ProfileViewModel profileViewModel;
    private final String USER_PREFERENCE = "User";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        nameText = root.findViewById(R.id.profile_username);
        phonetext=root.findViewById(R.id.profile_phone);
        placetext=root.findViewById(R.id.profile_place);
        signInLayout = root.findViewById(R.id.signoutLayout);
        profileLayout = root.findViewById(R.id.SigInLayout);
        loginButton = root.findViewById(R.id.Login_btn);
        logoutText = root.findViewById(R.id.logout_btn);


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        profileViewModel.getLoggedInUser().observe(getViewLifecycleOwner(), userDetails -> {

            if (userDetails != null) {
                signInLayout.setVisibility(View.GONE);
                saveUsernameToSharedPreferences(userDetails.getName(), userDetails.getPlace(),userDetails.getPhoneNumber());
                profileLayout.setVisibility(View.VISIBLE);
                SharedPreferences preferences = requireActivity().getSharedPreferences(USER_PREFERENCE, Context.MODE_PRIVATE);
                String userName = preferences.getString("username", null);
                String place = preferences.getString("place", null);
                String phonenumber=preferences.getString("phonenumber",null);
                String formattedName = userName != null ? userName : "";
                String formattedplace = place != null ? place : "";
                String formatttednumber = phonenumber != null ? phonenumber :"";
                nameText.setText(formattedName);
                phonetext.setText(formatttednumber);
                placetext.setText(formattedplace);
            } else {
                signInLayout.setVisibility(View.VISIBLE);
                profileLayout.setVisibility(View.GONE);
            }
        });
        logoutText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteUser();
                profileViewModel.logout();

                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoginPhone_Activity.class);
                startActivity(intent);

            }
        });
    }

    private void saveUsernameToSharedPreferences(String username,String place,String phonenumber) {
        SharedPreferences preferences = requireContext().getSharedPreferences(USER_PREFERENCE, Context.MODE_PRIVATE);

        // Check if the preference is empty before saving
        if (preferences.getString("username", null) == null) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("username", username);
            editor.putString("place", place);
            editor.putString("phonenumber",phonenumber);
            editor.apply();
        }
    }
    private void deleteUser(){
        SharedPreferences preferences = requireContext().getSharedPreferences(USER_PREFERENCE, Context.MODE_PRIVATE);
          SharedPreferences.Editor editor = preferences.edit();
          editor.clear();
          editor.apply();


    }

}
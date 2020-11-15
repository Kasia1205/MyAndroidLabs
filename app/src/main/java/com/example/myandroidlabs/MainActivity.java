package com.example.myandroidlabs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText address, password;
    Button login;
    Button chatButton;
    public static final String emailAddress = "emailKey";
    public static final String inputPassword = "passwordKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login= findViewById(R.id.loginButton);
        login.setOnClickListener(v -> {
            Intent goToProfile = new Intent(MainActivity.this, ProfileActivity.class);

            address= findViewById(R.id.typeAddress);
            String givenAddress = address.getText().toString();

            goToProfile.putExtra(emailAddress, givenAddress);
            startActivity(goToProfile);
        });
        chatButton = findViewById(R.id.chatButton);
        chatButton.setOnClickListener(v -> {
            Intent goToChat = new Intent(MainActivity.this, ChatRoomActivity.class);
            startActivity(goToChat);
        });
    }

    @Override
    protected void onPause() {

        super.onPause();

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        address= findViewById(R.id.typeAddress);
        password= findViewById(R.id.typePass);
        login= findViewById(R.id.loginButton);
        String givenAddress = address.getText().toString();
        String givenPassWord = password.getText().toString();

        editor.putString(emailAddress, givenAddress);
        editor.putString(inputPassword, givenPassWord);
        editor.apply();

    }
}
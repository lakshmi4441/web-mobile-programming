package com.example.lakshmikorrapati.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Translate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);
    }

    public void Logout(android.view.View view) {
        //This code redirects the from login page to the logout page.
        android.content.Intent intent = new android.content.Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
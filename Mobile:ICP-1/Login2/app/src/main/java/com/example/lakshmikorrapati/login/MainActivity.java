package com.example.lakshmikorrapati.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Context;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void Login (android.view.View view)
    {
        EditText usernameCtrl = (EditText)findViewById(R.id.editText2);
        EditText passwordCtrl = (EditText) findViewById(R.id.editText3);
        String userName = usernameCtrl.getText().toString();
        String password = passwordCtrl.getText().toString();

        boolean validationFlag = false;
        //Verify if the username and password are not empty.
        if(!userName.isEmpty() && !password.isEmpty()) {
            if(userName.equals("vkvn3") && password.equals("UmkcEdu")) {
                validationFlag = true;

            }
        }
        if(!validationFlag)
        {
            Context context = getApplicationContext();
            CharSequence text = "Wrong Username or Password!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else
        {
            //This code redirects the from login page to the logout page.
            android.content.Intent intent = new android.content.Intent(this, Translate.class);
            startActivity(intent);
        }



    }
}
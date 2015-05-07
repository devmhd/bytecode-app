package com.rubik.bytecodem;

import android.app.ActionBar;
import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;


public class LoginActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

 //       getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();


        setContentView(R.layout.activity_login);


        boolean showSignUp = getIntent().getBooleanExtra("showSignUp", false);

        if(showSignUp){
            getFragmentManager()	.beginTransaction()
                    .replace(R.id.content_frame, new SignupFragment())
                    .commit();
        } else {
            getFragmentManager()	.beginTransaction()
                    .replace(R.id.content_frame, new LoginFragment())
                    .commit();
        }

    }
}
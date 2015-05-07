package com.rubik.bytecodem;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class LaunchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        PreferenceStorage.init(getApplicationContext());
        NetworkTasks.isNetworkConnected(getApplicationContext());
        Constants.init();

        //startActivity(new Intent(LaunchActivity.this, DashboardActivity.class));



        if(PreferenceStorage.isFirstLaunch()){

            PreferenceStorage.setFirstLaunch();

            Intent intent = new Intent(LaunchActivity.this, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("showSignUp", true);
            startActivity(intent);
            overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);
        }

        else {

            if(PreferenceStorage.isLoggedIn()){

                // TODO goto homescreen
            }else{
                Intent intent = new Intent(LaunchActivity.this, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("showSignUp", false);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);
            }
        }

    }



}

package com.example.steve.a390assa;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class MainActivity extends AppCompatActivity {

    protected Button profileButton = null;

    public void gotoProfileActivity(){
        Intent i = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profileButton = (Button) findViewById(R.id.Profile_button);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoProfileActivity();
            }
        });



    }


    @Override
    protected void onStart () {
        super.onStart();

        SharedPreferences sp = getSharedPreferences("Profile_Preference", Context.MODE_PRIVATE);
        String name = sp.getString("profile_name", null);
        if(name == null)
            gotoProfileActivity();
        else{
            profileButton.setText(name);

        }

    }




}

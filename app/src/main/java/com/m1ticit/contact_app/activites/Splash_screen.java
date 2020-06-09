package com.m1ticit.contact_app.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.m1ticit.contact_app.R;

public class Splash_screen extends AppCompatActivity {
    TextView getting_started;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        init();
        navigate();


    }

    private void navigate() {
        getting_started.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i =new Intent(Splash_screen.this,MainActivity.class);
                startActivity(i);

            }
        });
    }

    private void init() {
        getting_started=(TextView)findViewById(R.id.getting_started);

    }

}

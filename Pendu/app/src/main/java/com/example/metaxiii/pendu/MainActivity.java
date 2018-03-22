package com.example.metaxiii.pendu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    private Button easy;
    private Button medium;
    private Button hard;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Déclaration
        easy = findViewById(R.id.easy);
        medium = findViewById(R.id.medium);
        hard = findViewById(R.id.hard);

        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentEasy = new Intent(getApplicationContext(), PenduActivity.class);
                intentEasy.putExtra("mode", easy.getText().toString());
                startActivity(intentEasy);
            }
        });

        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMedium = new Intent(getApplicationContext(), PenduActivity.class);
                intentMedium.putExtra("mode", medium.getText().toString());
                startActivity(intentMedium);
            }
        });

        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentHard = new Intent(getApplicationContext(), PenduActivity.class);
                intentHard.putExtra("mode", hard.getText().toString());
                startActivity(intentHard);
            }
        });
    }
}

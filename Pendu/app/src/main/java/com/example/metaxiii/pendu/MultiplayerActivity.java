package com.example.metaxiii.pendu;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MultiplayerActivity extends AppCompatActivity {

    private EditText wordGiven;
    private Button btn_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer);

        Toast.makeText(getApplicationContext(), "Ne montrez pas votre mot à votre ami", Toast.LENGTH_SHORT).show();
        wordGiven = findViewById(R.id.wordGiven);
        btn_send = findViewById(R.id.btn_send);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PenduActivity.class);
                intent.putExtra("mode", wordGiven.getText().toString().toUpperCase());
                startActivity(intent);
            }
        });

    }

}

package com.example.metaxiii.pendu;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
public class PenduActivity extends AppCompatActivity {
    private LinearLayout word_container;
    private Button btn_send;
    private TextView dead_word;
    private ImageView image;
    private EditText word;
    private String wordHidden;
    private int tmp;
    private int error;
    private List<Character> ListOfLetters = new ArrayList<>();
    private boolean game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendu);
        word_container = findViewById(R.id.word_container);
        btn_send = findViewById(R.id.btn_send);
        dead_word = findViewById(R.id.dead_word);
        word = findViewById(R.id.word);
        image = findViewById(R.id.image_pendu);
    }
    public void initGame()
    {
        wordHidden = "test";
        game = false;
        error = 0;
        tmp = 0;
        dead_word.setText("");
        image.setBackgroundResource(R.drawable.first);
        //
        for (int i = 0; i < wordHidden.length(); i++)
        {
            TextView letters = (TextView) getLayoutInflater().inflate(R.layout.textview, null);
            word_container.addView(letters);
        }
    }
}

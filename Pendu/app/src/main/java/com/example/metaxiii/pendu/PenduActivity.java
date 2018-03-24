package com.example.metaxiii.pendu;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class PenduActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout word_container;
    private Button btn_send;
    private TextView dead_word;
    private ImageView image;
    private EditText letter;
    private String wordHidden;
    private int tmp;
    private int error;
    private List<Character> listOfLetters = new ArrayList<>();
    private boolean game;
    private List<String> wordList = new ArrayList<>();
    private String mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Déclaration de la difficulté
        Intent intent = getIntent();
        String mode = intent.getStringExtra("mode");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendu);
        word_container = findViewById(R.id.word_container);
        btn_send = findViewById(R.id.btn_send);
        dead_word = findViewById(R.id.dead_word);
        letter = findViewById(R.id.letter);
        image = findViewById(R.id.image_pendu);
        initGame(mode);
        btn_send.setOnClickListener(this);
    }


    public void initGame(String mode) {
        wordHidden = generateWord(mode);
        game = false;
        error = 0;
        tmp = 0;
        dead_word.setText("");
        listOfLetters = new ArrayList<>();
        image.setBackgroundResource(R.drawable.first);

        word_container.removeAllViews();

        for (int i = 0; i < wordHidden.length(); i++) {
            TextView letters = (TextView) getLayoutInflater().inflate(R.layout.textview, null);
            word_container.addView(letters);
        }
    }

    @Override
    public void onClick(View view) {
        String saisie = letter.getText().toString().toUpperCase();
        letter.setText("");
        if (saisie.length() > 0) {
            if (!isUsed(saisie.charAt(0), listOfLetters)) {
                listOfLetters.add(saisie.charAt(0));
                insideWord(saisie, wordHidden);
            }
            if (tmp == wordHidden.length()) {
                game = true;
                createDialog(game,mode);
            }

            if (!wordHidden.contains(saisie)) {
                error++;
            }
            setImage(error);
            if (error == 6) {
                game = false;
                createDialog(game, mode);
            }
            //Affiche les lettres entrées
            showBadCharacter(listOfLetters);
        }
    }


    public boolean isUsed(char a, List<Character> listOfLetters) {
        for (int i = 0; i < listOfLetters.size(); i++) {
            if (listOfLetters.get(i) == a) {
                Toast.makeText(getApplicationContext(), "Vous avez déjà entré cette lettre", Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        return false;
    }


    public void insideWord(String letter, String word) {
        for (int i = 0; i < word.length(); i++) {
            if (letter.equals(String.valueOf(word.charAt(i)))) {
                TextView tv = (TextView) word_container.getChildAt(i);
                tv.setText(String.valueOf(word.charAt(i)));
                tmp++;
            }
        }
    }

    public void showBadCharacter(List<Character> listOfLetters) {
        String chaine = "";
        for (int i = 0; i < listOfLetters.size(); i++) {
            chaine += listOfLetters.get(i) + "\n";
        }
        if (!chaine.equals("")) {
            dead_word.setText(chaine);
        }
    }


    public void setImage(int error) {
        switch (error) {
            case 1:
                image.setBackgroundResource(R.drawable.second);
                break;
            case 2:
                image.setBackgroundResource(R.drawable.third);
                break;
            case 3:
                image.setBackgroundResource(R.drawable.fourth);
                break;
            case 4:
                image.setBackgroundResource(R.drawable.fifth);
                break;
            case 5:
                image.setBackgroundResource(R.drawable.sixth);
                break;
            case 6:
                image.setBackgroundResource(R.drawable.seventh);
                break;
            default:
                image.setBackgroundResource(R.drawable.first);
                break;
        }
    }

    public void createDialog(boolean win, final String mode) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Vous avez gagné");
        if (!win) {
            builder.setTitle("Vous avez perdu");
            builder.setMessage("Le mot a trouvé était : " + wordHidden);
        }
        builder.setPositiveButton(getResources().getString(R.string.rejouer), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        builder.create().show();
    }


    public List<String> getListWord() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getAssets().open("pendu_liste.txt")));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                wordList.add(line);
            }
            bufferedReader.close();

        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return wordList;
    }


    public String generateWord(String mode) {
        wordList = getListWord();
        String word = "";
        if (mode.equals("hard")) {
            while (word.length() < 6) {
                int random = (int) Math.floor(Math.random() * wordList.size());
                word = wordList.get(random).trim();
            }
        } else if (mode.equals("medium")) {
            while (word.length() < 4 || word.length() > 6) {
                int random = (int) Math.floor(Math.random() * wordList.size());
                word = wordList.get(random).trim();
            }
        } else {
            while (word.length() > 4 || word.length() == 0) {
                int random = (int) Math.floor(Math.random() * wordList.size());
                word = wordList.get(random).trim();
            }
        }
        return word;
    }
}

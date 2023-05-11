package ru.ctf.kartochki;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Pair;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView labelView;
    TextView statusView;
    TextView wordView;
    EditText inputText;
    Button button;
    ArrayList<Pair<String, String>> espRus = new ArrayList<Pair<String, String>>();
    int counter = 0;
    String wordCount = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ArrayList<Pair<String, String>> espRus = new ArrayList<Pair<String, String>>();
        espRus.add(new Pair<String, String>("chica", "девушка"));
        espRus.add(new Pair<String, String>("padre", "отец"));
        espRus.add(new Pair<String, String>("quatro", "четыре"));
        espRus.add(new Pair<String, String>("perro", "собака"));
        espRus.add(new Pair<String, String>("hora", "час"));
        espRus.add(new Pair<String, String>("raton", "мышь"));
        espRus.add(new Pair<String, String>("trabajo", "работа"));
        wordCount = String.valueOf(espRus.size());

        labelView = (TextView)findViewById(R.id.labelView);
        statusView = (TextView)findViewById(R.id.statusView);
        wordView = (TextView)findViewById(R.id.wordView);
        inputText = (EditText)findViewById(R.id.inputText);
        button = (Button)findViewById(R.id.button);

        wordView.setText(espRus.get(counter).first);
        statusView.setText("Слово " + String.valueOf(counter) + "/" + wordCount);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check();
            }
        });
    }

    public void check() {
        if (inputText.getText().toString().equals(espRus.get(counter).second)) {
            counter++;
            wordView.setText(espRus.get(counter).first);
            statusView.setText("Слово " + String.valueOf(counter) + "/" + wordCount);
            Toast.makeText(MainActivity.this, "Правильно!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Неправильно!", Toast.LENGTH_SHORT).show();
        }
        inputText.getText().clear();
    }
}
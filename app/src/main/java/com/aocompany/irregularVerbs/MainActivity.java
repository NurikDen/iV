package com.aocompany.irregularVerbs;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.util.Locale;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private TextView textView;
    private EditText editText;
    private TextView textView2;
    private Button button;
    private TextToSpeech textToSpeech;
    private String infinitive = "";
    private String pastSimple = "";
    private String pastParticiple = "";
    private Boolean appear = false;
    private String searchWord = "";
    private ImageView fragment_ask;
    private Button btnspeak;
    private ImageView quiz;



    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView2 = findViewById(R.id.three_forms);
        textView = findViewById(R.id.agreement);
        button = findViewById(R.id.button);
        fragment_ask = findViewById(R.id.fragment_ask);
        editText = findViewById(R.id.editText);
        btnspeak = findViewById(R.id.btnspeak);
        editText = findViewById(R.id.editText);
        textToSpeech = new TextToSpeech(this, this);
        quiz = findViewById(R.id.quiz);

        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        });

        btnspeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                textToSpeech.speak(text, TextToSpeech.QUEUE_ADD, null);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().trim().equals("")) {
                    Toast.makeText(MainActivity.this,"Input verb",Toast.LENGTH_SHORT).show();
                }
                else {
                    searchWord = editText.getText().toString().toLowerCase();
                    searching(searchWord);
                    if (appear == true) {
                        textView.setText("Yes " + searchWord + " is irregularVerb !");
                        textView2.setText("1." + infinitive + "; 2." + pastSimple + "; 3." + pastParticiple);
                        fragment_ask.setVisibility(View.VISIBLE);

                    } else if (searchWord.equals("were")) {
                        textView.setText("Yes " + searchWord + " is irregularVerb !");
                        textView2.setText("1.be; 2.was,were; 3.been");
                        fragment_ask.setVisibility(View.VISIBLE);
                    } else
                        textView.setText("No " + searchWord + " isn't irregularVerb !");
                    fragment_ask.setVisibility(View.VISIBLE);
                }}});




    }
    private void searching(String s){
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = null;
            Document doc = null;

            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(getAssets().open("irregularverbs.xml"));
            doc.getDocumentElement().normalize();
            NodeList verbsList = doc.getElementsByTagName("verb");
            for (int i = 0; i < verbsList.getLength(); i++) {
                Element verb = (Element) verbsList.item(i);
                infinitive = verb.getElementsByTagName("infinitive").item(0).getTextContent().toLowerCase();
                pastSimple = verb.getElementsByTagName("pastsimple").item(0).getTextContent().toLowerCase();
                pastParticiple = verb.getElementsByTagName("pastparticiple").item(0).getTextContent().toLowerCase();

                if (infinitive.equals(s) || pastParticiple.equals(s) || pastSimple.equals(s)) {
                    appear = true;
                    break;
                }
                else {
                    appear = false;
                }
            }
        }
         catch (IOException | SAXException | NullPointerException | ParserConfigurationException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = textToSpeech.setLanguage(Locale.US);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                // Language data is missing or the language is not supported.
            }
        } else {
            // Initialization failed.
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }
}










package com.aocompany.irregularVerbs;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class QuizActivity extends AppCompatActivity {
    private RadioGroup question1, question2, question3, question4, question5, question6, question7, question8, question9, question10;
    private TextView askText;
    private boolean lose = false;
    private ImageView exitButton;
    private ImageView nextButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_1);
        exitButton = findViewById(R.id.exitButton);
        askText = findViewById(R.id.textViewask);
        question1 = findViewById(R.id.question1);
        question2 = findViewById(R.id.question2);
        question3 = findViewById(R.id.question3);
        question4 = findViewById(R.id.question4);
        question5 = findViewById(R.id.question5);
        question6 = findViewById(R.id.question6);
        question7 = findViewById(R.id.question7);
        question8 = findViewById(R.id.question8);
        question9 = findViewById(R.id.question9);
        question10 = findViewById(R.id.question10);
        ArrayList<RadioGroup> radioGroups = new ArrayList<RadioGroup>();
        radioGroups.add(question1);
        radioGroups.add(question2);
        radioGroups.add(question3);
        radioGroups.add(question4);
        radioGroups.add(question5);
        radioGroups.add(question6);
        radioGroups.add(question7);
        radioGroups.add(question8);
        radioGroups.add(question9);
        radioGroups.add(question10);
        nextButton = findViewById(R.id.nextButton);
        randomizer(radioGroups);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


    @SuppressLint("DiscouragedApi")
    private void randomizer(ArrayList<RadioGroup> radioGroups) {
        int randnumber = ThreadLocalRandom.current().nextInt(0, 9);
        switch (randnumber) {
            case 0:
                askText.setText(" What is the past tense of \"eat\"?");
                break;
            case 1:
                askText.setText("What is the past participle of \"go\"?");
                break;
            case 2:
                askText.setText("What is the past participle of \"sing\"?");
                break;
            case 3:
                askText.setText("What is the past tense of \"swim\"?");
                break;
            case 4:
                askText.setText("What is the past participle of \"drink\"?");
                break;
            case 5:
                askText.setText("What is the past tense of \"be\"?");
                break;
            case 6:
                askText.setText("What is the past tense of \"run\"?");
                break;
            case 7:
                askText.setText("What is the past participle of \"write\"?");
                break;
            case 8:
                askText.setText("What is the past participle of \"read\"?");
                break;
            case 9:
                askText.setText("What is the past tense of \"see\"?");
                break;

        }

        radioGroups.get(randnumber).setVisibility(View.VISIBLE);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer checkedId = radioGroups.get(randnumber).getCheckedRadioButtonId();
                        switch (checkedId) {
                            case R.id.question1_right_answer:
                                radioGroups.get(randnumber).setVisibility(View.GONE);
                                randomizer(radioGroups);
                                radioGroups.get(randnumber).clearCheck();
                                break;
                            case R.id.question2_right_answer:
                                radioGroups.get(randnumber).setVisibility(View.GONE);
                                randomizer(radioGroups);
                                radioGroups.get(randnumber).clearCheck();
                                break;
                            case R.id.question3_right_answer :
                                radioGroups.get(randnumber).setVisibility(View.GONE);
                                randomizer(radioGroups);
                                radioGroups.get(randnumber).clearCheck();
                                break;
                            case R.id.question4_right_answer:
                                radioGroups.get(randnumber).setVisibility(View.GONE);
                                randomizer(radioGroups);
                                radioGroups.get(randnumber).clearCheck();
                                break;
                            case R.id.question5_right_answer:
                                radioGroups.get(randnumber).setVisibility(View.GONE);
                                randomizer(radioGroups);
                                radioGroups.get(randnumber).clearCheck();
                                break;
                            case R.id.question6_right_answer:
                                radioGroups.get(randnumber).setVisibility(View.GONE);
                                randomizer(radioGroups);
                                radioGroups.get(randnumber).clearCheck();
                                break;
                            case R.id.question7_right_answer:
                                radioGroups.get(randnumber).setVisibility(View.GONE);
                                randomizer(radioGroups);
                                radioGroups.get(randnumber).clearCheck();
                                break;
                            case R.id.question8_right_answer:
                                radioGroups.get(randnumber).setVisibility(View.GONE);
                                randomizer(radioGroups);
                                radioGroups.get(randnumber).clearCheck();
                                break;
                            case R.id.question9_right_answer:
                                radioGroups.get(randnumber).setVisibility(View.GONE);
                                randomizer(radioGroups);
                                radioGroups.get(randnumber).clearCheck();
                                break;
                            case R.id.question10_right_answer:
                                radioGroups.get(randnumber).setVisibility(View.GONE);
                                randomizer(radioGroups);
                                radioGroups.get(randnumber).clearCheck();
                                break;
                            default:
                                Toast.makeText(QuizActivity.this,"You lost !",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(QuizActivity.this, MainActivity.class);
                                startActivity(intent);
                        }
                    }


        });
    }
    }





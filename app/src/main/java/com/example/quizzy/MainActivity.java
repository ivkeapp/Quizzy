package com.example.quizzy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //TODO: Declare constant variables

    //TODO: Declare member variables
    Button mTrueButton;
    Button mFalseButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTrueButton = findViewById(R.id.true_btn);
        mFalseButton = findViewById(R.id.false_btn);
    }
}

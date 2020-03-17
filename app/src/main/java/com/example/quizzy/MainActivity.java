package com.example.quizzy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizzy.model.TrueFalse;

public class MainActivity extends AppCompatActivity {

    //TODO: Declare constant variables
    final int PROGRESS_BAR_INCREMENT = 1;


    //TODO: Declare member variables
    Button mTrueButton;
    Button mFalseButton;
    TextView mQuestionTextView, mScoreTextView;
    ProgressBar mProgressBar;

    int mIndex;
    int mQuestion;
    int mScore;

    private TrueFalse [] mQuestions = new TrueFalse[]{
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, false),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, false),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, true),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, false)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTrueButton = findViewById(R.id.true_btn);
        mFalseButton = findViewById(R.id.false_btn);
        mQuestionTextView = findViewById(R.id.question);
        mScoreTextView = findViewById(R.id.score);
        mProgressBar = findViewById(R.id.progress);

        mQuestion = mQuestions[mIndex].getmQuestionID();
        mQuestionTextView.setText(mQuestion);

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
                updateQuestion();
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
                updateQuestion();
            }
        });
    }

    private void updateQuestion() {
        mIndex = (mIndex + 1) % mQuestions.length;

        if(mIndex == 0) {
            AlertDialog.Builder ad = new AlertDialog.Builder(this);
            if(mScore>6) {
                ad.setTitle("You passed the test!");
            } else {
                ad.setTitle("Unfortunately you did not passed the test.");
            }
            ad.setCancelable(false);
            ad.setMessage("Your score is " + mScore + "/10");
            ad.setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    recreate();
                    mProgressBar.setProgress(0);
                }
            });
            ad.setNegativeButton("Close app", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            ad.show();
        }
        Log.d("Quizzy", String.valueOf(mIndex));

        mQuestion = mQuestions[mIndex].getmQuestionID();
        mQuestionTextView.setText(mQuestion);
        mProgressBar.incrementProgressBy(PROGRESS_BAR_INCREMENT);
        mScoreTextView.setText("Score " + mScore + "/" + mQuestions.length);
    }

    private void checkAnswer(boolean userSelected) {

        boolean correctAnswer = mQuestions[mIndex].ismIsTrue();

        if(userSelected == correctAnswer){
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            mScore ++;
        } else {
            Toast.makeText(this, "Wrong answer!", Toast.LENGTH_SHORT).show();
        }

    }
}

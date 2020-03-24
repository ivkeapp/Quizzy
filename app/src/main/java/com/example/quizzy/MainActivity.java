package com.example.quizzy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizzy.model.TrueFalse;
import com.example.quizzy.retrofit.APIUtils;
import com.example.quizzy.retrofit.GetSelectedQuestions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    final int PROGRESS_BAR_INCREMENT = 1;

    Button mTrueButton;
    Button mFalseButton;
    TextView mQuestionTextView, mScoreTextView;
    ProgressBar mProgressBar;

    int mIndex;
    String mQuestion;
    int mScore;

    final TrueFalse[] mQuestions = new TrueFalse[10];

//    private TrueFalse [] mQuestions = new TrueFalse[]{
//            new TrueFalse(R.string.question_1, true),
//            new TrueFalse(R.string.question_2, false),
//            new TrueFalse(R.string.question_3, true),
//            new TrueFalse(R.string.question_4, false),
//            new TrueFalse(R.string.question_5, true),
//            new TrueFalse(R.string.question_6, false),
//            new TrueFalse(R.string.question_7, true),
//            new TrueFalse(R.string.question_8, true),
//            new TrueFalse(R.string.question_9, true),
//            new TrueFalse(R.string.question_10, false)
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){
            Log.d("Quizzy", String.valueOf(savedInstanceState.getInt("Score")));
            mScore = savedInstanceState.getInt("Score");
            mIndex = savedInstanceState.getInt("Index");
        } else {
            //mScore = 0;
            //mIndex = 0;
        }

        mTrueButton = findViewById(R.id.true_btn);
        mFalseButton = findViewById(R.id.false_btn);
        mQuestionTextView = findViewById(R.id.question);
        mScoreTextView = findViewById(R.id.score);
        mProgressBar = findViewById(R.id.progress);



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



        GetSelectedQuestions getServise;
        getServise = APIUtils.getAPIServiceFetchAll();
        getServise.getAllNews().enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    //Log.d("responser", response.body());
                    if (response.body() != null) {
                        String JSONresponse = response.body();
                        //Log.d("responser", JSONresponse);
                        try {
                            JSONObject obj = new JSONObject(response.body());
                            JSONArray dataArray  = obj.getJSONArray("data");
                            for (int i = 0; i < dataArray.length(); i++){
                                String question;
                                int IntIsTrue;
                                boolean isTrue = false;

                                JSONObject dataobj = dataArray.getJSONObject(i);
                                question = dataobj.getString("question");
                                IntIsTrue = dataobj.getInt("is_true");
                                if(IntIsTrue==1){
                                    isTrue = true;
                                }
                                Log.d("responser", question + isTrue);
                                mQuestions[i] = new TrueFalse(i, isTrue, question);
                                mScoreTextView.setText("Score " + mScore + "/" + mQuestions.length);
                                mQuestion = mQuestions[mIndex].getmQuestion();
                                mQuestionTextView.setText(mQuestion);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("responser", t.getMessage());
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

        mQuestion = mQuestions[mIndex].getmQuestion();
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("Score", mScore);
        outState.putInt("Index", mIndex);
    }
}

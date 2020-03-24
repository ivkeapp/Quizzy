package com.example.quizzy.model;

public class TrueFalse {

    private int mQuestionID;
    private boolean mIsTrue;
    private String mQuestion;

    public TrueFalse(int mQuestionID, boolean mIsTrue, String mQuestion) {
        this.mQuestionID = mQuestionID;
        this.mIsTrue = mIsTrue;
        this.mQuestion = mQuestion;
    }

    public int getmQuestionID() {
        return mQuestionID;
    }

    public void setmQuestionID(int mQuestionID) {
        this.mQuestionID = mQuestionID;
    }

    public boolean ismIsTrue() {
        return mIsTrue;
    }

    public String getmQuestion() {
        return mQuestion;
    }

    public void setmQuestion(String mQuestion) {
        this.mQuestion = mQuestion;
    }

    public void setmIsTrue(boolean mIsTrue) {
        this.mIsTrue = mIsTrue;
    }
}

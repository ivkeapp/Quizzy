package com.example.quizzy.model;

public class TrueFalse {

    private int mQuestionID;
    private boolean mIsTrue;

    public TrueFalse(int mQuestionID, boolean mIsTrue) {
        this.mQuestionID = mQuestionID;
        this.mIsTrue = mIsTrue;
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

    public void setmIsTrue(boolean mIsTrue) {
        this.mIsTrue = mIsTrue;
    }
}

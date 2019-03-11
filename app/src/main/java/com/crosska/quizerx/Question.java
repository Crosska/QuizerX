package com.crosska.quizerx;

public class Question {

    public int getTextResId() {
        return mTextResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerIsTrue;
    }

    private int mTextResId;
    private boolean mAnswerIsTrue;

    public Question(int textResId, boolean answerTrue) {
        mTextResId = textResId;
        mAnswerIsTrue = answerTrue;
    }

}

package com.crosska.quizerx;

public class Question {
    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerIsTrue;
    }

    public void setAnswerIsTrue(boolean answerIsTrue) {
        mAnswerIsTrue = answerIsTrue;
    }

    private int mTextResId;
    private boolean mAnswerIsTrue;

    public Question(int textResId, boolean answerTrue) {
        mTextResId = textResId;
        mAnswerIsTrue = answerTrue;
    }

}

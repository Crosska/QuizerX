package com.crosska.quizerx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private TextView mQuestionTextView;

    private Question[] mQuestionBank = new Question[]{ // Массив вопросов
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };

    private int mCurrentIndex = 0; // Текущий индекс вопроса

    @Override
    protected void onCreate(Bundle savedInstanceState) { // Метод создания активности
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mQuestionTextView = findViewById(R.id.question_text_view);
        updateQuestion();
    }

    public void true_button_pressed(View view) { // Действие при нажатии на кнопку "Правда"
        checkAnswer(true);
    }

    public void false_button_pressed(View view) { // Действие при нажатии на кнопку "Ложь"
        checkAnswer(false);
    }

    private void showToast(int ToastType) { // Метод показа toast-сообщений
        switch (ToastType) {
            case 1: // Действия при правильном ответе
                Toast toast_tr = Toast.makeText(getApplicationContext(), R.string.correct_toast, Toast.LENGTH_SHORT);
                toast_tr.setGravity(Gravity.BOTTOM, -200, 50);
                toast_tr.show();
                break;
            case 2: // Действия при неправильном ответе
                Toast toast_fl = Toast.makeText(getApplicationContext(), R.string.incorrect_toast, Toast.LENGTH_SHORT);
                toast_fl.setGravity(Gravity.BOTTOM, 200, 50);
                toast_fl.show();
                break;
            default: // Действия при ошибке
                Toast toast_er = Toast.makeText(getApplicationContext(), R.string.error_toast, Toast.LENGTH_LONG);
                toast_er.setGravity(Gravity.CENTER, 0, 0);
                toast_er.show();
                break;
        }
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        if (userPressedTrue == answerIsTrue) {
            showToast(1);
        } else {
            showToast(2);
        }
    }

    public void next_button_pressed(View view) { // Действие при нажатии на кнопку "Далее"
        if (mCurrentIndex == mQuestionBank.length-1) mCurrentIndex = 0;
        else mCurrentIndex++;
        updateQuestion();
    }

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId(); // Метод обновления вопроса на экране
        mQuestionTextView.setText(question);
    }

    public void text_view_pressed(View view) {
        if (mCurrentIndex == mQuestionBank.length-1) mCurrentIndex = 0;
        else mCurrentIndex++;
        updateQuestion();
    }
}

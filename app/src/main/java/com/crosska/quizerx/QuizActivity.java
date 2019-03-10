package com.crosska.quizerx;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index"; // Ключи для сохраняемого значения
    private static final String KEY_ACCESS = "access";
    private static final String KEY_RIGHT_ANSWER = "right";
    private static final String KEY_IS_CHEATER = "cheater";
    private static final int REQUEST_CODE_CHEAT = 0;

    private TextView mQuestionTextView;
    private Button mTrueButton;
    private Button mFalseButton;

    private Question[] mQuestionBank = new Question[]{ // Массив вопросов
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };

    private boolean[] mButtonAccess = new boolean[]{true, true, true, true, true, true}; // Массив доступа к кнопкам
    private int mNumberOfRightAnswers = 0;  // Количество правильных ответов ответов
    private int mCurrentIndex = 0; // Текущий индекс вопроса
    private boolean mIsCheater;

    @Override
    protected void onCreate(Bundle savedInstanceState) { // Метод создания активности
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called"); // Логгирование запуска
        setContentView(R.layout.activity_quiz); // Запуск активити
        if (savedInstanceState != null) { // Проверка существует ли значение mCurrentIndex
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            mNumberOfRightAnswers = savedInstanceState.getInt(KEY_RIGHT_ANSWER);
            mButtonAccess = savedInstanceState.getBooleanArray(KEY_ACCESS);
            mIsCheater = savedInstanceState.getBoolean(KEY_IS_CHEATER);
        }
        mQuestionTextView = findViewById(R.id.question_text_view);
        mFalseButton = findViewById(R.id.false_button);
        mTrueButton = findViewById(R.id.true_button);
        updateQuestion();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) { // Метод для записи значения в Bundle
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex); // Сохраняем значение mCurrentIndex с использованием ключа KEY_INDEX
        savedInstanceState.putBooleanArray(KEY_ACCESS, mButtonAccess); // Сохраняем значение массива mButtonAccess с использованием ключа KEY_ACCESS
        savedInstanceState.putInt(KEY_RIGHT_ANSWER, mNumberOfRightAnswers); // Сохраняем значение mNumberOfRightAnswers с использованием ключа KEY_RIGHT_ANSWER
        savedInstanceState.putBoolean(KEY_IS_CHEATER, mIsCheater);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE_CHEAT) {
            if (data == null) {
                return;
            }
            mIsCheater = CheatActivity.wasAnswerShown(data);
        }
    }

    public void true_button_pressed(View view) { // Действие при нажатии на кнопку "Правда"
        checkAnswer(true);
    }

    public void false_button_pressed(View view) { // Действие при нажатии на кнопку "Ложь"
        checkAnswer(false);
    }

    private void showToast(int ToastType, int mRightPercent) { // Метод показа toast-сообщений
        switch (ToastType) {
            case 1: // Действия при правильном ответе
                Toast toast_true = Toast.makeText(getApplicationContext(), R.string.correct_toast, Toast.LENGTH_SHORT);
                toast_true.setGravity(Gravity.BOTTOM, -200, 50);
                toast_true.show();
                break;
            case 2: // Действия при неправильном ответе
                Toast toast_false = Toast.makeText(getApplicationContext(), R.string.incorrect_toast, Toast.LENGTH_SHORT);
                toast_false.setGravity(Gravity.BOTTOM, 200, 50);
                toast_false.show();
                break;
            case 3: // Действия при первом вопросе
                Toast toast_first_question = Toast.makeText(getApplicationContext(), R.string.first_question_toast, Toast.LENGTH_SHORT);
                toast_first_question.setGravity(Gravity.BOTTOM, -100, 50);
                toast_first_question.show();
                break;
            case 4: // Действия при последнем вопросе
                Toast toast_last_question = Toast.makeText(getApplicationContext(), R.string.last_question_toast, Toast.LENGTH_SHORT);
                toast_last_question.setGravity(Gravity.BOTTOM, 100, 50);
                toast_last_question.show();
                break;
            case 5: // Действие при последнем ответе
                Toast toast_percent_answers = Toast.makeText(getApplicationContext(), "У вас " + mRightPercent + "% правильных ответов!", Toast.LENGTH_LONG);
                toast_percent_answers.setGravity(Gravity.BOTTOM, 0, 250);
                toast_percent_answers.show();
                break;
            case 6:
                Toast toast_judgement = Toast.makeText(getApplicationContext(), R.string.judgment_toast, Toast.LENGTH_SHORT);
                toast_judgement.setGravity(Gravity.BOTTOM, 0, 250);
                toast_judgement.show();
                break;
            default: // Действия при ошибке
                Toast toast_error = Toast.makeText(getApplicationContext(), R.string.error_toast, Toast.LENGTH_LONG);
                toast_error.setGravity(Gravity.CENTER, 0, 0);
                toast_error.show();
                break;
        }
    }

    private void checkAnswer(boolean userPressedTrue) { // Метод проверки правильности ответа
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        mButtonAccess[mCurrentIndex] = false;
        if (mIsCheater) {
            showToast(6, 0);
            mButtonAccess[mCurrentIndex] = false;
        } else {
            if (userPressedTrue == answerIsTrue) {
                showToast(1, 0);
                mNumberOfRightAnswers++;
            } else {
                showToast(2, 0);
            }
        }
            if (!mButtonAccess[0] && !mButtonAccess[1] && !mButtonAccess[2] && !mButtonAccess[3] && !mButtonAccess[4] && !mButtonAccess[5]) {
                int mPercentAnswers = (100 / 6) * mNumberOfRightAnswers;
                if (mPercentAnswers > 100) mPercentAnswers = 100;
                showToast(5, mPercentAnswers);
            }
            updateButtonAccess();
    }

    public void next_button_pressed(View view) { // Действие при нажатии на кнопку "Далее"
        if (mCurrentIndex == mQuestionBank.length - 1) showToast(4, 0);
        else mCurrentIndex++;
        mIsCheater = false;
        updateQuestion();
    }

    public void back_button_pressed(View view) { // Действие при нажатии на кнопку "Назад"
        if (mCurrentIndex == 0) showToast(3, 0);
        else mCurrentIndex--;
        updateQuestion();
    }

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId(); // Метод обновления вопроса на экране
        mQuestionTextView.setText(question);
        updateButtonAccess();
    }

    private void updateButtonAccess() { // Метод обновления доступности кнопок
        mTrueButton.findViewById(R.id.true_button);
        mFalseButton.findViewById(R.id.false_button);
        if (!mButtonAccess[mCurrentIndex]) {
            mTrueButton.setEnabled(false);
            mFalseButton.setEnabled(false);
        } else {
            mTrueButton.setEnabled(true);
            mFalseButton.setEnabled(true);
        }
    }

    public void cheat_button_pressed(View view) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        Intent intent = CheatActivity.newIntent(QuizActivity.this, answerIsTrue); // Создание обьекта "интент", сообщающий ActivityManager какую активность следует запустить
        startActivityForResult(intent, REQUEST_CODE_CHEAT);
    }
}

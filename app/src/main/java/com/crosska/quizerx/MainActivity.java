package com.crosska.quizerx;

import android.icu.text.UnicodeSetSpanner;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void true_button_pressed(View view) {
        showToast(1);
    }

    public void false_button_pressed(View view) {
        showToast(2);
    }

    private void showToast(int ToastType) {
        switch (ToastType)
        {
            case 1:
                Toast toast_tr = Toast.makeText(getApplicationContext(), R.string.correct_toast, Toast.LENGTH_SHORT);
                toast_tr.setGravity(Gravity.TOP, 0, 0);
                toast_tr.show();
                break;
            case 2:
                Toast toast_fl = Toast.makeText(getApplicationContext(), R.string.incorrect_toast, Toast.LENGTH_SHORT);
                toast_fl.setGravity(Gravity.TOP, 0, 0);
                toast_fl.show();
                break;
            default:
                Toast toast_er = Toast.makeText(getApplicationContext(), R.string.error_toast, Toast.LENGTH_LONG);
                toast_er.setGravity(Gravity.TOP, 0, 0);
                toast_er.show();
                break;
        }
    }

}

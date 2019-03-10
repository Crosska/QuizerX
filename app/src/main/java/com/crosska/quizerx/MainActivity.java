package com.crosska.quizerx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void false_button_pressed(View view) {
        Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
    }

    public void true_button_pressed(View view) {
        Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
    }
}

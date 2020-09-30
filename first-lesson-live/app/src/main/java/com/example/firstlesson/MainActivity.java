package com.example.firstlesson;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int score = 0;
    private Button leftButton, rightButton;
    private TextView scoreTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        leftButton = findViewById(R.id.leftButton);
        rightButton = findViewById(R.id.rightButton);

        refreshButtonRandomNumber();

        scoreTextView = findViewById(R.id.scoreTextView);
    }

    private void refreshButtonRandomNumber() {
        Random random = new Random();
        Integer randomNumber = random.nextInt(100);
        Integer anotherRandomNumber;

        do {
            anotherRandomNumber = random.nextInt(100);
        } while( anotherRandomNumber == randomNumber);

        leftButton.setText(Integer.toString(randomNumber));
        rightButton.setText(Integer.toString(anotherRandomNumber));
    }

    private void updateScoreTextView() {
        score++;
        scoreTextView.setText("Score : " + score);
    }

    public void buttonClicked(View view) {
        Button buttonClicked = (Button) view;
        int buttonClickedValue = Integer.parseInt(buttonClicked.getText().toString());

        int leftValue = Integer.parseInt(leftButton.getText().toString());
        int rightValue = Integer.parseInt(rightButton.getText().toString());

        if (leftValue > rightValue && leftValue == buttonClickedValue) {
            updateScoreTextView();
        } else if (rightValue > leftValue && rightValue == buttonClickedValue) {
            updateScoreTextView();
        } else {
        }

        refreshButtonRandomNumber();
    }
}
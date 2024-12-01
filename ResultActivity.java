package com.example.mathquiz;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView textScore;
    private Button buttonRestart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Link XML views
        textScore = findViewById(R.id.textScore);
        buttonRestart = findViewById(R.id.buttonRestart);

        // Get data from Intent
        Intent intent = getIntent();
        int correctAnswers = intent.getIntExtra("RA", 0);

        // Assuming total questions is always 10 for this app
        int totalQuestions = 10;

        // Display the score
        textScore.setText(String.format("Your Score: %d / %d", correctAnswers, totalQuestions));

        // Restart quiz button listener
        buttonRestart.setOnClickListener(view -> {
            // Restart the quiz
            Intent restartIntent = new Intent(ResultActivity.this, MainActivity.class);
            restartIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Clear any back stack
            startActivity(restartIntent);
            finish();
        });
    }
}

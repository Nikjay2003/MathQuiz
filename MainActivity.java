package com.example.mathquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Views
    private TextView textLevel;
    private TextView textRightAnswered;
    private TextView textQuestion;
    private Button buttonOp1;
    private Button buttonOp2;
    private Button buttonOp3;

    // Quiz state variables
    private int level = 0;
    private int correctAnswers = 0;
    private int rightAnswer = 0;

    // Question-related variables
    private QuizRepository quizRepository;
    private List<Question> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Link XML views
        textLevel = findViewById(R.id.textLevel);
        textRightAnswered = findViewById(R.id.textRightAnswered);
        textQuestion = findViewById(R.id.textQuestion);
        buttonOp1 = findViewById(R.id.buttonOption1);
        buttonOp2 = findViewById(R.id.buttonOption2);
        buttonOp3 = findViewById(R.id.buttonOption3);

        // Initialize text
        textLevel.setText("Q : 0 / 10");
        textRightAnswered.setText("RA : 0 / 10");

        // Initialize repository and load questions
        quizRepository = new QuizRepository(this);
        questionList = quizRepository.getAllQuestions();

        // Ensure there are enough questions to run the quiz
        if (questionList.size() >= 10) {
            displayNextQuestion();
        } else {
            // Handle insufficient questions
            textQuestion.setText("Not enough questions in the database!");
            disableButtons();
        }
    }

    private void displayNextQuestion() {
        if (level < 10) {
            // Fetch the current question (in sequence, no repetition)
            Question currentQuestion = questionList.get(level);

            // Set the question and options
            textQuestion.setText(currentQuestion.getQuestion());
            buttonOp1.setText(currentQuestion.getOption1());
            buttonOp2.setText(currentQuestion.getOption2());
            buttonOp3.setText(currentQuestion.getOption3());

            // Store the correct answer index (1-based index from the database)
            rightAnswer = currentQuestion.getCorrectAnswer();

            // Reset button colors (default background)
            resetButtonColors();

            // Set button listeners
            setButtonClickListener(buttonOp1, 1);
            setButtonClickListener(buttonOp2, 2);
            setButtonClickListener(buttonOp3, 3);
        } else {
            // End of quiz, show toast with the score
            String scoreMessage = "Quiz Finished! Your Score: " + correctAnswers + " / 10";
            Toast.makeText(MainActivity.this, scoreMessage, Toast.LENGTH_LONG).show();

            // Move to results screen
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtra("RA", correctAnswers);
            startActivity(intent);
            finish();
        }
    }

    private void setButtonClickListener(Button button, int selectedAnswer) {
        button.setOnClickListener(view -> {
            // Check if the clicked answer is correct
            if (selectedAnswer == rightAnswer) {
                button.setBackgroundResource(R.drawable.right_answer_bg);
                correctAnswers++;
            } else {
                button.setBackgroundResource(R.drawable.wrong_answer_bg);
            }

            // Update question count and score
            level++;
            textLevel.setText("Q : " + level + " / 10");
            textRightAnswered.setText("RA : " + correctAnswers + " / 10");

            // Delay before moving to the next question
            new Handler(Looper.getMainLooper()).postDelayed(this::displayNextQuestion, 1000); // 1-second delay
        });
    }

    private void resetButtonColors() {
        buttonOp1.setBackgroundResource(R.drawable.button_option_bg);
        buttonOp2.setBackgroundResource(R.drawable.button_option_bg);
        buttonOp3.setBackgroundResource(R.drawable.button_option_bg);
    }

    private void disableButtons() {
        buttonOp1.setEnabled(false);
        buttonOp2.setEnabled(false);
        buttonOp3.setEnabled(false);
    }
}


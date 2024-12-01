package com.example.mathquiz;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class QuizRepository {

    private final QuizDatabaseHelper dbHelper;

    public QuizRepository(Context context) {
        dbHelper = new QuizDatabaseHelper(context);
    }

    // Get all questions
    public List<Question> getAllQuestions() {
        List<Question> questions = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Query to fetch all the questions
        String query = "SELECT * FROM " + QuizDatabaseHelper.TABLE_QUESTIONS;
        Cursor cursor = db.rawQuery(query, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    // Create a new Question object and set its properties
                    Question question = new Question();
                    question.setId(cursor.getInt(cursor.getColumnIndexOrThrow(QuizDatabaseHelper.COLUMN_ID)));
                    question.setQuestion(cursor.getString(cursor.getColumnIndexOrThrow(QuizDatabaseHelper.COLUMN_QUESTION)));
                    question.setOption1(cursor.getString(cursor.getColumnIndexOrThrow(QuizDatabaseHelper.COLUMN_OPTION1)));
                    question.setOption2(cursor.getString(cursor.getColumnIndexOrThrow(QuizDatabaseHelper.COLUMN_OPTION2)));
                    question.setOption3(cursor.getString(cursor.getColumnIndexOrThrow(QuizDatabaseHelper.COLUMN_OPTION3)));
                    question.setCorrectAnswer(cursor.getInt(cursor.getColumnIndexOrThrow(QuizDatabaseHelper.COLUMN_ANSWER)));

                    // Add the question to the list
                    questions.add(question);
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close(); // Always close the cursor
            db.close(); // Always close the database connection
        }

        return questions;
    }
}

package com.example.mathquiz;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class QuizDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "QuizDB"; // Database Name
    private static final int DATABASE_VERSION = 1; // Database Version

    // Table and columns
    public static final String TABLE_QUESTIONS = "questions";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_QUESTION = "question";
    public static final String COLUMN_OPTION1 = "option1";
    public static final String COLUMN_OPTION2 = "option2";
    public static final String COLUMN_OPTION3 = "option3";
    public static final String COLUMN_ANSWER = "answer";

    // Create table query
    private static final String CREATE_TABLE_QUESTIONS =
            "CREATE TABLE " + TABLE_QUESTIONS + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_QUESTION + " TEXT, " +
                    COLUMN_OPTION1 + " TEXT, " +
                    COLUMN_OPTION2 + " TEXT, " +
                    COLUMN_OPTION3 + " TEXT, " +
                    COLUMN_ANSWER + " INTEGER" +
                    ")";

    public QuizDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_QUESTIONS); // Create the questions table
        prepopulateQuestions(db); // Insert sample questions
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);
        onCreate(db);
    }

    // Add sample questions to the database
    private void prepopulateQuestions(SQLiteDatabase db) {
        // Insert each question into the database
        db.execSQL("INSERT INTO " + TABLE_QUESTIONS + " (question, option1, option2, option3, answer) VALUES " +
                "('6 + 2 = ?', '8', '10', '12', 1)");
        db.execSQL("INSERT INTO " + TABLE_QUESTIONS + " (question, option1, option2, option3, answer) VALUES " +
                "('5 - 3 = ?', '1', '2', '3', 2)");
        db.execSQL("INSERT INTO " + TABLE_QUESTIONS + " (question, option1, option2, option3, answer) VALUES " +
                "('10 ÷ 2 = ?', '2', '5', '10', 2)");
        db.execSQL("INSERT INTO " + TABLE_QUESTIONS + " (question, option1, option2, option3, answer) VALUES " +
                "('7 + 3 = ?', '9', '10', '11', 2)");
        db.execSQL("INSERT INTO " + TABLE_QUESTIONS + " (question, option1, option2, option3, answer) VALUES " +
                "('12 - 4 = ?', '6', '8', '9', 2)");
        db.execSQL("INSERT INTO " + TABLE_QUESTIONS + " (question, option1, option2, option3, answer) VALUES " +
                "('15 ÷ 3 = ?', '3', '4', '5', 3)");
        db.execSQL("INSERT INTO " + TABLE_QUESTIONS + " (question, option1, option2, option3, answer) VALUES " +
                "('9 × 2 = ?', '14', '16', '18', 3)");
        db.execSQL("INSERT INTO " + TABLE_QUESTIONS + " (question, option1, option2, option3, answer) VALUES " +
                "('8 + 5 = ?', '12', '13', '14', 2)");
        db.execSQL("INSERT INTO " + TABLE_QUESTIONS + " (question, option1, option2, option3, answer) VALUES " +
                "('18 ÷ 3 = ?', '5', '6', '7', 2)");
        db.execSQL("INSERT INTO " + TABLE_QUESTIONS + " (question, option1, option2, option3, answer) VALUES " +
                "('5 × 4 = ?', '18', '20', '22', 2)");
    }
}



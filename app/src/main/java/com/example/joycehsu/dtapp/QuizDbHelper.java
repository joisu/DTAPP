package com.example.joycehsu.dtapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.joycehsu.dtapp.QuizContract.QuestionsTable;

import java.util.ArrayList;
import java.util.List;

//CHANGE THISSSSSSSSSSSSSSSSSSSSSSSSSS IF YOU CHANGE THE PACKAGEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE

public class QuizDbHelper extends SQLiteOpenHelper {

    //Declaring standard database variable

    private SQLiteDatabase db;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "DTquiz.db";

    //Constructor for QuizDbHelper
    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        //SQL statement for creating questions table
        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NO + " INTEGER" + ")";

        //executing the SQL statement
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);

        populateQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        //When upgrading the database, drop existing table and recreate it, and then increment version
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    //Declares question data and then calls addQuestion() to populate the table
    private void populateQuestionsTable() {
        Question q1 = new Question("I think A is correct?", "A", "B", "C", 1);
        addQuestion(q1);
        Question q2 = new Question("I think B is correct?", "A", "B", "C", 2);
        addQuestion(q2);
        Question q3 = new Question("I think C is correct?", "A", "B", "C", 3);
        addQuestion(q3);
        Question q4 = new Question("I think A is correct #2", "A", "B", "C", 1);
        addQuestion(q4);
        Question q5 = new Question("I think B is correct #2", "A", "B", "C", 2);
        addQuestion(q5);
        Question q6 = new Question("Extra question 1 (pick A)", "A", "B", "C", 1);
        addQuestion(q6);
        Question q7 = new Question("Extra question 2 (Pick B)", "A", "B", "C", 2);
        addQuestion(q7);
    }

    //ContentValues uses key-value pairs to put the declared question data into the table
    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NO, question.getAnswerNo());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }


    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        //To retrieve questions from the database, we first take the full database with getReadableDatabase()
        db = getReadableDatabase();

        //We select ALL the data from the QuestionsTable
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        //While there are entries, this will go through each column of the table to get the data from each row
        //If there are no entries, this will return false.
        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNo(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NO)));

                //Add all the question objects to the questionList
                questionList.add(question);

            } while (c.moveToNext());
        }

        //Need to close cursor after using it
        c.close();

        //finally returns a questionList
        return questionList;
    }
}

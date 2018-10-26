package com.example.joycehsu.dtapp.Quiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.joycehsu.dtapp.Quiz.QuizContract.QuestionsTable;

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
        Question q1 = new Question("Design thinking ___ ideas during brainstorming", "builds up", "maintains", "narrows down", 1);
        addQuestion(q1);
        Question q2 = new Question("Design thinking encourages: ___", "robbing banks", "thinking outside the box", "bad behaviour", 2);
        addQuestion(q2);
        Question q3 = new Question("Who was one of the first authors to use the term 'design thinking?'?", "Kathy Xu", "Jeremy Fu", "John E. Arnold", 3);
        addQuestion(q3);
        Question q4 = new Question("In what UNSW UG INFS course is design thinking most thoroughly covered?", "INFS2603", "INFS1609", "INFS3634", 1);
        addQuestion(q4);
        Question q5 = new Question("What is a common early consideration in design thinking?", "Emanciate", "Empathise", "Empanada", 2);
        addQuestion(q5);
        Question q6 = new Question("Who can benefit from using design thinking?", "Everyone", "Me", "You", 1);
        addQuestion(q6);
        Question q7 = new Question("Stanford's d.school suggests how many main aspects to design thinking?", "1", "5", "42", 2);
        addQuestion(q7);
        Question q8 = new Question("Design thinking is ___", "the Shadow Minister for Transport", "a brand of cereal", "a way of thinking", 3);
        addQuestion(q8);
        Question q9 = new Question("According to John E. Arnold, what is a possible benefit of design thinking?", "The power of flight", "Access to Qantas Club Lounges", "Increased salability", 3);
        addQuestion(q9);
        Question q10 = new Question("Design thinking gained traction in what century?", "14th", "20th", "5th BC", 2);
        addQuestion(q10);
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

package com.example.joycehsu.dtapp.Quiz;

import android.provider.BaseColumns;

public final class QuizContract {

    //placeholder as good practice; prevents objects being made from QuizContract
    //QuizContract class should hold my declared constants below only.
    private QuizContract() {
    }

    //declaring constants; allows me to change all instances of them in one place conveniently
    //BaseColumns interface has _ID column which I've included in the database
    //didn't actually use ID because it's not necessary for the small database
    //but the functionality is there if I need to use it in the future (new entries etc.)
    public static class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME = "quiz_questions";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_OPTION1 = "option1";
        public static final String COLUMN_OPTION2 = "option2";
        public static final String COLUMN_OPTION3 = "option3";
        public static final String COLUMN_ANSWER_NO = "answer_no";
    }
}

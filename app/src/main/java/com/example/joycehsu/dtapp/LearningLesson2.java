package com.example.joycehsu.dtapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;


public class LearningLesson2 extends YouTubeBaseActivity {

    private static final String TAG = "LearningLesson2";

    public static final String TRANSFER_LEARNED = "transferlearned";
    private TextView textviewLearningLesson2Text1;
    private TextView textviewLearningLesson2Text2;
    private TextView textviewLearningLesson2Text3;
    private TextView textviewLearningLesson2Text4;
    private Button buttonSeeMore;
    private Button buttonSeeEvenMore;
    private Button buttonLearned;

    private YouTubePlayerView view_youtubePlayer;
    private Button button_youtubePlayer;

    YouTubePlayer.OnInitializedListener onInitializedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_lesson2);
        Log.d(TAG, "onCreate: Starting LearningLesson2");

        button_youtubePlayer = (Button) findViewById(R.id.button_youtubeplayer);
        view_youtubePlayer = (YouTubePlayerView) findViewById(R.id.view_youtubeplayer);

        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo("eW9wwZdIJ5M");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d(TAG, "onClick: Failed to initialize YouTube Player");
            }
        };

        button_youtubePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Initializing YouTube Player");
                view_youtubePlayer.initialize(YouTubeConfig.getApiKey(), onInitializedListener);

            }
        });

        textviewLearningLesson2Text1 = (TextView) findViewById(R.id.textview_learninglesson2text1);
        textviewLearningLesson2Text2 = (TextView) findViewById(R.id.textview_learninglesson2text2);
        textviewLearningLesson2Text3 = (TextView) findViewById(R.id.textview_learninglesson2text3);
        textviewLearningLesson2Text4 = (TextView) findViewById(R.id.textview_learninglesson2text4);
        buttonSeeMore = (Button) findViewById(R.id.button_seemore);
        buttonSeeEvenMore = (Button) findViewById(R.id.button_seeevenmore);
        buttonLearned = (Button) findViewById(R.id.button_learned);

        buttonSeeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSeeMore.setVisibility(View.INVISIBLE);
                textviewLearningLesson2Text3.setVisibility(View.VISIBLE);
                buttonSeeEvenMore.setVisibility(View.VISIBLE);

            }
        });

        buttonSeeEvenMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSeeEvenMore.setVisibility(View.INVISIBLE);
                textviewLearningLesson2Text4.setVisibility(View.VISIBLE);
                buttonLearned.setVisibility(View.VISIBLE);

            }
        });

        buttonLearned.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent(LearningLesson2.this, LearningActivity.class);
                resultIntent.putExtra(TRANSFER_LEARNED, 2   );
                setResult(RESULT_OK, resultIntent);
                finish();

            }
        });



        textviewLearningLesson2Text1.setText("The History of Design Thinking");

        textviewLearningLesson2Text2.setText("John E. Arnold was one of the first authors to use the term 'design thinking'. In \"Creative Engineering\" (1959) he distinguishes four areas of design thinking. According to Arnold, design thinking can yield (1) novel functionality, i.e. solutions that satisfy a novel need or solutions that satisfy an old need in an entirely new way, (2) higher performance levels of a solution, (3) lower production costs or (4) increased salability. Thus, according to this early concept, ‘design thinking’ covers all forms of product innovation, including especially incremental innovation (“higher performance”) and radical innovation (“novel functionality”). Arnold recommends a balanced approach: Product developers should seek opportunities in all four areas of design thinking.");

        textviewLearningLesson2Text3.setText("It is rather interesting to look over the developmental history of any product or family of products and try to classify the changes into one of the four areas. It might be a good idea for each one of you to do that for your own company’s products. Your group, too, might have gotten into a rut and is inadvertently doing all of your design thinking in one area and is missing good bets in other areas.\n" +
                "\n" +
                "— J.E. Arnold, 1959/2016");

        textviewLearningLesson2Text4.setText("Another early author to use the term 'design thinking' was L.Bruce Archer in his book \"Systematic Method for Designers\" (1965). The notion of design as a \"way of thinking\" in the sciences can be traced to Herbert A. Simon's 1969 book The Sciences of the Artificial, and in design engineering to Robert McKim's 1973 book Experiences in Visual Thinking. Bryan Lawson's 1980 book How Designers Think, primarily addressing design in architecture, began a process of generalising the concept of design thinking. A 1982 article by Nigel Cross on Designerly ways of knowing established some of the intrinsic qualities and abilities of design thinking that also made it relevant in general education and thus for wider audiences. Peter Rowe's 1987 book Design Thinking, which described methods and approaches used by architects and urban planners, was a significant early usage of the term in the design research literature. Rolf Faste expanded on McKim's work at Stanford University in the 1980s and 1990s, teaching \"design thinking as a method of creative action.\" Design thinking was adapted for business purposes by Faste's Stanford colleague David M. Kelley, who founded the design consultancy IDEO in 1991. Richard Buchanan's 1992 article \"Wicked Problems in Design Thinking\" expressed a broader view of design thinking as addressing intractable human concerns through design.");



    }

    @Override
    public void onBackPressed() {
        Intent resultIntent = new Intent(LearningLesson2.this, LearningActivity.class);
        resultIntent.putExtra(TRANSFER_LEARNED, 1);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}

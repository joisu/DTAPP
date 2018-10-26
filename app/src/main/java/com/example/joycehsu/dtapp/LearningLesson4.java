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


public class LearningLesson4 extends YouTubeBaseActivity {

    private static final String TAG = "LearningLesson3";

    public static final String TRANSFER_LEARNED = "transferlearned";
    private TextView textviewLearningLesson4Text1;
    private TextView textviewLearningLesson4Text2;
    private TextView textviewLearningLesson4Text3;
    private TextView textviewLearningLesson4Text4;
    private Button buttonSeeMore;
    private Button buttonSeeEvenMore;
    private Button buttonLearned;

    private YouTubePlayerView view_youtubePlayer;
    private Button button_youtubePlayer;

    YouTubePlayer.OnInitializedListener onInitializedListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_lesson4);

        Log.d(TAG, "onCreate: Starting LearningLesson4");

        button_youtubePlayer = (Button) findViewById(R.id.button_youtubeplayer);
        view_youtubePlayer = (YouTubePlayerView) findViewById(R.id.view_youtubeplayer);

        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.d(TAG, "onClick: Done initializing YouTube Player");
                youTubePlayer.loadVideo("GPXeeyL4tEA");
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

        textviewLearningLesson4Text1 = (TextView) findViewById(R.id.textview_learninglesson4text1);
        textviewLearningLesson4Text2 = (TextView) findViewById(R.id.textview_learninglesson4text2);
        textviewLearningLesson4Text3 = (TextView) findViewById(R.id.textview_learninglesson4text3);
        textviewLearningLesson4Text4 = (TextView) findViewById(R.id.textview_learninglesson4text4);
        buttonSeeMore = (Button) findViewById(R.id.button_seemore);
        buttonSeeEvenMore = (Button) findViewById(R.id.button_seeevenmore);
        buttonLearned = (Button) findViewById(R.id.button_learned);

        buttonSeeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSeeMore.setVisibility(View.INVISIBLE);
                textviewLearningLesson4Text3.setVisibility(View.VISIBLE);
                buttonSeeEvenMore.setVisibility(View.VISIBLE);

            }
        });

        buttonSeeEvenMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSeeEvenMore.setVisibility(View.INVISIBLE);
                textviewLearningLesson4Text4.setVisibility(View.VISIBLE);
                buttonLearned.setVisibility(View.VISIBLE);

            }
        });

        buttonLearned.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent(LearningLesson4.this, LearningActivity.class);
                resultIntent.putExtra(TRANSFER_LEARNED, 2);
                setResult(RESULT_OK, resultIntent);
                finish();

            }
        });



        textviewLearningLesson4Text1.setText("Application of Design Thinking");

        textviewLearningLesson4Text2.setText("IN BUSINESS:\n" +
                "Historically, designers tended to be involved only in the later parts of the process of new product development, focusing their attention on the aesthetics and functionality of products. Many businesses and other organisations now realise the utility of embedding design as a productive asset throughout organisational policies and practices, and design thinking has been used to help many different types of business and social organisations to be more constructive and innovative. In the 2000s there was a significant growth of interest in design thinking as a catalyst for gaining competitive advantage within business, but doubts around design thinking as a panacea for success have also been expressed. Designers bring their methods into business either by taking part themselves from the earliest stages of product and service development processes or by training others to use design methods and to build innovative thinking capabilities within organisations.");

        textviewLearningLesson4Text3.setText("IN COMPUTER SCIENCE:\n" +
                "Design thinking has been central to user-centered design and human-centered design—the dominant methods of designing human-computer interfaces—for over 40 years. Design thinking is also central to recent conceptions of software development in general.");

        textviewLearningLesson4Text4.setText("IN EDUCATION\n" +
                "All forms of professional design education can be assumed to be developing design thinking in students, even if only implicitly, but design thinking is also now explicitly taught in general as well as professional education, across all sectors of education. Design as a subject was introduced into secondary schools' educational curricula in the UK in the 1970s, gradually replacing and/or developing from some of the traditional art and craft subjects, and increasingly linked with technology studies. This development sparked related research studies in both education and design.\n" +
                "\n" +
                "In the K-12 sector, design thinking is used to promote creative thinking, teamwork, and student responsibility for learning. New courses in design thinking have also been introduced at university level, especially where linked with business and innovation studies. A notable early course of this type was introduced at Stanford University in 2003, the Hasso Plattner Institute of Design, known as the d.school. It draws students from several Stanford departments, including engineering, medicine, business, law, and education, utilizing the d.school approach to design thinking to develop innovative solutions to problems. Also, the REDLab group, from Stanford's Graduate School of Education, conducts research into design thinking in K-12, secondary, and post-secondary settings.");



    }

    @Override
    public void onBackPressed() {
        Intent resultIntent = new Intent(LearningLesson4.this, LearningActivity.class);
        resultIntent.putExtra(TRANSFER_LEARNED, 1);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}

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



public class LearningLesson3 extends YouTubeBaseActivity {

    private static final String TAG = "LearningLesson3";

    public static final String TRANSFER_LEARNED = "transferlearned";
    private TextView textviewLearningLesson3Text1;
    private TextView textviewLearningLesson3Text2;
    private TextView textviewLearningLesson3Text3;
    private TextView textviewLearningLesson3Text4;
    private TextView textviewLearningLesson3Text5;
    private Button buttonSeeMore;
    private Button buttonSeeEvenMore;
    private Button buttonSeeEvenMore2;
    private Button buttonLearned;

    private YouTubePlayerView view_youtubePlayer;
    private Button button_youtubePlayer;

    YouTubePlayer.OnInitializedListener onInitializedListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_lesson3);

        Log.d(TAG, "onCreate: Starting LearningLesson3");

        button_youtubePlayer = (Button) findViewById(R.id.button_youtubeplayer);
        view_youtubePlayer = (YouTubePlayerView) findViewById(R.id.view_youtubeplayer);

        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.d(TAG, "onClick: Done initializing YouTube Player");
                youTubePlayer.loadVideo("a7sEoEvT8l8");
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

        textviewLearningLesson3Text1 = (TextView) findViewById(R.id.textview_learninglesson3text1);
        textviewLearningLesson3Text2 = (TextView) findViewById(R.id.textview_learninglesson3text2);
        textviewLearningLesson3Text3 = (TextView) findViewById(R.id.textview_learninglesson3text3);
        textviewLearningLesson3Text4 = (TextView) findViewById(R.id.textview_learninglesson3text4);
        textviewLearningLesson3Text5 = (TextView) findViewById(R.id.textview_learninglesson3text5);
        buttonSeeMore = (Button) findViewById(R.id.button_seemore);
        buttonSeeEvenMore = (Button) findViewById(R.id.button_seeevenmore);
        buttonSeeEvenMore2 = (Button) findViewById(R.id.button_seeevenmore2);
        buttonLearned = (Button) findViewById(R.id.button_learned);

        buttonSeeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSeeMore.setVisibility(View.INVISIBLE);
                textviewLearningLesson3Text3.setVisibility(View.VISIBLE);
                buttonSeeEvenMore.setVisibility(View.VISIBLE);

            }
        });

        buttonSeeEvenMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSeeEvenMore.setVisibility(View.INVISIBLE);
                textviewLearningLesson3Text4.setVisibility(View.VISIBLE);
                buttonSeeEvenMore2.setVisibility(View.VISIBLE);

            }
        });

        buttonSeeEvenMore2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSeeEvenMore2.setVisibility(View.INVISIBLE);
                textviewLearningLesson3Text5.setVisibility(View.VISIBLE);
                buttonLearned.setVisibility(View.VISIBLE);

            }
        });

        buttonLearned.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent(LearningLesson3.this, LearningActivity.class);
                resultIntent.putExtra(TRANSFER_LEARNED, 2);
                setResult(RESULT_OK, resultIntent);
                finish();

            }
        });



        textviewLearningLesson3Text1.setText("Process of Design Thinking");

        textviewLearningLesson3Text2.setText("As an approach, design thinking taps into innate human capacities that are overlooked by more conventional problem-solving practices. The process is best thought of as a system of overlapping spaces rather than a sequence of orderly steps: inspiration, ideation, and implementation; or alternatively: empathize, define, ideate, prototype and test. Projects may loop back through inspiration, ideation, and implementation more than once as the team refines its ideas and explores new directions. Therefore, design thinking can feel chaotic, but over the life of a project, participants come to see that the process makes sense and achieves results, even though its form differs from the linear, milestone-based processes that organizations typically undertake.");

        textviewLearningLesson3Text3.setText("Generally, the design process starts with the inspiration phase: understanding the problem or the opportunity. This understanding can be documented in a brief which includes constraints that gives the project team a framework from which to begin, benchmarks by which they can measure progress, and a set of objectives to be realized—such as price point, available technology, and market segment.\n" +
                "\n" +
                "Similarly, both Tom and David Kelley have stated that Design Thinking begins with empathy. Designers should approach users with the goal of understanding their wants and needs, what might make their life easier and more enjoyable and how technology can be useful for them. Empathic design transcends physical ergonomics to include understanding the psychological and emotional needs of people - the way they do things, why and how they think and feel about the world, and what is meaningful to them.");

        textviewLearningLesson3Text4.setText("Ideation is idea generation. Mentally it represents a process of \"going wide\" in terms of concepts and outcomes. The process is characterized by the alternation of divergent and convergent thinking, typical of design thinking process. To achieve divergent thinking, it is important to have a diverse group of people involved in the process. Multidisciplinary people—architects who have studied psychology, artists with MBAs, or engineers with marketing experience—often demonstrate this quality. They're people with the capacity and the disposition for collaboration across disciplines.\n" +
                "\n" +
                "Interdisciplinary teams typically move into a structured brainstorming process by \"thinking outside the box\". During this process participants' ideas should not be judged and participants should take on generative roles. Participants are encouraged to come up with as many ideas as possible and to explore new alternatives. Good ideas naturally rise to the top, whereas the bad ones drop off early on. Every member of the team needs to possess a depth of skill that allows him or her to make tangible contributions to the outcome, and to be empathic for people and for disciplines beyond their own. It tends to be expressed as openness, curiosity, optimism, a tendency toward learning through doing, and experimentation. Convergent thinking, on the other hand, allows for zooming and focusing on the different proposals to select the best choice, which permits continuation of the design thinking process to achieve the final goals. After collecting lots of ideas, a team goes through a process of synthesis in which it has to translate ideas into insights that can lead to solutions or opportunities for change. This approach helps multiply options to create choices and different insights about human behavior and define in which direction the process should go on. These might be either visions of new product offerings, or choices among various ways of creating interactive experience.");

        textviewLearningLesson3Text5.setText("The third space of the design thinking process is implementation, when the best ideas generated during ideation are turned into something concrete. At the core of the implementation process is prototyping: turning ideas into actual products and services that are then tested, iterated, and refined. A prototype helps to gather feedback and improve the idea. Prototypes speed up the process of innovation because they allow one to understand strengths and weaknesses of new solutions. Prototyping is particularly important for products and services destined for the developing world, where the lack of infrastructure, retail chains, communication networks, literacy, and other essential pieces of the system often make it difficult to design new products and services. Prototyping, testing, \"failing many times but quickly and cheaply in order to succeed\" are different existing methods to test solutions, but the earlier users can give feedback, the lower the costs for the organizations and the higher the level of adaptation of the solution to customer needs.");



    }

    @Override
    public void onBackPressed() {
        Intent resultIntent = new Intent(LearningLesson3.this, LearningActivity.class);
        resultIntent.putExtra(TRANSFER_LEARNED, 1);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}

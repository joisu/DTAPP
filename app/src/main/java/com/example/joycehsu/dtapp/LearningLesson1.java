package com.example.joycehsu.dtapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class LearningLesson1 extends AppCompatActivity {

    public static final String TRANSFER_LEARNED = "transferlearned";
    private TextView textviewLearningLesson1Text1;
    private TextView textviewLearningLesson1Text2;
    private TextView textviewLearningLesson1Text3;
    private TextView textviewLearningLesson1Text4;
    private Button buttonSeeMore;
    private Button buttonSeeEvenMore;
    private Button buttonLearned;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_lesson1);

        textviewLearningLesson1Text1 = (TextView) findViewById(R.id.textview_learninglesson1text1);
        textviewLearningLesson1Text2 = (TextView) findViewById(R.id.textview_learninglesson1text2);
        textviewLearningLesson1Text3 = (TextView) findViewById(R.id.textview_learninglesson1text3);
        textviewLearningLesson1Text4 = (TextView) findViewById(R.id.textview_learninglesson1text4);
        buttonSeeMore = (Button) findViewById(R.id.button_seemore);
        buttonSeeEvenMore = (Button) findViewById(R.id.button_seeevenmore);
        buttonLearned = (Button) findViewById(R.id.button_learned);

        buttonSeeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSeeMore.setVisibility(View.INVISIBLE);
                textviewLearningLesson1Text3.setVisibility(View.VISIBLE);
                buttonSeeEvenMore.setVisibility(View.VISIBLE);

            }
        });

        buttonSeeEvenMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSeeEvenMore.setVisibility(View.INVISIBLE);
                textviewLearningLesson1Text4.setVisibility(View.VISIBLE);
                buttonLearned.setVisibility(View.VISIBLE);

            }
        });

        buttonLearned.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent(LearningLesson1.this, LearningActivity.class);
                resultIntent.putExtra(TRANSFER_LEARNED, 2);
                setResult(RESULT_OK, resultIntent);
                finish();

            }
        });



        textviewLearningLesson1Text1.setText("Introduction to Design Thinking");

        textviewLearningLesson1Text2.setText("Design thinking is a form of solution-focused thinking with the intent of producing a constructive future result.\n" +
                "\n" +
                "Design thinking identifies and investigates both known and ambiguous aspects of the current situation in an effort to discover parameters and alternative solution sets which may lead to one or more satisfactory goals. Because design thinking is iterative, intermediate \"solutions\" are potential starting points of alternative paths, allowing for redefinition of the initial problem, in a process of co-evolution of problem and solution.\n" +
                "\n" +
                "In 1979 Bryan Lawson published results from an empirical study to investigate the different problem-solving approaches of designers and scientists. He took two groups of students – final year students in architecture and post-graduate science students – and asked them to create one-layer structures from a set of coloured blocks. The perimeter of the structure had to optimize either the red or the blue colour; however, there were unspecified rules governing the placement and relationship of some of the blocks. Lawson found that:");

        textviewLearningLesson1Text3.setText("The scientists adopted a technique of trying out a series of designs which used as many different blocks and combinations of blocks as possible as quickly as possible. Thus they tried to maximise the information available to them about the allowed combinations. If they could discover the rule governing which combinations of blocks were allowed they could then search for an arrangement which would optimise the required colour around the layout. [problem-focused] By contrast, the architects selected their blocks in order to achieve the appropriately coloured perimeter. If this proved not to be an acceptable combination, then the next most favourably coloured block combination would be substituted and so on until an acceptable solution was discovered. [solution-focused]\n" +
                "\n" +
                "— Bryan Lawson, How Designers Think");

        textviewLearningLesson1Text4.setText("Unlike analytical thinking, design thinking includes \"building up\" ideas, with few, or no, limits on breadth during a \"brainstorming\" phase. This helps reduce fear of failure in the participant(s) and encourages input and participation from a wide variety of sources in the ideation phases. The phrase \"thinking outside the box\" has been coined to describe one goal of the brainstorming phase and is encouraged, since this can aid in the discovery of hidden elements and ambiguities in the situation and discovering potentially faulty assumptions.\n" +
                "\n" +
                "One version of the design thinking process has seven stages: define, research, ideate, prototype, choose, implement, and learn. Within these seven steps, problems can be framed, the right questions can be asked, more ideas can be created, and the best answers can be chosen. The steps aren't linear; they can occur simultaneously and repeat. A simpler expression of the process is Robert McKim's phrase \"Express–Test–Cycle\". An alternative five-phase description of the process is described by Christoph Meinel and Larry Leifer: (re)defining the problem, needfinding and benchmarking, ideating, building, testing.\n" +
                "\n" +
                "The path through these process steps is not strictly circular. Meinel and Leifer state: \"While the stages are simple enough, the adaptive expertise required to choose the right inflection points and appropriate next stage is a high order intellectual activity that requires practice and is learnable.\"");



    }

    @Override
    public void onBackPressed() {
        Intent resultIntent = new Intent(LearningLesson1.this, LearningActivity.class);
        resultIntent.putExtra(TRANSFER_LEARNED, 1);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}

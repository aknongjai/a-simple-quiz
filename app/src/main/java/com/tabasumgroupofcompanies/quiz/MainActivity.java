package com.tabasumgroupofcompanies.quiz;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button trueButton;
    private Button falseButton;
    private TextView toDisplayQues;
    private int questionNumber=0;
    private boolean userSelection;
    private ProgressBar isProgressBar;
    private TextView isScoreText;
    private int isScore=0;
    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13,true)
    };
    private final int progBarIncrement=(int)Math.ceil(100.0/mQuestionBank.length);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trueButton=findViewById(R.id.true_button);
        falseButton=findViewById(R.id.false_button);
        toDisplayQues=findViewById(R.id.question_text_view);
        toDisplayQues.setText(mQuestionBank[questionNumber].getQuestionId());
        isProgressBar=findViewById(R.id.progress_bar);
        isScoreText=findViewById(R.id.score);

        if(savedInstanceState!=null){
            isScore=savedInstanceState.getInt("Score");
            questionNumber=savedInstanceState.getInt("Ques");
            isScoreText.setText("Score "+isScore+"/"+mQuestionBank.length);
        }

        View.OnClickListener myListionerOfTrue=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userSelection=true;
                checkAnswer();
                updateQues();
            }
        };
        View.OnClickListener myListionerOfFalse=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userSelection=false;
                checkAnswer();
                updateQues();

            }
        };

        trueButton.setOnClickListener(myListionerOfTrue);
        falseButton.setOnClickListener(myListionerOfFalse);
    }
    private void updateQues(){
        questionNumber=(questionNumber+1)%mQuestionBank.length;
        if(questionNumber==0){
            AlertDialog.Builder alert=new AlertDialog.Builder(this);
            alert.setTitle("Quiz Over");
            alert.setMessage("You scored "+isScore+" Points!");
            alert.setCancelable(false);
            alert.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(),"closed successfully",Toast.LENGTH_SHORT);
                    finish();
                }
            });
            alert.show();
        }
        toDisplayQues.setText(mQuestionBank[questionNumber].getQuestionId());
        isProgressBar.incrementProgressBy(progBarIncrement);
    }
    private void checkAnswer(){
        boolean correctAnswer=mQuestionBank[questionNumber].isAnswer();
        if(userSelection==correctAnswer){
            isScore=isScore+1;
            Toast.makeText(getApplicationContext(),R.string.correct_toast,Toast.LENGTH_SHORT).show();
            isScoreText.setText("Score "+isScore+"/"+mQuestionBank.length);
        }else {
            Toast.makeText(getApplicationContext(),R.string.incorrect_toast,Toast.LENGTH_SHORT).show();
        }

    }
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("Score",isScore);
        outState.putInt("Ques",questionNumber);
    }

}

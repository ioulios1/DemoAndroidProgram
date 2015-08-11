package com.example.trypowlini.firstapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity  implements View.OnClickListener{


    ImageView ivEikona ;
    TextView tvaAithmosErwtisis ;
    TextView tvaErwtisi ;

    Button btnNext ;
    Button btnPrevious ;
    Button btnOK ;

    ManageQuestions manQuestions ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivEikona = (ImageView) findViewById(R.id.Eikona) ;
        tvaAithmosErwtisis = (TextView) findViewById(R.id.arithmosErwtisis) ;
        tvaErwtisi = (TextView) findViewById(R.id.Erwtisi) ;

        btnNext = (Button) findViewById(R.id.button2) ;
        btnPrevious = (Button) findViewById(R.id.button6) ;
        btnOK = (Button) findViewById(R.id.button7) ;

        btnNext.setOnClickListener (this);
        btnPrevious.setOnClickListener(this);
        btnOK.setOnClickListener(this);


        /// ManageQuestions
        manQuestions = new ManageQuestions(this) ;
        ///

        System.out.println("CurrentType: " + manQuestions.getCurrentType().toString());
        if (manQuestions.getCurrentType() == ManageQuestions.CustomType.EDUCATION)
            System.out.println("Edu!");
        else
            System.out.println("Exam!");

        viewInfo() ;
    }


    @Override
    public void onClick(View view) {

        if (view == btnNext)
        {
            manQuestions.next() ;
        }
        else if (view == btnPrevious)
        {
            manQuestions.previous() ;
        }
        else if (!manQuestions.haveFinishedQuestions() && view == btnOK)
        {
            manQuestions.setUserResponseToCurQuestion(0); // Tyxaia i proti apantisi.
            manQuestions.next() ;
            if (manQuestions.haveFinishedQuestions())
                Toast.makeText(getApplicationContext(),"Finish", Toast.LENGTH_LONG).show();
        }
        viewInfo() ;
    }

    private void viewInfo()
    {
        tvaErwtisi.setText( "" + manQuestions.getCurQuestionText());
        tvaAithmosErwtisis.setText("" + manQuestions.getCurQuestionNumber()) ;
        ivEikona.setImageBitmap(manQuestions.getCurQuestionImage());
        int tmpCounter = 0 ;
        for (String answer : manQuestions.getCurAnswerTexts())
            System.out.println("AnswerText " + (tmpCounter++) + " : " + answer);
        tmpCounter = 0 ;
        for (boolean answer : manQuestions.getCurCorrectAnswers())
            System.out.println("CorrectAnswer " + (tmpCounter++) + " : " + answer);

        System.out.println("Score: " + manQuestions.getScore());
    }
}

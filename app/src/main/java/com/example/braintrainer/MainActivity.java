package com.example.braintrainer;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startbutton;
    ConstraintLayout game;
    ArrayList<Integer> answers=new ArrayList<Integer>();
    Button button0,button1,button2,button3,play;
    TextView resultTextView,points,sumTextView,timer;
    int locationOfCorrectAnswer;
    int score=0;
    int numberOfQuestions=0;
    public void playAgain(View view) {
        score = 0;
        numberOfQuestions = 0;
        timer.setText("30s");
        points.setText("0/0");
        resultTextView.setText("");
        play.setVisibility(View.INVISIBLE);
        button0.setVisibility(View.VISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        sumTextView.setVisibility(View.VISIBLE);
        generateQuestions();
        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {
                play.setVisibility(View.VISIBLE);
                button0.setVisibility(View.INVISIBLE);
                button1.setVisibility(View.INVISIBLE);
                button2.setVisibility(View.INVISIBLE);
                button3.setVisibility(View.INVISIBLE);
                sumTextView.setVisibility(View.INVISIBLE);
                timer.setText("0s");
                resultTextView.setText("Your Score " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
            }
        }.start();
    }
    public void generateQuestions(){
        answers.clear();
        Random rand=new Random();
        int a=rand.nextInt(21);
        int b=rand.nextInt(21);
        int c=rand.nextInt(4);
        int d=0;
        if (c==0){
            sumTextView.setText(Integer.toString(a)+" + "+Integer.toString(b));
            d=a+b;
        }else if(c==1){
            sumTextView.setText(Integer.toString(a)+" * "+Integer.toString(b));
            d=a*b;
        }else if(c==2){
            sumTextView.setText(Integer.toString(a)+" - "+Integer.toString(b));
            d=a-b;
        }
        else {
            sumTextView.setText(Integer.toString(a)+" / "+Integer.toString(b));
            d=a/b;
        }
        locationOfCorrectAnswer=rand.nextInt(4);
        int incorrectAnswer;
        for (int i=0;i<4;i++){
            if(i==locationOfCorrectAnswer){
                answers.add(d);
            }
            else {
                incorrectAnswer=rand.nextInt(400);
                while (incorrectAnswer==d){
                    incorrectAnswer=rand.nextInt(400);
                }
                answers.add(incorrectAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }
    public void chooseAnswer(View view){
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            score++;
            resultTextView.setText("Correct");
        }else{
            resultTextView.setText("Wrong");
        }
        numberOfQuestions++;
        points.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        generateQuestions();
    }
    public void start(View view){
        startbutton.setVisibility(View.INVISIBLE);
        game.setVisibility(ConstraintLayout.VISIBLE);
    }
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startbutton=(Button)findViewById(R.id.button);
        game=(ConstraintLayout)findViewById(R.id.gameon);
        button0=(Button)findViewById(R.id.button2);
        button1=(Button)findViewById(R.id.button3);
        button2=(Button)findViewById(R.id.button4);
        button3=(Button)findViewById(R.id.button5);
        play=(Button)findViewById(R.id.play);
        resultTextView=(TextView)findViewById(R.id.textView2);
        points=(TextView)findViewById(R.id.scoretextView);
        sumTextView=(TextView)findViewById(R.id.sumtextView);
        timer=(TextView)findViewById(R.id.counttextView);
        playAgain(findViewById(R.id.play));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        if (id==R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
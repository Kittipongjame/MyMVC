package com.example.user.mymvc;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
 ImageView imvAnimal;
 RadioGroup radAnswer;
 private String strAnswer;
 private MyAlerDialog objMyAlert;
 private Question objQuestion;
 private MyAlerDialog objMyAlertDialog;
  RadioButton rdb1,rdb2,rdb3,rdb4;
    Button answer;
    int intTime = 1,userChoose,userScore;
    int userChooseArray[],trueAnswer[];
    private MediaPlayer objMediaPlayerButton,objMediaPlayerRadioButton,mp1,mp2,mp3,mp4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialWidget();
        answer=(Button)findViewById(R.id.button);
        userChooseArray=new int[5];
        trueAnswer=new int[5];
        setValueTrueAnswer();
        mp1=MediaPlayer.create(this,R.raw.monkey);
        mp2=MediaPlayer.create(this,R.raw.bird);
        mp3=MediaPlayer.create(this,R.raw.pig);
        mp4=MediaPlayer.create(this,R.raw.dog);
        mp1.start();
        mp1.setLooping(true);
        objQuestion=new Question();
        objQuestion.setOnQuestionChangeListener(new Question.onQuestionChangeListener() {
            @Override
            public void onQuestionChangeListener(Question question) {
                switch (question.getIntQuestion()){
                    case 1:
                        imvAnimal.setImageResource(R.drawable.monkey);
                        radAnswer.clearCheck();
                        break;
                    case 2:
                        imvAnimal.setImageResource(R.drawable.bird);
                        mp1.stop();
                        mp2.start();
                        mp2.setLooping(true);
                        radAnswer.clearCheck();
                        break;
                    case 3:
                        imvAnimal.setImageResource(R.drawable.pig);
                        mp2.stop();
                        mp3.start();
                        mp3.setLooping(true);
                        radAnswer.clearCheck();
                        break;
                    case 4:
                        imvAnimal.setImageResource(R.drawable.dog);
                        mp3.stop();
                        mp4.start();
                        mp4.setLooping(true);
                        radAnswer.clearCheck();
                        break;


                }
                soundButton();
            }
        });

        radAnswer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radioButton:
                        strAnswer = "Pig";
                        userChoose=1;
                        break;
                    case R.id.radioButton1:
                        userChoose=2;
                        strAnswer = "Monkey";
                        break;
                    case R.id.radioButton2:
                        userChoose=3;
                        strAnswer = "bird";
                        break;
                    case R.id.radioButton3:
                        userChoose=4;
                        strAnswer = "Dog";
                        break;
                    default:
                        strAnswer = null;
                        break;

                }
                soundRadioButton();
            }
        });

        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkChooseAnswer();
            }
        });

    }

    private void initialWidget() {
        imvAnimal = (ImageView) findViewById(R.id.imageView);
        radAnswer = (RadioGroup) findViewById(R.id.radioGroup);
        }
private void  checkChooseAnswer() {
    if (strAnswer != null) {
        Log.d("Answer", "IntTime = " + strAnswer);
        //sentValueToQuestion();
        checkScore();
        intTime++;

    } else {
        Log.d("Answer", "Please Choose Something");
        objMyAlertDialog = new MyAlerDialog();
        objMyAlertDialog.NoChooseEveryThing(MainActivity.this);
    }
}

    private void setValueTrueAnswer() {

        trueAnswer[1] = 2;
        trueAnswer[2] = 3;
        trueAnswer[3] = 1;
        trueAnswer[4] = 4;
    }
    private void checkScore(){
        userChooseArray[intTime] = userChoose;
        Log.d("ChooseArray","userChooseArray["+String.valueOf(intTime)+"] = "+String.valueOf(userChoose));
        if (userChooseArray[intTime]==trueAnswer[intTime]){
            userScore++;
        }Log.d("Score","userScore  = "+String.valueOf(userScore));
        sentValueToQuestion();
    }
    private void soundRadioButton() {
        objMediaPlayerRadioButton=MediaPlayer.create(getBaseContext(),R.raw.effect);
        objMediaPlayerRadioButton.start();
    }
    private void soundButton() {
    objMediaPlayerButton=MediaPlayer.create(getBaseContext(),R.raw.effect);
    objMediaPlayerButton.start();
    }
    private void sentValueToQuestion(){

        if(intTime == 4) {
           /* intTime = 0;*/
            Intent showScore = new Intent(MainActivity.this,ShowAnswer.class);
            showScore.putExtra("Score",userScore);
            startActivity(showScore);
            mp4.stop();
            finish();
        }

        objQuestion.setIntQuestion(intTime+1);
    }
    }


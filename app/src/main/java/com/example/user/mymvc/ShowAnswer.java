package com.example.user.mymvc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ShowAnswer extends AppCompatActivity {

    TextView textShowScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_answer);

        textShowScore=(TextView) findViewById(R.id.textView4);
        textShowScore.setText(String.valueOf(getIntent().getExtras().getInt("Score")));

    }
    public void ClickPlayAgain(View view){
        Intent agian=new Intent(ShowAnswer.this,MainActivity.class);
        startActivity(agian);
        finish();
    }
    public void Exit(View view){
        finish();
    }
}

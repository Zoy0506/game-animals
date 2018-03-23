package com.dan.animals;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FailActivity extends AppCompatActivity implements View.OnClickListener{
    MediaPlayer mediaPlayer1 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        mediaPlayer1 = MediaPlayer.create(this, R.raw.cry);

        mediaPlayer1.start();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fail);

        Button start = (Button) findViewById(R.id.FailBut);  //将控件注册到onclick中
        start.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.FailBut)
        {
            Intent intent = new Intent(FailActivity.this, GameActivity.class);
            startActivity(intent);
            mediaPlayer1.pause();
            FailActivity.this.finish();
        }
    }
}

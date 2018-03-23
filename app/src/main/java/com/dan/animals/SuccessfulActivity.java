package com.dan.animals;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;


public class SuccessfulActivity extends AppCompatActivity  implements  View.OnClickListener{
    MediaPlayer mediaPlayer1 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        mediaPlayer1 = MediaPlayer.create(this, R.raw.cheer);

        mediaPlayer1.start();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful);
        ImageView titleflower=(ImageView)findViewById(R.id.titleflower);  //获取控件
        Bitmap bear=getImageFromAssetsFile("flower");       //获取图片
        titleflower.setImageBitmap(bear);                              //将图片加载到控件上
        titleflower.setBackgroundColor(Color.TRANSPARENT);

        Button start=(Button) findViewById(R.id.successbut);
        start.setOnClickListener(this);
    }
    private Bitmap getImageFromAssetsFile(String fileName)
    {
        Bitmap image = null;
        AssetManager am = getResources().getAssets();
        try
        {
            InputStream is = am.open("png/"+fileName+"/256w/"+fileName+"Artboard 1xxxhdpi.png");
            image = BitmapFactory.decodeStream(is);
            is.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return image;

    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.successbut)
        {
            Intent intent = new Intent(SuccessfulActivity.this, GameActivity.class);
            startActivity(intent);
            mediaPlayer1.pause();
            SuccessfulActivity.this.finish();
        }
    }
}

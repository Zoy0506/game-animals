package com.dan.animals;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    MediaPlayer mediaPlayer2 = null;

    private final int SPLASH_DISPLAY_LENGHT = 3000;  //延迟3秒
    private String[] animals = new String[]{
            "bear","bird","cat","elephant","fish","giraffe","honey","hypo","kangaroo","leo","lion","pig","rhino","tiger","wolf"
    };
    private int[] pic_num = new int[]{1,2,3,4,5,6};
    private int intRd=0,right_num=0;    //记录随机数  record random number
    int flag = 0;           //已经生成随机数与否的标志
    int count = 0;         //记录第几个随机数

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        mediaPlayer2 = MediaPlayer.create(this, R.raw.theonestudio);

        mediaPlayer2.start();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Play_game();
    }


    private void Play_game(){

        //****************************************生成图片随机数  picture random*************************************
        Random right = new Random();
        right_num = Math.abs(right.nextInt())%4;           //获取正确的那个
        count = 0;
        while (count < 6) {
            Random random = new Random(System.currentTimeMillis());
            intRd = Math.abs(random.nextInt()) % animals.length;       //生成动物种类数的随机数
            for (int i = 0; i < count; i++)
                if (pic_num[i] == intRd) {
                    flag = 1;
                    break;
                } else {
                    flag = 0;
                }

            if (flag == 0) {
                pic_num[count] = intRd;
                count++;
            }
        }
        //**********************************************随机数生成结束*************************************************


        //获取各种控件id    get button id
        ImageButton Img_1 = (ImageButton) findViewById(R.id.pc1);
        ImageButton Img_2 = (ImageButton) findViewById(R.id.pc2);
        ImageButton Img_3 = (ImageButton) findViewById(R.id.pc3);
        ImageButton Img_4 = (ImageButton) findViewById(R.id.pc4);
        ImageButton Img_5 = (ImageButton) findViewById(R.id.pc5);
        ImageButton Img_6 = (ImageButton) findViewById(R.id.pc6);
        TextView animal_name = (TextView) findViewById(R.id.right);
        ImageView picture=(ImageView) findViewById(R.id.original);

        //设置消息相应
        Img_1.setOnClickListener(this);
        Img_2.setOnClickListener(this);
        Img_3.setOnClickListener(this);
        Img_4.setOnClickListener(this);
        Img_5.setOnClickListener(this);
        Img_6.setOnClickListener(this);

        Bitmap bmp_1 = getImageFromAssetsFile(animals[pic_num[0]]);
        Bitmap bmp_2 = getImageFromAssetsFile(animals[pic_num[1]]);
        Bitmap bmp_3 = getImageFromAssetsFile(animals[pic_num[2]]);
        Bitmap bmp_4 = getImageFromAssetsFile(animals[pic_num[3]]);
        Bitmap bmp_5 = getImageFromAssetsFile(animals[pic_num[4]]);
        Bitmap bmp_6 = getImageFromAssetsFile(animals[pic_num[5]]);
        Bitmap bmp_right = getImageFromAssetsFile(animals[pic_num[right_num]]);

        //设置背景透明   set background transparent
        Img_1.setBackgroundColor(Color.TRANSPARENT);
        Img_2.setBackgroundColor(Color.TRANSPARENT);
        Img_3.setBackgroundColor(Color.TRANSPARENT);
        Img_4.setBackgroundColor(Color.TRANSPARENT);
        Img_5.setBackgroundColor(Color.TRANSPARENT);
        Img_6.setBackgroundColor(Color.TRANSPARENT);
        picture.setBackgroundColor(Color.TRANSPARENT);

        Img_1.setImageBitmap(bmp_1);
        Img_2.setImageBitmap(bmp_2);
        Img_3.setImageBitmap(bmp_3);
        Img_4.setImageBitmap(bmp_4);
        Img_5.setImageBitmap(bmp_5);
        Img_6.setImageBitmap(bmp_6);
        picture.setImageBitmap(bmp_right);

        animal_name.setText("Find the "+animals[pic_num[right_num]]);

        new Handler().postDelayed(new Runnable(){
            public void run() {
                //获取各种控件id    get button id
                ImageButton Img_1 = (ImageButton) findViewById(R.id.pc1);
                ImageButton Img_2 = (ImageButton) findViewById(R.id.pc2);
                ImageButton Img_3 = (ImageButton) findViewById(R.id.pc3);
                ImageButton Img_4 = (ImageButton) findViewById(R.id.pc4);
                ImageButton Img_5 = (ImageButton) findViewById(R.id.pc5);
                ImageButton Img_6 = (ImageButton) findViewById(R.id.pc6);
                TextView animal_name = (TextView) findViewById(R.id.right);
                ImageView picture=(ImageView) findViewById(R.id.original);
                TextView cute=(TextView) findViewById(R.id.cute);

                cute.setVisibility(View.GONE);
                picture.setVisibility(View.GONE);
                animal_name.setVisibility(View.GONE);

                Img_1.setVisibility(View.VISIBLE);
                Img_2.setVisibility(View.VISIBLE);
                Img_3.setVisibility(View.VISIBLE);
                Img_4.setVisibility(View.VISIBLE);
                Img_5.setVisibility(View.VISIBLE);
                Img_6.setVisibility(View.VISIBLE);
            }
        }, SPLASH_DISPLAY_LENGHT);
    }


    /**
     * 从Assets中读取图片
     */
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
        switch(v.getId()) {
            case R.id.pc1:
                if(right_num==0)    //答对
                {
                    Intent intent = new Intent(GameActivity.this, SuccessfulActivity.class);
                    startActivity(intent);
                    mediaPlayer2.pause();
                    GameActivity.this.finish();
                }
                else        //答错
                {
                    Intent intent = new Intent(GameActivity.this, FailActivity.class);
                    startActivity(intent);
                    mediaPlayer2.pause();
                    GameActivity.this.finish();
                }
                break;
            case R.id.pc2:
                if(right_num==1)    //答对
                {
                    Intent intent = new Intent(GameActivity.this, SuccessfulActivity.class);
                    startActivity(intent);
                    mediaPlayer2.pause();
                    GameActivity.this.finish();
                }
                else       //答错
                {
                    Intent intent = new Intent(GameActivity.this, FailActivity.class);
                    startActivity(intent);
                    mediaPlayer2.pause();
                    GameActivity.this.finish();
                }
                break;
            case R.id.pc3:
                if(right_num==2)    //答对
                {
                    Intent intent = new Intent(GameActivity.this, SuccessfulActivity.class);
                    startActivity(intent);
                    mediaPlayer2.pause();
                    GameActivity.this.finish();
                }
                else        //答错
                {
                    Intent intent = new Intent(GameActivity.this, FailActivity.class);
                    startActivity(intent);
                    mediaPlayer2.pause();
                    GameActivity.this.finish();
                }
                break;
            case R.id.pc4:
                if(right_num==3)    //答对
                {
                    Intent intent = new Intent(GameActivity.this, SuccessfulActivity.class);
                    startActivity(intent);
                    mediaPlayer2.pause();
                    GameActivity.this.finish();
                }
                else       //答错
                {
                    Intent intent = new Intent(GameActivity.this, FailActivity.class);
                    startActivity(intent);
                    mediaPlayer2.pause();
                    GameActivity.this.finish();
                }
                break;
            case R.id.pc5:
                if(right_num==4)    //答对
                {
                    Intent intent = new Intent(GameActivity.this, SuccessfulActivity.class);
                    startActivity(intent);
                    mediaPlayer2.pause();
                    GameActivity.this.finish();
                }
                else        //答错
                {
                    Intent intent = new Intent(GameActivity.this, FailActivity.class);
                    startActivity(intent);
                    mediaPlayer2.pause();
                    GameActivity.this.finish();
                }
                break;
            case R.id.pc6:
                if(right_num==5)    //答对
                {
                    Intent intent = new Intent(GameActivity.this, SuccessfulActivity.class);
                    startActivity(intent);
                    mediaPlayer2.pause();
                    GameActivity.this.finish();
                }
                else       //答错
                {
                    Intent intent = new Intent(GameActivity.this, FailActivity.class);
                    startActivity(intent);
                    mediaPlayer2.pause();
                    GameActivity.this.finish();
                }
                break;
        }

    }
}

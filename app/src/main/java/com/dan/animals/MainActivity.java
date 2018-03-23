package com.dan.animals;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Typeface tp1 = Typeface.createFromAsset(getAssets(),"Fonts/ARBERKLEY.ttf");
        TextView biaoti=(TextView)findViewById(R.id.BIAOTI);
        biaoti.setTypeface(tp1);

        ImageView tiliebear=(ImageView)findViewById(R.id.titlebear);  //获取控件
        Bitmap bear=getImageFromAssetsFile("bear");       //获取图片
        tiliebear.setImageBitmap(bear);                              //将图片加载到控件上
        tiliebear.setBackgroundColor(Color.TRANSPARENT);

        Button start=(Button) findViewById(R.id.bt1);
        start.setOnClickListener(this);
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
        switch(v.getId())
        {
            case R.id.bt1:
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(intent);
                MainActivity.this.finish();
                break;
        }
    }
}

package com.example.hyun.drawnav;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/*이미지 뷰 클릭시 나오는 커스텀 다이얼로그*/
public class Dialog extends AppCompatActivity {

    //다이알로그
    AlertDialog dlg;
    View dialog;
    TextView shopname;
    TextView xbtn;
    TextView foodname;
    TextView price;
    Button hash1,hash2,hash3;
    TextView firstscore, secondscore, thirdscore;
    RatingBar firstStar,secondStar, thirdStar;
    private static ViewPager mPager;

    String num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);

        //다이알로그 정의
        dialog = View.inflate(getApplicationContext(),R.layout.menu_dialog,null);
        dlg = new AlertDialog.Builder(Dialog.this).setView(dialog).create();

        dlg.show();

        xbtn = (TextView) dialog.findViewById(R.id.x);
        xbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlg.dismiss();
                finish();
            }
        });

        change();

    }//onCreate

    public void change()
    {
        //가게이름
        shopname = (TextView)dialog.findViewById(R.id.shopname);
        shopname.setText("말랑둘기의 카페");
        //음식이름
        foodname = (TextView)dialog.findViewById(R.id.foodname);
        foodname.setText("말랑둘기 스페셜");
        //가격
        price = (TextView)dialog.findViewById(R.id.price);
        price.setText(Integer.toString(2500));
        //해쉬태그
        hash1 = (Button)dialog.findViewById(R.id.hash1);
        hash1.setText("달콤함");
        hash2 = (Button)dialog.findViewById(R.id.hash2);
        hash2.setText("고소함");
        hash3 = (Button)dialog.findViewById(R.id.hash3);
        //별점
        firstStar = (RatingBar)dialog.findViewById(R.id.ratingBar);
        firstStar.setRating((float) 3.5);
        secondStar = (RatingBar)dialog.findViewById(R.id.ratingBar2);
        secondStar.setRating((float)4.3);
        thirdStar = (RatingBar)dialog.findViewById(R.id.ratingBar3);
        thirdStar.setRating((float)2.5);
        //평점
        firstscore = (TextView)dialog.findViewById(R.id.score);
        firstscore.setText(Float.toString((float) 3.5));
        secondscore = (TextView)dialog.findViewById(R.id.score2);
        secondscore.setText(Float.toString((float)4.3));
        thirdscore = (TextView)dialog.findViewById(R.id.score3);
        thirdscore.setText(Float.toString((float)2.5));
    }


}

package com.example.user.assignment1;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.RunnableFuture;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class DevelopActivity extends AppCompatActivity {
    int count;
    TextView tv2,tv1;
    TimerTask MyTimerCallBack;

    Button btn;
    final int[] time = {5};
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_develop);


        //이름가져오기
        Intent inIntent = getIntent();
        name = inIntent.getStringExtra("Dev1");



        btn = (Button)findViewById(R.id.button);
        tv1 = (TextView)findViewById(R.id.touchCount);

        tv1.setText(name+"(은)는 개발을 하기 시작했다.\n시간 안에 버튼을 많이 클릭하세요");
        exercise();
    }//end onCreate
    public void exercise(){
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                //버튼 누르면 count 증가
                count++;
                tv1.setText(Integer.toString(count));

                //이미지
                ImageView act1 = (ImageView)findViewById(R.id.act1);
                ImageView act2 = (ImageView)findViewById(R.id.act2);
                if(count%2==1)
                {
                    act1.setVisibility(View.VISIBLE);
                    act2.setVisibility(View.INVISIBLE);
                }
                else{
                    act2.setVisibility(View.VISIBLE);
                    act1.setVisibility(View.INVISIBLE);
                }
            }
        });


        tv2 = (TextView)findViewById(R.id.timer);
        //tv2.setText(Integer.toString(count));
        //타이머
        final Timer timer = new Timer();
        MyTimerCallBack = new TimerTask() {
            @Override
            public void run() {
                final Runnable timerAction = new Runnable() {
                    @Override
                    public void run() {
                        time[0] = UpdateText(time[0]);
                        if(time[0]==-1){
                            timer.cancel();
                            End();
                        }
                    }
                };
                tv2.post(timerAction);
            }
        };
        timer.scheduleAtFixedRate(MyTimerCallBack,1000,1*1000);
        //타이머 끝*/

    }
    private int UpdateText(int i){
        tv2.setText(Integer.toString(i));
        i--;
        return i;
    }
    private void End(){
        btn.setVisibility(View.INVISIBLE);
        tv2.setVisibility(View.INVISIBLE);
        tv1.setText("버튼 클릭 횟수 "+Integer.toString(count));
        tv1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(count<20) {//40
                    tv1.setText(name+"(은)는 컴파일을 했으나 오류가 5개 발견되었습니다" +
                            "\n개발에 실패하였습니다.");
                    tv1.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            tv1.setText("체력 -20   지능 +20\n" +
                                    "창의력 +10   스트레스 +30");
                            tv1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent outIntent = new Intent(getApplicationContext(),SecondActivity.class);
                                    outIntent.putExtra("HP",-20);
                                    outIntent.putExtra("INTEL",20);
                                    outIntent.putExtra("CREATIVITY",10);
                                    outIntent.putExtra("STRESS",30);
                                    setResult(RESULT_OK,outIntent);
                                    finish();
                                }
                            });
                        }
                    });
                }
                else
                {
                    tv1.setText(name+"(은)는 개발에 성공하였습니다.");
                    tv1.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            tv1.setText("체력 -20   지능 +40\n창의력 +20   스트레스 +20");
                            tv1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent outIntent = new Intent(getApplicationContext(),SecondActivity.class);
                                    outIntent.putExtra("HP",-20);
                                    outIntent.putExtra("INTEL",40);
                                    outIntent.putExtra("CREATIVITY",20);
                                    outIntent.putExtra("STRESS",20);
                                    setResult(RESULT_OK,outIntent);
                                    finish();
                                }
                            });
                        }
                });
            }
        }
    });
    }
}//end Class


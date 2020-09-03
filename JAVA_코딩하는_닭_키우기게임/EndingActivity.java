package com.example.user.assignment1;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class EndingActivity extends AppCompatActivity implements Runnable {
    String name;
    ImageView a1;
    Button btn;
    TextView tv1;
    boolean abc = true;
    int i;
    Thread myThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ending);

        //이름가져오기
        Intent inIntent = getIntent();
        name = inIntent.getStringExtra("Ending2");


        a1=(ImageView)findViewById(R.id.rich1);
        btn=(Button)findViewById(R.id.finish);
        tv1=(TextView)findViewById(R.id.freeendingmsg);

        myThread = new Thread(this);
        myThread.start();

        tv1.setText(name+"(은)는 열심히 일을 한 결과" +
                "소프트웨어 개발에 성공하였습니다.");
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv1.setText(name+"가 만든 소프트웨어는 큰 인기를 얻고\n" +
                        name+"(은)는 억만장자가 되었습니다.");
                tv1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tv1.setText(name+"(은)는 회사를 그만두고 창업을 시작하여\n" +
                                "사장이 되었습니다..\n" +
                                "드디어 자유를 얻었습니다.");
                        tv1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                tv1.setText("Ending case 억만장자");
                                tv1.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        tv1.setText("게임을 종료합니다.");
                                        abc=false;
                                        tv1.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                                finish();
                                                startActivityForResult(intent,0);
                                            }
                                        });
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });


    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //super.handleMessage(msg);
            update();
        }
    };

    public void run(){
        while(abc)
        {
            try{
                handler.sendMessage(handler.obtainMessage());
                Thread.sleep(500);
            }catch (InterruptedException e){}
        }
    }

    public void update(){
        int mod = i%3;
        switch(mod){
            case 0:
                i++;
                a1.setImageResource(R.drawable.rich1);
                break;
            case 1:
                i++;
                a1.setImageResource(R.drawable.rich2);
                break;
            case 2:
                i++;
                a1.setImageResource(R.drawable.rich3);
                break;
        }
    }
}

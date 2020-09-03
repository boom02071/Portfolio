package com.example.user.assignment1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class EatActivity extends AppCompatActivity implements Runnable {
    String name;
    TextView tv2, tv1;
    Button btn;
    ImageView a1;
    int my,random;
    int i;
    boolean abc=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eat);

        //이름가져오기
        Intent inIntent = getIntent();
        name = inIntent.getStringExtra("Eat1");

        a1=(ImageView)findViewById(R.id.act1);
        btn=(Button)findViewById(R.id.button);
        tv1=(TextView)findViewById(R.id.random);
        tv2=(TextView)findViewById(R.id.system2);


        tv2.setText(name+"(은)는 배가 고픕니다.");
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn.setVisibility(View.VISIBLE);
                tv2.setText("맛있는 식사를 위해서는 가위바위보에 이겨야 합니다.");
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String[] selected= new String[]{"가위","바위","보"};
                        AlertDialog.Builder dlg = new AlertDialog.Builder(EatActivity.this);
                        dlg.setTitle("무엇을 선택하겠습니까?");
                        dlg.setItems(selected,
                                new DialogInterface.OnClickListener(){
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        my=which;
                                        random=(int)(Math.random()*3);
                                        tv2.setText("나의 선택은 : "+selected[which]);
                                        tv1.setText(selected[random]);
                                        tv1.setVisibility(View.VISIBLE);
                                        btn.setVisibility(View.INVISIBLE);
                                        tv2.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                result();
                                            }
                                        });
                                    }
                                });
                        dlg.setPositiveButton("결정",null);
                        dlg.show();
                    }
                });
            }
        });
    }
    public void result() {
        tv1.setVisibility(View.INVISIBLE);
        if((my==1&&random==0)||(my==0&&random==2)||(my==2&&random==1)) {
            tv2.setText("당신의 승리입니다.");

            final Thread myThread = new Thread(this);
            myThread.start();

            tv2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tv2.setText(name+"(은)는 맛있게 빵을 먹습니다.");
                    tv2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv2.setText("체력 +10   지능 +20\n" +
                                    "창의력 +20   스트레스 -40");
                            abc=false;
                            tv2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent outIntent = new Intent(getApplicationContext(),SecondActivity.class);
                                    outIntent.putExtra("HP",10);
                                    outIntent.putExtra("INTEL",20);
                                    outIntent.putExtra("CREATIVITY",20);
                                    outIntent.putExtra("STRESS",-40);
                                    setResult(RESULT_OK,outIntent);
                                    finish();
                                }
                            });
                        }
                    });
                }
            });
        }
        else
        {
            tv2.setText("당신의 패배입니다.");
            tv2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    a1.setImageResource(R.drawable.eat4);
                    tv2.setText(name+"(은)는 밥을 못먹어\n당신을 원망하고 있습니다.");
                    tv2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv2.setText("체력 -20   지능 -30\n" +
                                    "창의력 -20   스트레스 +20");
                            tv2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent outIntent = new Intent(getApplicationContext(),SecondActivity.class);
                                    outIntent.putExtra("HP",-20);
                                    outIntent.putExtra("INTEL",-0);
                                    outIntent.putExtra("CREATIVITY",-10);
                                    outIntent.putExtra("STRESS",20);
                                    setResult(RESULT_OK,outIntent);
                                    finish();
                                }
                            });
                        }
                    });
                }
            });
        }
    }//end result function
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
                Thread.sleep(100);
            }catch (InterruptedException e){}

        }
    }

    public void update(){
        int mod = i%3;
        switch(mod){
            case 0:
                i++;
                a1.setImageResource(R.drawable.eat1);
                break;
            case 1:
                i++;
                a1.setImageResource(R.drawable.eat2);
                break;
            case 2:
                i++;
                a1.setImageResource(R.drawable.eat3);
                break;
        }
    }
}

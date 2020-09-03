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

public class RestActivity extends AppCompatActivity implements Runnable{
    TextView tv2, tv1;
    Button btn;
    ImageView a1,a2,a3,a4;
    String name;
    int randnum1,randnum2;
    int myrandom;
    int i;
    Thread myThread;
    boolean abc=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest);

        //이름가져오기
        Intent inIntent = getIntent();
        name = inIntent.getStringExtra("Rest1");

        btn = (Button)findViewById(R.id.random);
        tv1 = (TextView)findViewById(R.id.randomnum);
        tv2 = (TextView)findViewById(R.id.sysmsg);
        a1 = (ImageView)findViewById(R.id.act1);
        a2 = (ImageView)findViewById(R.id.act2);
        a3 = (ImageView)findViewById(R.id.act3);
        a4 = (ImageView)findViewById(R.id.act4);

        //램덤 숫자 가져오기
        randnum1 = (int)(Math.random()*10)+1;
        randnum2 = (int)(Math.random()*100)+10;




        myThread = new Thread(this);
        myThread.start();


        tv2.setText(name+"(은)는 휴식을 취하고 있습니다.\n"+
                "버튼을 눌러 랜덤한 숫자를 얻으세요");
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv1.setVisibility(View.VISIBLE);
                tv1.setText("랜덤숫자 범위"+Integer.toString(randnum1)
                        +" ~ "+Integer.toString(randnum2));
                tv2.setText("숫자가 맞지 않으면 휴식이 실패됩니다.\n");
                btn.setVisibility(View.VISIBLE);

                //버튼 클릭
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myrandom = (int)(Math.random()*100)+1;
                        btn.setVisibility(View.INVISIBLE);
                        tv2.setText("당신의 랜덤 숫자 : "+Integer.toString(myrandom));

                        //다음 메세지
                        tv2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(myrandom>=randnum1&&myrandom<=randnum2){
                                    tv2.setText(name+"(은)는 좋은 꿈을 꿉니다."+
                                            "\n잠을 잘잔"+name+"(은)는 기분이 좋습니다.");
                                    tv2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            tv2.setText(name+"(은)는 좋은 아이디어가 " +
                                                    "떠오르는 것 같습니다.\n");
                                            tv2.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    tv2.setText("체력 +20   지능 -10\n" +
                                                            "창의력 +40   스트레스 -20");
                                                    abc=false;
                                                    tv2.setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View v) {
                                                            Intent outIntent = new Intent(getApplicationContext(),SecondActivity.class);
                                                            outIntent.putExtra("HP",20);
                                                            outIntent.putExtra("INTEL",-10);
                                                            outIntent.putExtra("CREATIVITY",40);
                                                            outIntent.putExtra("STRESS",-20);
                                                            setResult(RESULT_OK,outIntent);
                                                            finish();
                                                        }
                                                    });
                                                }
                                            });
                                        }
                                    });
                                }//end if
                                else
                                {
                                    a1.setVisibility(View.INVISIBLE);
                                    a4.setVisibility(View.VISIBLE);
                                    tv2.setText(name+"(은)는 조류독감에 걸렸습니다.");
                                    tv2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            tv2.setText(name+"(은)는 죽어가는 느낌입니다.\n"+
                                                    "멀리서 구급차 소리가 들립니다.");
                                            tv2.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    tv1.setVisibility(View.INVISIBLE);
                                                    tv2.setText("체력 -30   지능 -10\n" +
                                                            "창의력 -20   스트레스 +20");
                                                    abc=false;
                                                    tv2.setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View v) {
                                                            Intent outIntent = new Intent(getApplicationContext(),SecondActivity.class);
                                                            outIntent.putExtra("HP",-30);
                                                            outIntent.putExtra("INTEL",-10);
                                                            outIntent.putExtra("CREATIVITY",-20);
                                                            outIntent.putExtra("STRESS",20);
                                                            setResult(RESULT_OK,outIntent);
                                                            finish();
                                                        }
                                                    });
                                                }
                                            });
                                        }
                                    });
                                }//end else
                            }
                        });

                    }//end click
                });
            }//end msg click
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
           finally {
               System.out.print("dead");
           }

       }
   }

    public void update(){
        int mod = i%3;
        switch(mod){
            case 0:
                i++;
                a1.setImageResource(R.drawable.zzz1);
                break;
            case 1:
                i++;
                a1.setImageResource(R.drawable.zzz2);
                break;
            case 2:
                i++;
                a1.setImageResource(R.drawable.zzz3);
                break;
        }
    }
}

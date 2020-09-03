package com.example.user.assignment1;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;


public class ExerciseActivity extends AppCompatActivity implements SensorEventListener{
    String name;
    TextView tv2, tv1;
    Button btn;
    ImageView a1;

    int i;
    long lastTime;
    float x,y,z,speed,lastX,lastY,lastZ;

    private static final int SHAKE_THRESHOLD = 800;
    private static final int DATA_X = SensorManager.DATA_X;
    private static final int DATA_Y = SensorManager.DATA_Y;
    private static final int DATA_Z = SensorManager.DATA_Z;

    //센서
    private SensorManager mSensorManager;
    private Sensor mAccelometer;

    //타이머
    TimerTask MyTimerCallBack;
    final int[] time = {5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        //이름가져오기
        Intent inIntent = getIntent();
        name = inIntent.getStringExtra("Exercise1");


        a1=(ImageView)findViewById(R.id.act1);
        btn=(Button)findViewById(R.id.button);
        tv1=(TextView)findViewById(R.id.timer);
        tv2=(TextView)findViewById(R.id.system2);


        tv2.setText(name+"(은)는 운동을 합니다."+
                "\n핸드폰을 위아래로 흔들어 주세요");
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


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
                tv1.post(timerAction);
            }
        };
        timer.scheduleAtFixedRate(MyTimerCallBack,1000,1*1000);
        //타이머 끝*/

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mAccelometer!=null)
          mSensorManager.registerListener(this,mAccelometer,SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mSensorManager!=null) mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            long currentTime = System.currentTimeMillis();
            long gabOfTime = (currentTime - lastTime);
            if(gabOfTime > 100){
                lastTime = currentTime;
                x = event.values[SensorManager.DATA_X];
                y = event.values[SensorManager.DATA_Y];
                z = event.values[SensorManager.DATA_Z];

                speed = Math.abs(x+y+z-lastX-lastY-lastZ)/gabOfTime * 10000;

                if(speed>SHAKE_THRESHOLD){
                    i++;
                    tv2.setText(Integer.toString(i));
                    int mod = i%3;
                    switch (mod)
                    {
                        case 0:
                            a1.setImageResource(R.drawable.ex1);
                            break;
                        case 1:
                            a1.setImageResource(R.drawable.ex2);
                            break;
                        case 2:
                            a1.setImageResource(R.drawable.ex3);
                            break;
                    }
                }

                lastX = event.values[DATA_X];
                lastY = event.values[DATA_Y];
                lastZ = event.values[DATA_Z];

            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    private int UpdateText(int i){
        tv1.setText(Integer.toString(i));
        i--;
        return i;
    }
    private void End(){
        if(mSensorManager!=null) mSensorManager.unregisterListener(this);
        tv1.setVisibility(View.INVISIBLE);
        tv2.setText("총 Count 수 : "+Integer.toString(i));

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i>20){//40
                    tv2.setText("운동에 성공하셨습니다.");
                    tv2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            a1.setImageResource(R.drawable.smile2);
                            tv2.setText(name+"은(는) 지나가던 " +
                                    "\n오징어(당신)에게 인사를 합니다.");
                            tv2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    tv2.setText("오징어(당신)은 발끈합니다.");
                                    tv2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            tv2.setText("체력 +40   지능 -10\n" +
                                                    "창의력 -10   스트레스 -20");
                                            tv2.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    Intent outIntent = new Intent(getApplicationContext(),SecondActivity.class);
                                                    outIntent.putExtra("HP",40);
                                                    outIntent.putExtra("INTEL",-10);
                                                    outIntent.putExtra("CREATIVITY",-10);
                                                    outIntent.putExtra("STRESS",-20);
                                                    setResult(RESULT_OK,outIntent);
                                                    finish();
                                                }
                                            });
                                        }
                                    });
                                }
                            });
                        }
                    });
                }
                else{
                    a1.setImageResource(R.drawable.ex);
                    tv2.setText("운동에 실패하셨습니다.");
                    tv2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv2.setText("달리던 "+name+"(은)는 \n체력 부실로 쓰러집니다.");
                            tv2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    tv2.setText("멀리서 구급차의 소리가 들립니다.");
                                    tv2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            tv2.setText("체력 +20   지능 -10\n" +
                                                    "창의력 -30   스트레스 -20");
                                            tv2.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    Intent outIntent = new Intent(getApplicationContext(),SecondActivity.class);
                                                    outIntent.putExtra("HP",20);
                                                    outIntent.putExtra("INTEL",-10);
                                                    outIntent.putExtra("CREATIVITY",-30);
                                                    outIntent.putExtra("STRESS",-20);
                                                    setResult(RESULT_OK,outIntent);
                                                    finish();
                                                }
                                            });
                                        }
                                    });
                                }
                            });
                        }
                    });


                }
            }
        });
    }
}

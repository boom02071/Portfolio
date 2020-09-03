package com.example.user.assignment1;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    int maxage=5;
    int age=0;
    int hp=30;
    int intell=30;
    int creativity=30;
    int stress=30;
    String name;
    TextView tv2,tvhp,tvintell,tvcreat,tvstress;

    ImageView develop,rest,exercise,eat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //종료버튼
        Button btnReturn = (Button) findViewById(R.id.button3);
        btnReturn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
            }
        });


        tvhp = (TextView)findViewById(R.id.hp);
        tvintell = (TextView)findViewById(R.id.intelligence);
        tvcreat = (TextView)findViewById(R.id.creativity);
        tvstress = (TextView)findViewById(R.id.stress);
        tvset();


        //개발
        develop = (ImageView)findViewById(R.id.developbtn);
        develop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                age++;
                tv2.setText(Integer.toString(age)+"/"+maxage);

                Intent intent = new Intent(getApplicationContext(),DevelopActivity.class);
                intent.putExtra("Dev1",name);
                startActivityForResult(intent,0);
            }
        });

        //휴식
        rest = (ImageView)findViewById(R.id.restbtn);
        rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                age++;

                tv2.setText(Integer.toString(age)+"/"+maxage);
                Intent intent = new Intent(getApplicationContext(),RestActivity.class);
                intent.putExtra("Rest1",name);
                startActivityForResult(intent,0);
            }
        });


        //운동
        exercise = (ImageView)findViewById(R.id.execisebtn);
        exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                age++;

                tv2.setText(Integer.toString(age)+"/"+maxage);
                Intent intent = new Intent(getApplicationContext(),ExerciseActivity.class);
                intent.putExtra("Exercise1",name);
                startActivityForResult(intent,0);
            }
        });



        //식사
        eat = (ImageView)findViewById(R.id.eating);
        eat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                age++;

                tv2.setText(Integer.toString(age)+"/"+maxage);
                Intent intent = new Intent(getApplicationContext(),EatActivity.class);
                intent.putExtra("Eat1",name);
                startActivityForResult(intent,0);
            }
        });




        //아이디랑 나이
        Intent inIntent = getIntent();
        TextView tv1 = (TextView)findViewById(R.id.id2);
        name = inIntent.getStringExtra("Num1");
        tv1.setText(inIntent.getStringExtra("Num1"));
        tv2 = (TextView)findViewById(R.id.age2);
        tv2.setText(Integer.toString(age)+"/"+maxage);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       // super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            hp+=data.getIntExtra("HP",0);
            intell+=data.getIntExtra("INTEL",0);
            creativity+=data.getIntExtra("CREATIVITY",0);
            stress+=data.getIntExtra("STRESS",0);
            tvset();
            if(age==maxage)endgame();
        }
    }

    public void tvset()
    {
        tvhp.setText("체력 : "+hp);
        tvintell.setText("지능 : "+intell);
        tvcreat.setText("창의력 : "+creativity);
        tvstress.setText("스트레스 : "+stress);
    }
    void endgame(){
        develop.setVisibility(View.INVISIBLE);
        rest.setVisibility(View.INVISIBLE);
        exercise.setVisibility(View.INVISIBLE);
        eat.setVisibility(View.INVISIBLE);

        int result = (hp+stress+intell)/3;
        if(hp>80&&stress<150&&(result>70))richending();
        else if(hp<=80||stress>=150) deadending();
        else freeending();

    }
    void freeending(){
        ImageView ending=(ImageView)findViewById(R.id.ending);
        ending.setVisibility(View.VISIBLE);
        ending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),EndingActivity3.class);
                intent.putExtra("Ending3",name);
                startActivityForResult(intent,0);
                finish();
            }
        });
    }
    void deadending(){
        ImageView ending=(ImageView)findViewById(R.id.ending);
        ending.setVisibility(View.VISIBLE);
        ending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),EndingActivity2.class);
                intent.putExtra("Ending2",name);
                startActivityForResult(intent,0);
                finish();
            }
        });
    }
    void richending(){
        ImageView ending=(ImageView)findViewById(R.id.ending);
        ending.setVisibility(View.VISIBLE);
        ending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),EndingActivity.class);
                intent.putExtra("Ending2",name);
                startActivityForResult(intent,0);
                finish();
            }
        });
    }
}

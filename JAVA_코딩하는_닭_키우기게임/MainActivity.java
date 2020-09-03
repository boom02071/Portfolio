package com.example.user.assignment1;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //스타트 버튼 클릭시
        final EditText InputID;
        Button button1;
        final View[] dialogView = new View[1];

        Button btnStart= (Button) findViewById(R.id.start);
        btnStart.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                dialogView[0] = (View) View.inflate(MainActivity.this,R.layout.id_layout,null);
                final AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("아이디를 입력하세요");
                dlg.setView(dialogView[0]);
                dlg.setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(getApplicationContext(),SecondActivity.class);

                               //연습
                                EditText dlgEdtID = (EditText) dialogView[0].findViewById(R.id.editText);
                                intent.putExtra("Num1",dlgEdtID.getText().toString());
                                startActivityForResult(intent,0);
                                //
                                //startActivity(intent);
                            }
                        });
                dlg.setNegativeButton("취소",null);
                dlg.show();
            }
        });

        //종료버튼
        Button btnFinish= (Button) findViewById(R.id.finish);
        btnFinish.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
            }
        });

    }
}
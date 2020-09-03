
package com.example.hyun.cookiefood;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ToggleButton;

import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button MenuKorean;    //한식
    Button MenuWestern;   //양식
    Button MenuChicken;   //치킨

    // view pager 이미지 슬라이딩 부분
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private ArrayList<ImageModel> imageModelArrayList;

    private int[] myImageList = new int[]{R.drawable.img1, R.drawable.img2,
            R.drawable.img3,R.drawable.img4};

// 말풍선 메뉴
    ImageButton AllMenu;         //전체메뉴
    ImageButton KoreanFood;     //한식
    ImageButton WesternFood;    //양식
    ImageButton ChickenFood;    //치킨
    ImageButton ChinaFood;      //중국집
    ImageButton DesertFood;     //분식/디저트
    ImageButton JapanFood;      //일식

    // 네비게이션 드로우
    private DrawerLayout drawer;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MenuKorean= (Button) findViewById(R.id.MenuKorean);
        MenuWestern=(Button)findViewById(R.id.MenuWestern);
        MenuChicken=(Button)findViewById(R.id.MenuChicken);

        imageModelArrayList = new ArrayList<>();
        imageModelArrayList = populateList();

        init();

        AllMenu=(ImageButton)findViewById(R.id.AllMenu);             //전체메뉴
        KoreanFood=(ImageButton)findViewById(R.id.KoreanFood);      //한식
        WesternFood=(ImageButton)findViewById(R.id.WesternFood);    //양식
        ChickenFood=(ImageButton)findViewById(R.id.ChickenFood);    //치킨
        ChinaFood=(ImageButton)findViewById(R.id.ChinaFood);        //중국집
        DesertFood=(ImageButton)findViewById(R.id.DesertFood);      //분식/디저트
        JapanFood=(ImageButton)findViewById(R.id.JapanFood);        //일식

        AllMenu.setOnClickListener(this);
        KoreanFood.setOnClickListener(this);
        WesternFood.setOnClickListener(this);
        ChickenFood.setOnClickListener(this);
        ChinaFood.setOnClickListener(this);
        DesertFood.setOnClickListener(this);
        JapanFood.setOnClickListener(this);

        //네비게이션 드로우
        drawer=(DrawerLayout)findViewById(R.id.drawer);
        mToggle= new ActionBarDrawerToggle(this,drawer,R.string.open,R.string.close);
        drawer.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(AllMenu)){
            Intent intent = new Intent(MainActivity.this,list.class);
            intent.putExtra("type","전체");
            startActivityForResult(intent,0);

        }
        if(v.equals(KoreanFood)){
            Intent intent = new Intent(MainActivity.this,list.class);
            intent.putExtra("type","한식");
            startActivityForResult(intent,0);

        }
        if(v.equals(WesternFood)){
            Intent intent = new Intent(MainActivity.this,list.class);
            intent.putExtra("type","양식");
            startActivityForResult(intent,0);
        }
        if(v.equals(ChickenFood)){
            Intent intent = new Intent(MainActivity.this,list.class);
            intent.putExtra("type","치킨");
            startActivityForResult(intent,0);

        }
        if(v.equals(ChinaFood)){
            Intent intent = new Intent(MainActivity.this,list.class);
            intent.putExtra("type","중식");
            startActivityForResult(intent,0);

        }
        if(v.equals(DesertFood)){
            Intent intent = new Intent(MainActivity.this,list.class);
            intent.putExtra("type","분식/디저트");
            startActivityForResult(intent,0);

        }
        if(v.equals(JapanFood)){
            Intent intent = new Intent(MainActivity.this,list.class);
            intent.putExtra("type","일식");
            startActivityForResult(intent,0);
        }
    }
    private ArrayList<ImageModel> populateList(){

        ArrayList<ImageModel> list = new ArrayList<>();

        for(int i = 0; i < 4; i++){
            ImageModel imageModel = new ImageModel();
            imageModel.setImage_drawable(myImageList[i]);
            list.add(imageModel);
        }

        return list;
    }
    private void init() {

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new SlidingImage_Adapter(MainActivity.this,imageModelArrayList));

        CirclePageIndicator indicator = (CirclePageIndicator)
                findViewById(R.id.indicator);

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius
        indicator.setRadius(5 * density);

        NUM_PAGES =imageModelArrayList.size();

        // Auto start of viewpager
        final android.os.Handler handler= new android.os.Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);

            }
        }, 3000, 3000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }
            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {
            }
            @Override
            public void onPageScrollStateChanged(int pos) {
            }
        });
    }
}

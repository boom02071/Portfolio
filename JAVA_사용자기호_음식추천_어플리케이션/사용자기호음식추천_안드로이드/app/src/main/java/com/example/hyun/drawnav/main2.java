package com.example.hyun.drawnav;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.viewpagerindicator.CirclePageIndicator;

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
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Hyun on 2017-10-27.
 */

public class main2 extends Fragment implements View.OnClickListener{


    Button MenuKorean;    //한식
    Button MenuWestern;   //양식
    Button MenuChicken;   //치킨
    Button nowon;
    Button mapo;

    // view pager 이미지 슬라이딩 부분
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private ArrayList<ImageModel> imageModelArrayList;

    /*
    private int[] myImageList = new int[]{R.drawable.img1, R.drawable.img2,
            R.drawable.img3,R.drawable.img4};*/



    // 말풍선 메뉴
    ImageButton AllMenu;         //전체메뉴
    ImageButton KoreanFood;     //한식
    ImageButton WesternFood;    //양식
    ImageButton ChickenFood;    //치킨
    ImageButton ChinaFood;      //중국집
    ImageButton DesertFood;     //분식/디저트
    ImageButton JapanFood;      //일식


    //
    String location="노원구";

    //////////////////////////////////////////////////////
    /////////////////////////////////////////////////////
    //php
    private static String TAG = "Dialog";
    private static final String TAG_JSON="codingstory";
    private static final String TAG_NAME = "name";
    private static final String TAG_TAG1 = "tag1";
    private static final String TAG_SCORE1 = "score1";
    private static final String TAG_TAG2 = "tag2";
    private static final String TAG_SCORE2 = "score2";
    private static final String TAG_TAG3 = "tag3";
    private static final String TAG_SCORE3 = "score3";
    private static final String TAG_SHOP_NAME = "shop_name";
    private static final String TAG_PHOTO = "photo";
    private static final String TAG_PRICE = "price";
    String mJsonString;

    ProgressDialog progressDialog;
    ArrayList<MyItem> food_list = new ArrayList<MyItem>();
    ArrayList<DB> db_list = new ArrayList<DB>();
    ArrayList<Bitmap> picture_list = new ArrayList<Bitmap>();

    //사진
    Bitmap bmimg;
    ArrayList<Food> recommend_list = new ArrayList<Food>();


    //hash
    private static String TAG_hash="phptest";
    private static final String Tag_JSON_hash="codingstory";
    private static final String Taste1="taste1", Taste2="taste2", Taste3="taste3";
    String mJsonString_hash;

    //
    String ID;

//////////////////////////////////////////////////////

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("main");
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main2, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MenuKorean= (Button) getView().findViewById(R.id.MenuKorean);
        MenuWestern=(Button)getView().findViewById(R.id.MenuWestern);
        MenuChicken=(Button)getView().findViewById(R.id.MenuChicken);
        nowon=(Button)getView().findViewById(R.id.nowon);
        mapo=(Button)getView().findViewById(R.id.mapo);


        //init();

        AllMenu=(ImageButton)getView().findViewById(R.id.AllMenu);             //전체메뉴
        KoreanFood=(ImageButton)getView().findViewById(R.id.KoreanFood);      //한식
        WesternFood=(ImageButton)getView().findViewById(R.id.WesternFood);    //양식
        ChickenFood=(ImageButton)getView().findViewById(R.id.ChickenFood);    //치킨
        ChinaFood=(ImageButton)getView().findViewById(R.id.ChinaFood);        //중국집
        DesertFood=(ImageButton)getView().findViewById(R.id.DesertFood);      //분식/디저트
        JapanFood=(ImageButton)getView().findViewById(R.id.JapanFood);        //일식




        AllMenu.setOnClickListener(this);
        KoreanFood.setOnClickListener(this);
        WesternFood.setOnClickListener(this);
        ChickenFood.setOnClickListener(this);
        ChinaFood.setOnClickListener(this);
        DesertFood.setOnClickListener(this);
        JapanFood.setOnClickListener(this);
        nowon.setOnClickListener(this);
        mapo.setOnClickListener(this);


        MenuKorean.setOnClickListener(this);
        MenuWestern.setOnClickListener(this);
        MenuChicken.setOnClickListener(this);


        registDB a = new registDB();
        a.execute("hi");

        String id=this.getArguments().getString("userId");
        ID = id;
        GetData task = new GetData();
        task.execute(id/*IDTxt.getText().toString()*/);
    }

    private void init() {

        mPager = (ViewPager) getView().findViewById(R.id.pager);

        SlidingImage_Adapter Sliding = new SlidingImage_Adapter(getActivity(),imageModelArrayList);

        //recommend_list.add(new Food(name,tag1,score1,tag2,score2,tag3,score3,shop_name,price));
        for(int i=0;i<recommend_list.size();i++) {
            Sliding.recommend_list.add(new Food(recommend_list.get(i).name,
                    recommend_list.get(i).tag1, recommend_list.get(i).score1,
                    recommend_list.get(i).tag2, recommend_list.get(i).score2,
                    recommend_list.get(i).tag3, recommend_list.get(i).score3,
                    recommend_list.get(i).shop_name,
                    recommend_list.get(i).price
            ));

            Sliding.picture_list.add(picture_list.get(i));
        }
        mPager.setAdapter(Sliding);
        //mPager.setAdapter(new SlidingImage_Adapter(getActivity(),imageModelArrayList));


        CirclePageIndicator indicator = (CirclePageIndicator)
                getView().findViewById(R.id.indicator);

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

    private class GetData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            progressDialog = ProgressDialog.show(getContext()/*HashActivity.this*/, "please wait", null, true, true);
        }

        @Override
        protected void onPostExecute(String result){
            super.onPostExecute(result);
            progressDialog.dismiss();
            //resultTxt.setText(result);
            Log.d(TAG, "response - "+result);
            if(result ==null){
                //resultTxt.setText(errorString);
            }
            else {
                mJsonString_hash =result;
                showResult_hash();
            }

        }

        @Override
        protected String doInBackground(String... params) {
            String searchID = params[0];

            String serverURL = "http://13.59.52.83/loadTaste.php";
            String postParameters = "id="+searchID;
            try{
                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();

                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG_hash,"response code - "+responseStatusCode);



                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }

                bufferedReader.close();

                return sb.toString().trim();

            } catch (Exception e){
                Log.d(TAG_hash, "search data : error", e);
                errorString = e.toString();
                return null;
            }
        }
    }
    private void showResult_hash(){
        try {
            JSONObject jsonObject = new JSONObject(mJsonString_hash);
            JSONArray jsonArray = jsonObject.getJSONArray(Tag_JSON_hash);

            JSONObject item = jsonArray.getJSONObject(0);

            String taste1 ;
            String taste2 ;
            String taste3 ;

            if(item.getString(Taste1)==null) taste1="0";
            else taste1 = item.getString(Taste1);
            if(item.getString(Taste2)==null) taste2="0";
            else taste2 = item.getString(Taste2);
            if(item.getString(Taste3)==null) taste3="0";
            else taste3 = item.getString(Taste3);

            //resultTxt.setText(taste1+" "+taste2+" "+taste3);
            int t1=Integer.parseInt(taste1), t2=Integer.parseInt(taste2), t3=Integer.parseInt(taste3);

            String Taste[]={"취향설정","달콤한", "매운", "담백한", "고소한", "짠맛", "상큼한"};

            MenuKorean.setText(Taste[t1]);
            MenuChicken.setText(Taste[t2]);
            MenuWestern.setText(Taste[t3]);

        } catch (JSONException e) {
            Log.d(TAG_hash, "showResult_hash : ", e);
        }
    }

    private ArrayList<ImageModel> populateList() {
        ArrayList<ImageModel> list = new ArrayList<>();

        for(int i = 0; i < 4; i++){
            ImageModel imageModel = new ImageModel();
            imageModel.setBitmap_drawable(picture_list.get(i));
            list.add(imageModel);
        }

        return list;
    }
    @Override
    public void onClick(View v) {
        if(v.equals(MenuKorean)||v.equals(MenuWestern)||v.equals(MenuChicken)){
            food_list.clear();
            db_list.clear();
            picture_list.clear();
            recommend_list.clear();
            android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.detach(this).attach(this).commit();
        }
        if(v.equals(AllMenu)){
            Intent intent = new Intent(getActivity(),list.class);
            intent.putExtra("type","전체");
            intent.putExtra("location",location);
            startActivity(intent);

        }
        if(v.equals(KoreanFood)){
            Intent intent = new Intent(getActivity(),list.class);
            intent.putExtra("type","한식");
            intent.putExtra("location",location);
            startActivity(intent);

        }
        if(v.equals(WesternFood)){
            Intent intent = new Intent(getActivity(),list.class);
            intent.putExtra("type","양식");
            intent.putExtra("location",location);
            startActivity(intent);
        }
        if(v.equals(ChickenFood)){
            Intent intent = new Intent(getActivity(),list.class);
            intent.putExtra("type","치킨");
            intent.putExtra("location",location);
            startActivity(intent);

        }
        if(v.equals(ChinaFood)){
            Intent intent = new Intent(getActivity(),list.class);
            intent.putExtra("type","중식");
            intent.putExtra("location",location);
            startActivity(intent);

        }
        if(v.equals(DesertFood)){
            Intent intent = new Intent(getActivity(),list.class);
            intent.putExtra("type","분식/디저트");
            intent.putExtra("location",location);
            startActivity(intent);

        }
        if(v.equals(JapanFood)){
            Intent intent = new Intent(getActivity(),list.class);
            intent.putExtra("type","일식");
            intent.putExtra("location",location);
            startActivity(intent);
        }
        if(v.equals(nowon)){
            location="노원구";
        }
        if(v.equals(mapo)){
            location="마포구";
        }
    }



    //메뉴 리스트 부분
    public class MyItem{
        Bitmap image;
        String text = "";
        int price=0;

        public MyItem(Bitmap image,String text, int price){
            super();
            this.image=image;
            this.text = text;
            this.price = price;
        }
    }

    public class DB{
        String text = "";
        String address="";
        int price=0;

        public DB(String text, String address,int price){
            super();
            this.text = text;
            this.address = address;
            this.price = price;
        }
    }

    public class Food{
        String name;
        String tag1;
        String score1;
        String tag2;
        String score2;
        String tag3;
        String score3;
        String shop_name;
        String price;

        public Food(String name, String tag1, String score1, String tag2, String score2,
                    String tag3, String score3, String shop_name, String price)
        {
            super();
            this.name=name;
            this.tag1=tag1;
            this.score1=score1;
            this.tag2=tag2;
            this.score2=score2;
            this.tag3=tag3;
            this.score3=score3;
            this.shop_name=shop_name;
            this.price=price;
        }
    }


    private class registDB extends AsyncTask<String, Void, String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(getActivity(),"쿠키가 음식 추천 중 o3o" ,null,true,true);
        }

        @Override
        protected String doInBackground(String... strings) {
            String errorString = null;
            String param = "id="+ID;
            try {
                URL url = new URL("http://13.59.52.83/recommend.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(param.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "response code - " + responseStatusCode);


                InputStream inputStream;
                if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                } else {
                    inputStream = httpURLConnection.getErrorStream();
                }

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder sb = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }

                bufferedReader.close();
                return sb.toString().trim();

            }catch (Exception e) {
                Log.d(TAG, "InsertData: Error ", e);
                errorString = e.toString();
                return null;

            }

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mJsonString=s;
            showResult();
        }

        private void showResult(){
            try{
                JSONObject jsonObject = new JSONObject(mJsonString);
                JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);
                back task=new back();

                for(int i=0;i<4;i++){
                    JSONObject item = jsonArray.getJSONObject(i);


                    String name = item.getString(TAG_NAME);
                    String tag1 = item.getString(TAG_TAG1);
                    String score1 = item.getString(TAG_SCORE1);
                    String tag2 = item.getString(TAG_TAG2);
                    String score2 = item.getString(TAG_SCORE2);
                    String tag3 = item.getString(TAG_TAG3);
                    String score3 = item.getString(TAG_SCORE3);
                    String shop_name = item.getString(TAG_SHOP_NAME);
                    String photo = item.getString(TAG_PHOTO);
                    String price = item.getString(TAG_PRICE);


                    HashMap<String,String> hashMap = new HashMap<>();


                    hashMap.put(TAG_NAME,name);
                    hashMap.put(TAG_TAG1,tag1);
                    hashMap.put(TAG_SCORE1,score1);
                    hashMap.put(TAG_TAG2,tag2);
                    hashMap.put(TAG_SCORE2,score2);
                    hashMap.put(TAG_TAG3,tag3);
                    hashMap.put(TAG_SCORE3,score3);
                    hashMap.put(TAG_SHOP_NAME,shop_name);
                    hashMap.put(TAG_PHOTO,photo);
                    hashMap.put(TAG_PRICE,price);


                    String [] tags = new String[]{"","달콤한", "매운", "담백한", "고소한", "짠맛", "상큼한"};

                    recommend_list.add(new Food(name,tags[Integer.parseInt(tag1)],score1,tags[Integer.parseInt(tag2)],score2,tags[Integer.parseInt(tag3)],score3,shop_name,price));
                    db_list.add(new DB(name,photo,Integer.parseInt(price)));

                }

                task.execute("");

            }catch (JSONException e) {
                Log.d(TAG, "showResult : ", e);
            }
        }

    }

    private class back extends AsyncTask<String, Integer,Bitmap> {
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected Bitmap doInBackground(String... urls) {
            try{

                for(int i=0;i<db_list.size();i++) {
                    URL myFileUrl = new URL("http://13.59.52.83" + db_list.get(i).address);
                    HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
                    conn.setDoInput(true);
                    conn.connect();

                    InputStream is = conn.getInputStream();
                    bmimg = BitmapFactory.decodeStream(is);
                    picture_list.add(bmimg);
                }

            }catch(IOException e){
                e.printStackTrace();
            }
            return bmimg;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);

            progressDialog.dismiss();

            for (int i = 0; i < picture_list.size(); i++) {
                food_list.add(new MyItem(picture_list.get(i), db_list.get(i).text, db_list.get(i).price));
            }
            imageModelArrayList = new ArrayList<>();
            imageModelArrayList = populateList();

            init();


        }
    }
}

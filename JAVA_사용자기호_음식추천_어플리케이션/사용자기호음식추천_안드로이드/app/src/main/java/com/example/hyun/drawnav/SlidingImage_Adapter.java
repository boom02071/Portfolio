package com.example.hyun.drawnav;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Hyun on 2017-10-13.
 * 이미지뷰 슬라이딩 페이지
 */

public class SlidingImage_Adapter extends PagerAdapter {


    private ArrayList<ImageModel> imageModelArrayList;
    private LayoutInflater inflater;
    private Context context;

    //다이알로그
    AlertDialog dlg;
    View dialog;
    TextView shopname;
    TextView xbtn;
    TextView foodname;
    TextView price;
    ImageView photo;
    Button hash1,hash2 ,hash3;
    TextView firstscore, secondscore, thirdscore;;
    RatingBar firstStar,secondStar, thirdStar;


    ArrayList<main2.Food> recommend_list = new ArrayList<main2.Food>();
    ArrayList<Bitmap> picture_list = new ArrayList<Bitmap>();
    int pos;


    public SlidingImage_Adapter(Context context, ArrayList<ImageModel> imageModelArrayList) {
        this.context = context;
        this.imageModelArrayList = imageModelArrayList;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return imageModelArrayList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, final int position) {
        final View imageLayout = inflater.inflate(R.layout.slidingimages_layout, view, false);

        assert imageLayout != null;
        final ImageView imageView = (ImageView) imageLayout.findViewById(R.id.image);
        imageView.setImageBitmap(imageModelArrayList.get(position).getImage_drawable());

        //다이알로그 정의
        dialog = View.inflate(context,R.layout.menu_dialog,null);
        dlg = new AlertDialog.Builder(context).setView(dialog).create();

        xbtn = (TextView) dialog.findViewById(R.id.x);
        xbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlg.dismiss();
            }
        });


        imageLayout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(position ==0){
                    pos=0;
                    change();
                    dlg.show();
                }
                else if(position==1)
                {
                    pos=1;
                    change();
                    dlg.show();
                }
                else if(position==2){
                    pos=2;
                    change();
                    dlg.show();
                }
                else
                {
                    pos=3;
                    change();
                    dlg.show();
                }
            }
        });
        view.addView(imageLayout, 0);

        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    public void change()
    {
        //가게이름
        shopname = (TextView)dialog.findViewById(R.id.shopname);
        shopname.setText(recommend_list.get(pos).shop_name);
        //음식이름
        foodname = (TextView)dialog.findViewById(R.id.foodname);
        foodname.setText(recommend_list.get(pos).name);

        //사진
        photo = (ImageView)dialog.findViewById(R.id.photo);
        photo.setImageBitmap(picture_list.get(pos));
        //가격
        price = (TextView)dialog.findViewById(R.id.price);
        price.setText(recommend_list.get(pos).price);
        //해쉬태그
        hash1 = (Button)dialog.findViewById(R.id.hash1);
        hash1.setText(recommend_list.get(pos).tag1);
        hash2 = (Button)dialog.findViewById(R.id.hash2);
        hash2.setText(recommend_list.get(pos).tag2);
        hash3 = (Button)dialog.findViewById(R.id.hash3);
        hash3.setText(recommend_list.get(pos).tag3);

        //별점
        firstStar = (RatingBar)dialog.findViewById(R.id.ratingBar);
        firstStar.setRating((float)(Float.parseFloat(recommend_list.get(pos).score1)));
        secondStar = (RatingBar)dialog.findViewById(R.id.ratingBar2);
        secondStar.setRating((float)(Float.parseFloat(recommend_list.get(pos).score2)));
        thirdStar = (RatingBar)dialog.findViewById(R.id.ratingBar3);
        thirdStar.setRating((float)(Float.parseFloat(recommend_list.get(pos).score3)));
        //평점
        firstscore = (TextView)dialog.findViewById(R.id.score);
        firstscore.setText(Float.toString((float)(Float.parseFloat(recommend_list.get(pos).score1))));
        secondscore = (TextView)dialog.findViewById(R.id.score2);
        secondscore.setText(Float.toString((float)(Float.parseFloat(recommend_list.get(pos).score2))));
        thirdscore = (TextView)dialog.findViewById(R.id.score3);
        thirdscore.setText(Float.toString((float)(Float.parseFloat(recommend_list.get(pos).score3))));



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

}
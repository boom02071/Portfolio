package com.example.hyun.drawnav;

import android.graphics.Bitmap;

/**
 * Created by Hyun on 2017-10-13.
 * 이미지 넣는 페이지
 */


public class ImageModel {

    //private int image_drawable;
    private Bitmap bitmap;

    /*public int getImage_drawable() {
        return image_drawable;
    }

    public void setImage_drawable(int image_drawable) {
        this.image_drawable = image_drawable;
    }*/

    public Bitmap getImage_drawable(){
        return bitmap;
    }
    public void setBitmap_drawable(Bitmap bitmap){
        this.bitmap=bitmap;
    }
}
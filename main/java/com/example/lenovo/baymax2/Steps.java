package com.example.lenovo.baymax2;

import android.graphics.drawable.Drawable;

/**
 * Created by LENOVO on 17/11/2016.
 */

public class Steps {
    public String steps;
    public Drawable pic;
    public String title;
    public Steps(String steps,Drawable pic,String title){
        this.steps=steps;
        this.pic=pic;
        this.title=title;
    }
    public String getSteps(){
        return  steps;
    }
    public String getTitle(){
        return title;
    }
    public Drawable getPic(){
        return pic;
    }

}

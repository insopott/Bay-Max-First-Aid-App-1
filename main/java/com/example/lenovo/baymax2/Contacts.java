package com.example.lenovo.baymax2;

import android.graphics.drawable.Drawable;

/**
 * Created by LENOVO on 16/11/2016.
 */

public class Contacts {
    public String name;
    public String number;
    public Drawable pic;
    public Contacts(String name,String number,Drawable pic){
        this.name=name;
        this.number=number;
        this.pic=pic;
    }
    public String getName(){
        return this.name;
    }
    public String getNumber(){
        return this.number;
    }
    public Drawable getPic(){
        return this.pic;
    }
}

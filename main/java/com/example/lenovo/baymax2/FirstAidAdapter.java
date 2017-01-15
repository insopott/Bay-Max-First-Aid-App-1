package com.example.lenovo.baymax2;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lenovo.baymax2.databinding.FirstaidBinding;

import java.util.ArrayList;

/**
 * Created by LENOVO on 19/11/2016.
 */

public class FirstAidAdapter extends RecyclerView.Adapter<FirstAidViewHolder> {
   static ArrayList<Steps>steps=new ArrayList<Steps>();
    Context c;
    public FirstAidAdapter(Context c, ArrayList<Steps>steps){
        this.c=c;
        this.steps=steps;
    }
    public static String text="";
    //int cpositon=0;
   static private FirstAidViewHolder fvh;
    @Override
    public FirstAidViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FirstaidBinding binding= DataBindingUtil.inflate(LayoutInflater.from(this.c),R.layout.firstaid,parent,false);
        fvh=new FirstAidViewHolder(binding);
        return fvh;
    }

    @Override
    public void onBindViewHolder(FirstAidViewHolder holder, int position) {
            holder.connection(steps.get(position));
       /* if(position>cpositon){
            AnimatorSet ani=new AnimatorSet();
            ObjectAnimator ob=ObjectAnimator.ofFloat(holder.itemView,"translationX",1000,0);
            text=steps.get(position).getSteps();
          // ObjectAnimator.)
            ob.setDuration(1000);
            ani.playTogether(ob);
            ani.start();
           // Toast.makeText(c,"Left",Toast.LENGTH_LONG).show();
        }else{
            AnimatorSet ani=new AnimatorSet();
            ObjectAnimator ob=ObjectAnimator.ofFloat(holder.itemView,"translationX",-1000,0);
            ob.setDuration(1000);
            ani.playTogether(ob);
            ani.start();
            text=steps.get(position).getSteps();
            //Toast.makeText(c,"Right",Toast.LENGTH_LONG).show();
        }*/
        text=steps.get(position).getSteps();

        //cpositon=position;
    }
    public static void addItem(Steps step){
        steps.remove(fvh.getAdapterPosition());


    }

    @Override
    public int getItemCount() {
        return steps.size();
    }
}

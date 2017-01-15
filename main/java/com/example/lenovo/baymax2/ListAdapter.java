package com.example.lenovo.baymax2;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lenovo.baymax2.databinding.View1Binding;

import java.util.ArrayList;

/**
 * Created by LENOVO on 15/11/2016.
 */

public class ListAdapter extends RecyclerView.Adapter<ViewH> {
    public static ArrayList<Name>items;
    public ListAdapter(ArrayList<Name>list){
        super();
        this.items=list;

    }
    @Override
    public ViewH onCreateViewHolder(ViewGroup parent, int viewType) {
        View1Binding binding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.view1,
                parent,false);
        //Toast.makeText(MainActivity.getC(),parent.toString(),Toast.LENGTH_LONG).show();
        return new ViewH(binding);
    }

    @Override
    public void onBindViewHolder(ViewH holder, int position) {
        holder.connection(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

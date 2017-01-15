package com.example.lenovo.baymax2;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.lenovo.baymax2.databinding.FirstaidBinding;

/**
 * Created by LENOVO on 19/11/2016.
 */

public class FirstAidViewHolder extends RecyclerView.ViewHolder {
    private FirstaidBinding binding;
    public FirstAidViewHolder(FirstaidBinding f) {
        super(f.getRoot());
        binding=f;

    }
    public void connection(Steps s){
        binding.setSteps(s);
    }


}

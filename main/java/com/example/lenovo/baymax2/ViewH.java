package com.example.lenovo.baymax2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.lenovo.baymax2.databinding.View1Binding;



/**
 * Created by LENOVO on 15/11/2016.
 */

public class ViewH extends RecyclerView.ViewHolder implements View.OnClickListener{
    private View1Binding binding;
    public ViewH(View1Binding _binding) {
        super(_binding.getRoot());
        _binding.getRoot().setOnClickListener(this);
       // _binding.getRoot().set;
       // _binding.getRoot().findViewById(R.id.card_view)
        binding=_binding;
    }
    public void connection(Name n){
        binding.setName(n);
    }

    @Override
    public void onClick(View v) {
            //Toast.makeText(MainActivity.getC(),"Yes " +getPosition(),Toast.LENGTH_LONG).show();
        Intent i=new Intent(MainActivity.getC(),FirstAid.class);
        i.putExtra("key",ListAdapter.items.get(getAdapterPosition()).key);
        i.putExtra("title",ListAdapter.items.get(getAdapterPosition()).text);
        //Toast.makeText(MainActivity.getC(),"id:"+ListAdapter.items.get(getPosition()).key,Toast.LENGTH_LONG).show();
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      MainActivity.getC().startActivity(i);
    }
}

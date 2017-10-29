package com.wjr.toybox.common;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/22.
 */

public class NumberTextRecyclerAdapter extends RecyclerView.Adapter<NumberTextHolder>{
    Context context;
    ArrayList<Integer> numbsers;
    public NumberTextRecyclerAdapter(Context context, ArrayList<Integer> numbers) {
        this.context = context;
        this.numbsers = numbers;
    }

    @Override
    public NumberTextHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1,parent,false);
        NumberTextHolder numberTextHolder = new NumberTextHolder(rootView);
        numberTextHolder.numberTextView = (TextView) rootView.findViewById(android.R.id.text1);
        return numberTextHolder;
    }

    @Override
    public void onBindViewHolder(NumberTextHolder holder, int position) {
        holder.numberTextView.setText(""+numbsers.get(position));
    }

    @Override
    public int getItemCount() {
        return numbsers.size();
    }
}

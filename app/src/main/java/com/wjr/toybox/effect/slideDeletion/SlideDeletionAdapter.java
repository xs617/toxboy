package com.wjr.toybox.effect.slideDeletion;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wjr.toybox.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/2/22.
 */

public class SlideDeletionAdapter extends BaseAdapter {
    ArrayList<DataWrapped> data;
    Context context;
    public SlideDeletionAdapter(Context context, @NonNull  ArrayList<DataWrapped> data) {
        this.data = data;
        this.context= context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootView;
        SlideDeletionHolder holder;
        if (convertView == null) {
            rootView = LayoutInflater.from(context).inflate(R.layout.item_slide_deletion, parent, false);
            rootView.setOnClickListener(itemClickLsr);
            holder = new SlideDeletionHolder(data);
            holder.deleteBtn = (TextView) rootView.findViewById(R.id.delete);
            holder.deleteBtn.setTag(holder.deleteBtn.getId(), holder);
            holder.deleteBtn.setOnClickListener(deleteBtnClickLsr);
            holder.messageView = (TextView) rootView.findViewById(R.id.message);
            rootView.setTag(rootView.getId(), holder);
        } else {
            rootView = convertView;
            holder = (SlideDeletionHolder) rootView.getTag(rootView.getId());
        }

        if (position >= 0 && position< data.size()) {
            if (holder != null) {
                holder.messageView.setText(data.get(position).getMessage());
                holder.position = position;
            }

            if (data.get(position).isSlide){
                try {
                    rootView.scrollTo(((SlideDeletionLinearLayout) rootView).getDeleteViewWidth(), 0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                rootView.scrollTo(0, 0);
            }
        }else{
            Log.e("Error","get data out of bound when get view in slideDeletionAdapter");
        }
        return rootView;
    }

    private ItemOperateListener itemOperateListener;
    private View.OnClickListener deleteBtnClickLsr = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SlideDeletionHolder holder = (SlideDeletionHolder) v.getTag(v.getId());
            DataWrapped removedData  =data.remove(holder.position);
            if (itemOperateListener != null) {
                itemOperateListener.OnDelete(removedData);
            }
            notifyDataSetChanged();
        }
    };

    private View.OnClickListener itemClickLsr = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            SlideDeletionHolder holder = (SlideDeletionHolder) v.getTag(v.getId());
            DataWrapped clickedData  =data.get(holder.position);
            if (itemOperateListener != null) {
                itemOperateListener.OnClick(clickedData);
            }
        }
    };
    public interface ItemOperateListener {
        /**
         * you can do something here when the data is remove from list
         * @param dataWrapped the date removed
         */
        void OnDelete(DataWrapped dataWrapped);

        void OnClick(DataWrapped dataWrapped);
    }
    public void setItemOperateListener(ItemOperateListener itemOperateListener){
        this.itemOperateListener = itemOperateListener;
    }
}

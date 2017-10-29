package com.wjr.toybox.widget.mulItemTypeRecyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/29.
 */

public class MulTypeRecyclerViewAdapter extends RecyclerView.Adapter{
    private final int HEAD_VIEW = 0;
    private final int RECYCLER_LIST_ITEM_VIEW = 1;
    private final int FOOTER_VIEW = 2;
    private ArrayList<DataInfo> dataInfos;
    private Context mContext;
    public MulTypeRecyclerViewAdapter(Context context) {
        dataInfos = new ArrayList<DataInfo>();
        for (int i=0;i<200;i++){
            DataInfo dataInfo = new DataInfo();
            dataInfo.data = "" + i;
            dataInfos.add(dataInfo);
        }
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_1,parent,false);
        if (viewType == HEAD_VIEW){
            HeadViewHolder headViewHolder = new HeadViewHolder(item);
            return headViewHolder;
        }else if (viewType == FOOTER_VIEW){
            FooterHolder footerHolder = new FooterHolder(item);

            return footerHolder;
        }else if (viewType == RECYCLER_LIST_ITEM_VIEW){
            ListItemHolder listItemHolder = new ListItemHolder(item);
            return listItemHolder;
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((TextView)holder.itemView).setText(""+position+ " " + holder.getItemViewType() + " " + holder.toString());
    }

    @Override
    public int getItemCount() {
        return dataInfos.size() +2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == HEAD_VIEW){
            return HEAD_VIEW;
        }else if(position == getItemCount() - 1){
            return FOOTER_VIEW;
        }else{
            return RECYCLER_LIST_ITEM_VIEW;
        }
    }

    private class DataInfo{
        String data;
    }

    private class HeadViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public HeadViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }
    }
    private class ListItemHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public ListItemHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }
    }
    private class FooterHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public FooterHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }
    }
}

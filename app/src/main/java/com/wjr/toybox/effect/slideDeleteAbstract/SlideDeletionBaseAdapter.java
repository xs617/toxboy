package com.wjr.toybox.effect.slideDeleteAbstract;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.wjr.toybox.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/2/23.
 */

public abstract class SlideDeletionBaseAdapter extends BaseAdapter implements SlideObserver{
    final static private String SLIDE_ADAPTER_TAG = "slideAdapter";
    Context context;
    //数据数组
    ArrayList<BaseData> dataList;
    //item点击回调监听
    private ItemOperateListener itemOperateListener;
    //滑出的item数
    private int slideNum = 0;

    public SlideDeletionBaseAdapter(Context context, @NonNull ArrayList<BaseData> dataList) {
        this.dataList = dataList;
        this.context= context;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SlideDeletionGenLinearLayout rootView;

        BaseHolder holder;
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            //这是整个框架的Layout
            rootView = (SlideDeletionGenLinearLayout) layoutInflater.inflate(R.layout.item_abstract_slide_deletion, parent, false);
            rootView.setOnClickListener(itemClickLsr);
            FrameLayout customLayout = (FrameLayout) rootView.findViewById(R.id.customLayout);
            //这是用户自定义item内容的回调接口，用户只需将自定义的View加入到CustomLayout里面即可，
            //同时返回一个BaseHolder对象，用户可以通过继承BaseHolder来扩展ViewHolder中的成员
            holder = onCreateCustomHolder(context,customLayout);
            holder.deleteView = (TextView) rootView.findViewById(R.id.delete);
            holder.deleteView.setTag(holder.deleteView.getId(), holder);
            holder.deleteView.setOnClickListener(deleteBtnClickLsr);
            rootView.setTag(rootView.getId(), holder);
            rootView.setSlideObserver(this);
        } else {
            rootView = (SlideDeletionGenLinearLayout) convertView;
            holder = (BaseHolder) rootView.getTag(rootView.getId());
        }
        if (position >= 0 && position< dataList.size()) {
            holder.position = position;
            //这里是将数据和View绑定的接口，可以通过继承BaseData来扩展数据
            onBindViewWithData(holder, dataList.get(position));
            //根据isSlide恢复已经滚出的item、或收起已经滚出的item(因为有可能是回收利用的，要保持一致)
            if (dataList.get(position).getIsSlide()){
                try {
                    rootView.scrollTo(rootView.getDeleteViewWidth(), 0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                rootView.scrollTo(0, 0);
            }
        }else{
            Log.e(SLIDE_ADAPTER_TAG,"get dataList out of bound when get view in slideDeletionAdapter");
        }
        return rootView;
    }

    /**
     * 通过获得LayoutInflater去inflate CustomView，并添加到parentLayout里面去
     * @param context
     * @param parentLayout 将自定义View加入到ParentLayout中,注意这里需要手动加入。如inflate(layoutId,parentLayout,true)
     * @return  BaseHolder对象，可以通过继承并返回其子类来扩展Holder
     */
    abstract protected BaseHolder onCreateCustomHolder(Context context, ViewGroup parentLayout);

    /**
     * 将holder中的View与data关联
     * @param baseHolder onCreateCustomHolder时返回的Holder
     * @param baseData 构造adapter时传入的当前item对应的数据对象
     */
    abstract protected void onBindViewWithData(BaseHolder baseHolder, BaseData baseData);

    private View.OnClickListener deleteBtnClickLsr = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            BaseHolder holder = (BaseHolder) v.getTag(v.getId());
            BaseData removedData  = dataList.get(holder.position);
            //有回调的时候通过回调来判断是否删除成功
            if (itemOperateListener != null) {
                if(itemOperateListener.OnDeletePrepare(removedData)) {
                    slideNum--;
                    dataList.remove(holder.position);
                    itemOperateListener.OnDeleteFinish(removedData,true);
                }else{
//                    Toast.makeText(context,"删除失败",Toast.LENGTH_SHORT).show();
                    itemOperateListener.OnDeleteFinish(removedData,true);
                }
//                Toast.makeText(context,""+slideNum,Toast.LENGTH_SHORT).show();
            }else{
                //没回调的时候直接删除
                slideNum--;
                dataList.remove(holder.position);
            }
            notifyDataSetChanged();
        }
    };

    private View.OnClickListener itemClickLsr = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            BaseHolder holder = (BaseHolder) v.getTag(v.getId());
            BaseData clickedData  = dataList.get(holder.position);
            if (itemOperateListener != null) {
                itemOperateListener.OnClick(clickedData);
            }
        }
    };
    public void setItemOperateListener(ItemOperateListener itemOperateListener){
        this.itemOperateListener = itemOperateListener;
    }

    @Override
    public void notifySlideChange(int position, boolean isSlide) {
        if (dataList.get(position).getIsSlide() != isSlide){
            if (isSlide){
                slideNum ++;
            }else{
                slideNum --;
            }
        }
        dataList.get(position).setIsSlide(isSlide);
//        Toast.makeText(context,""+slideNum,Toast.LENGTH_SHORT).show();
    }

    public int getSlideNum() {
        return slideNum;
    }

}

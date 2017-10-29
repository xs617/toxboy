package com.wjr.toybox.effect.slideDeleteAbstract;

/**
 * Created by Administrator on 2017/2/23.
 */

/**
 * 删除的回调方法在点击deleteView后执行
 * 点击的回调方法在点击itemView后执行
 */
public interface ItemOperateListener {

    /**
     * 在删除item前回调
     * @param baseData 删除item对应的数据
     * @return 是否成功删除，返回true将会将item删除，返回false则不删除
     */
    boolean OnDeletePrepare(BaseData baseData);

    /**
     * 在item被删除后回调
     * @param baseData 删除item对应的数据
     * @param isDeleteSuccess 是否删除成功，其值为OnDeletePrepare传的值
     */
    void OnDeleteFinish(BaseData baseData,boolean isDeleteSuccess);

    /**
     * 在item被点击时回调
     * @param baseData
     */
    void OnClick(BaseData baseData);
}

package com.haomiao.cloud.rx_zhihu.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cloud on 16/4/5.
 */
public class CBaseRecyclerViewAdapter<T>  extends RecyclerView.Adapter{

    protected Context context;
    protected LayoutInflater inflater;
    protected List<T> itemList = new ArrayList<T>();




    public CBaseRecyclerViewAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    /**
     * 判断数据是否为空
     *
     * @return 为空返回true，不为空返回false
     */
    public boolean isEmpty() {
        return itemList.isEmpty();
    }

    /**
     * 在原有的数据上添加新数据
     *
     * @param itemList
     */
    public void addItems(ArrayList<T> itemList) {
        int pos = getItemCount();
        Log.e("CBaseRecyclerViewAdapte", "addpos:" + pos);
        this.itemList.addAll(itemList);
        Log.e("CBaseRecyclerViewAdapte", "itemList.size():" + this.itemList.size());
        notifyItemRangeInserted(pos-1, itemList.size());

    }

    public void removeItems(ArrayList<T> itemList, int postion) {
        this.itemList.remove(postion);
        Log.e("removeItems", "positon" + postion);
        notifyDataSetChanged();
    }

    /**
     * 设置为新的数据，旧数据会被清空
     *
     * @param itemList
     */
    public void setItems(ArrayList<T> itemList) {
        this.itemList.clear();
        this.itemList = itemList;
//        notifyDataSetChanged();
        notifyItemRangeRemoved(0, getItemCount()+1);
    }

    public void setRecycleItems(ArrayList<T> items) {
        // 启动动画的关键位, 顺次添加动画效果
        int pos = getItemCount();
        Log.e("CBaseRecyclerViewAdapte", "setpos:" + pos);
        this.itemList.addAll(items);
        Log.e("CBaseRecyclerViewAdapte", "setitemList.size():" + itemList.size());
        notifyItemRangeInserted(pos, itemList.size());
    }


    /**
     * 清空数据
     */
    public void clearItems() {
        itemList.clear();
        notifyDataSetChanged();
    }




    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }







}

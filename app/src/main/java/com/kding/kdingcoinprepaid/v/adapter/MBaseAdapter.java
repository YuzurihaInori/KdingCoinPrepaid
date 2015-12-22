package com.kding.kdingcoinprepaid.v.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kding.kdingcoinprepaid.R;
import com.kding.kdingcoinprepaid.bean.SelectBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by Toast-pc on 2015/12/22.
 */
public abstract class MBaseAdapter<T,VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH>{

    public final LayoutInflater layoutInflater;
    public final Context mContext;
    public List<T> mData = new ArrayList<>();;


    public MBaseAdapter(Context context) {
        mContext = context;
        layoutInflater= LayoutInflater.from(context);
    }

    public void add(T object) {
        mData.add(object);
        notifyDataSetChanged();
    }

    public void add(int index, T object) {
        mData.add(index, object);
        notifyDataSetChanged();
    }

    public void addAll(List<T> collection) {
        if (collection != null) {
            mData.addAll(collection);
            notifyDataSetChanged();
        }
    }

    public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }

    public void remove(T object) {
        mData.remove(object);
        notifyDataSetChanged();
    }

    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 :mData.size();
    }

    abstract public VH onCreateViewHolder(ViewGroup parent, int viewType);
    abstract public void onBindViewHolder(VH holder, int position);



}

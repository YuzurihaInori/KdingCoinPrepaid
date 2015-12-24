package com.kding.kdingcoinprepaid.v.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kding.kdingcoinprepaid.R;
import com.kding.kdingcoinprepaid.bean.UnionFinalBean;
import com.kding.kdingcoinprepaid.p.callback.ListviewOnclickListener;

import java.util.List;

/**
 * Created by Toast-pc on 2015/12/22.
 */
public class UnionAdapter extends MBaseAdapter<UnionFinalBean,UnionAdapter.UnionHolder>{


    public UnionAdapter(Context context) {
        super(context);
    }

    @Override
    public UnionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UnionHolder(layoutInflater.inflate(R.layout.item_union,parent,false));
    }

    @Override
    public void onBindViewHolder(UnionHolder holder, int position) {

        holder.itemView.setTag(getItem(position));
        holder.unionLeft.setText(getItem(position).unionLeft);
        holder.unionLeft.setCompoundDrawablesWithIntrinsicBounds(getItem(position).unionLeftImg, 0, 0, 0);
        holder.unionRight.setText(getItem(position).unionRight);
    }

    public class UnionHolder extends RecyclerView.ViewHolder{

        private final TextView unionLeft;
        private final TextView unionRight;
        private final View itemView;

        public UnionHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            unionLeft = (TextView)itemView.findViewById(R.id.item_union_left);
            unionRight = (TextView)itemView.findViewById(R.id.item_union_right);
        }
    }
}

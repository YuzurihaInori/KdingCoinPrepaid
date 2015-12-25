package com.kding.kdingcoinprepaid.v.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kding.kdingcoinprepaid.R;
import com.kding.kdingcoinprepaid.bean.TradeDetailBean;

/**
 * Created by Toast-pc on 2015/12/22.
 */
public class CTCRecordDetailAdapter extends MBaseAdapter<TradeDetailBean,CTCRecordDetailAdapter.UnionHolder>{


    public CTCRecordDetailAdapter(Context context) {
        super(context);
    }

    @Override
    public UnionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UnionHolder(layoutInflater.inflate(R.layout.item_trade_detail,parent,false));
    }

    @Override
    public void onBindViewHolder(UnionHolder holder, int position) {

        holder.itemTradeDetail.setText(getItem(position).tradeItem);
    }

    public class UnionHolder extends RecyclerView.ViewHolder{

        private final TextView itemTradeDetail;

        public UnionHolder(View itemView) {
            super(itemView);
            itemTradeDetail = (TextView)itemView.findViewById(R.id.item_trade_detail);
        }
    }
}

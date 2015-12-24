package com.kding.kdingcoinprepaid.v.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.kding.kdingcoinprepaid.R;
import com.kding.kdingcoinprepaid.bean.AccountBean;
import com.socks.library.KLog;

/**
 * Created by Toast-pc on 2015/12/24.
 */
public class AccountAdapter extends BaseLoadMoreAdapter<AccountBean,AccountAdapter.AccountHolder>{


    private final Context context;

    public AccountAdapter(Context context) {
        this.context = context;
    }

    @Override
    public AccountHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {

        return new AccountHolder(LayoutInflater.from(context).inflate(R.layout.item_account,parent,false));

    }

    @Override
    public void onBindItemViewHolder(AccountHolder holder, int position) {


        if (position == 0){
            holder.accountArrow.setVisibility(View.INVISIBLE);
            holder.accountLeft.setTextColor(context.getResources().getColor(R.color.colorTextTitle));
            holder.accountMid.setTextColor(context.getResources().getColor(R.color.colorTextTitle));
            holder.accountRight.setTextColor(context.getResources().getColor(R.color.colorTextTitle));
        }else {
            holder.accountArrow.setVisibility(View.VISIBLE);
            holder.accountLeft.setTextColor(context.getResources().getColor(R.color.colorTextContent));
            holder.accountMid.setTextColor(context.getResources().getColor(R.color.colorTextContent));
            holder.accountRight.setTextColor(context.getResources().getColor(R.color.colorTextContent));
        }

        holder.accountLeft.setText(getItem(position).accountLeft);
        holder.accountMid.setText(getItem(position).accountMid);
        holder.accountRight.setText(getItem(position).accountRight);

    }

    public class AccountHolder extends RecyclerView.ViewHolder {

        private final TextView accountLeft;
        private final TextView accountMid;
        private final TextView accountRight;
        private final ImageButton accountArrow;

        public AccountHolder(View itemView) {
            super(itemView);
            accountLeft = (TextView)itemView.findViewById(R.id.item_account_left);
            accountMid = (TextView)itemView.findViewById(R.id.item_account_mid);
            accountRight = (TextView)itemView.findViewById(R.id.item_account_right);
            accountArrow = (ImageButton)itemView.findViewById(R.id.item_account_arrow);
        }
    }
}

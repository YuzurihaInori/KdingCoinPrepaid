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
import com.kding.kdingcoinprepaid.bean.CTCRecordBean;

/**
 * Created by Toast-pc on 2015/12/24.
 */
public class CTCRecordAdapter extends BaseLoadMoreAdapter<CTCRecordBean,CTCRecordAdapter.CTCRecordHolder>{


    private final Context context;

    public CTCRecordAdapter(Context context) {
        this.context = context;
    }

    @Override
    public CTCRecordHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {

        return new CTCRecordHolder(LayoutInflater.from(context).inflate(R.layout.item_c_t_c_record,parent,false));

    }

    @Override
    public void onBindItemViewHolder(CTCRecordHolder holder, int position) {

        int status = getItem(position).status;

        switch (status){
            case CTCRecordBean.STATUS_SUC:
                holder.accountRight.setTextColor(context.getResources().getColor(R.color.ctc_record_status_suc));
                break;
            case CTCRecordBean.STATUS_OPERATION:
                holder.accountRight.setTextColor(context.getResources().getColor(R.color.ctc_record_status_opertion));
                break;
            case CTCRecordBean.STATUS_EXAMINE:
                holder.accountRight.setTextColor(context.getResources().getColor(R.color.ctc_record_status_examine));
                break;
            default:
                holder.accountRight.setTextColor(context.getResources().getColor(R.color.ctc_record_status_fail));
                break;
        }

        if (position == 0){
            holder.accountArrow.setVisibility(View.INVISIBLE);
            holder.accountLeft.setTextColor(context.getResources().getColor(R.color.colorTextTitle));
            holder.accountMid.setTextColor(context.getResources().getColor(R.color.colorTextTitle));
        }else {
            holder.accountArrow.setVisibility(View.VISIBLE);
            holder.accountLeft.setTextColor(context.getResources().getColor(R.color.colorTextContent));
            holder.accountMid.setTextColor(context.getResources().getColor(R.color.colorTextContent));
        }

        holder.accountLeft.setText(getItem(position).cTCRecordLeft);
        holder.accountMid.setText(getItem(position).cTCRecordMid);
        holder.accountRight.setText(getItem(position).cTCRecordRight);

    }

    public class CTCRecordHolder extends RecyclerView.ViewHolder {

        private final TextView accountLeft;
        private final TextView accountMid;
        private final TextView accountRight;
        private final ImageButton accountArrow;

        public CTCRecordHolder(View itemView) {
            super(itemView);
            accountLeft = (TextView)itemView.findViewById(R.id.item_c_t_c_record_left);
            accountMid = (TextView)itemView.findViewById(R.id.item_c_t_c_record_mid);
            accountRight = (TextView)itemView.findViewById(R.id.item_c_t_c_record_right);
            accountArrow = (ImageButton)itemView.findViewById(R.id.item_c_t_c_record_arrow);
        }
    }
}

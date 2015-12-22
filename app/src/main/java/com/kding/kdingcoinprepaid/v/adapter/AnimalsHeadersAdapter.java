package com.kding.kdingcoinprepaid.v.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;
import com.kding.kdingcoinprepaid.R;
import com.kding.kdingcoinprepaid.p.callback.ListviewOnclickListener;
import com.kding.kdingcoinprepaid.utils.FirstLetterUtil;
import com.socks.library.KLog;

/**
 * Created by Toast-pc on 2015/12/21.
 */
public class AnimalsHeadersAdapter extends AnimalsAdapter<AnimalsHeadersAdapter.HeaderViewHolder>implements StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder> {


    private final ListviewOnclickListener listviewOnclickListener;

    public AnimalsHeadersAdapter(ListviewOnclickListener listviewOnclickListener) {
        this.listviewOnclickListener = listviewOnclickListener;
    }

    @Override
    public AnimalsHeadersAdapter.HeaderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_org, parent, false);
        return new HeaderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HeaderViewHolder holder, final int position) {
        holder.mTextView.setText(getItem(position).selectName);

        KLog.e("position == " + position + "  " + getItem(position).isSelected());
        holder.mCheckBox.setChecked(getItem(position).isSelected());
        holder.mCheckBox.setTag(getItem(position));
        holder.mCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listviewOnclickListener.onClick(v);
            }
        });

    }

    @Override
    public long getHeaderId(int position) {
        if (position == 0) {
            return -1;
        } else {
            return FirstLetterUtil.getFirstLetter(getItem(position).selectName).charAt(0);
        }
    }

    //super
    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_select_org, parent, false);
        return new RecyclerView.ViewHolder(view) {
        };
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        TextView textView = (TextView) holder.itemView;
        textView.setText(FirstLetterUtil.getFirstLetter(String.valueOf(getItem(position).selectName.charAt(0))));
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        public final CheckBox mCheckBox;
        public final TextView mTextView;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView)itemView.findViewById(R.id.listview_select_item_game_name);
            mCheckBox = (CheckBox)itemView.findViewById(R.id.listview_select_item_check);


        }
    }
}
package com.kding.kdingcoinprepaid.v.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.kding.kdingcoinprepaid.R;
import com.kding.kdingcoinprepaid.bean.AccountBean;
import com.kding.kdingcoinprepaid.p.callback.IRefreshAndLoadMoreData;
import com.kding.kdingcoinprepaid.p.callback.IRefreshView;
import com.kding.kdingcoinprepaid.p.impl.AccountImpl;
import com.kding.kdingcoinprepaid.p.impl.RefreshAndLoadImpl;
import com.kding.kdingcoinprepaid.utils.RefreshAndLoadUtil;
import com.kding.kdingcoinprepaid.v.activity.CoinToCashActivity;
import com.kding.kdingcoinprepaid.v.activity.CoinToCashRecordActivity;
import com.kding.kdingcoinprepaid.v.adapter.AccountAdapter;

import java.util.List;


/**
 * Created by sunger on 2015/10/23.
 */
public class Account2Fragment extends BaseFragment implements IRefreshView,IRefreshAndLoadMoreData<AccountBean>{
    private static final String KEY_VIDEO_ID = "id";
    private static final String KEY_VIDEO_TYPE = "type";
    private String id;
    private String type;
    private TextView dayAccout;
    private TextView weekAccout;
    private TextView mouthAccout;
    private TextView allAccout;
    private TextView usableAccout;
    private SwipeRefreshLayout mSwipeRefreshWidget;
    private AccountImpl accountImpl;
    private RefreshAndLoadUtil refreshAndLoadUtil;

    public static Fragment newInstance(String id, String type) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_VIDEO_ID, id);
        bundle.putString(KEY_VIDEO_TYPE, type);
        Fragment fragment = new Account2Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_account_2,container,false);
        initView(view);


        refreshAndLoadUtil = new RefreshAndLoadUtil(getHolder(),null,this);
        refreshAndLoadUtil.bindRefresh(mSwipeRefreshWidget);

        accountImpl = new AccountImpl(this);
        accountImpl.postRefresh(id, type);

        return view;
    }

    private void initView(View view) {

        id = getArguments().getString(KEY_VIDEO_ID);
        type = getArguments().getString(KEY_VIDEO_TYPE);


        mSwipeRefreshWidget = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_widget);
        mSwipeRefreshWidget.setColorSchemeResources(R.color.colorPrimary);

        dayAccout = (TextView)view.findViewById(R.id.accout_2_day);
        weekAccout = (TextView)view.findViewById(R.id.accout_2_week);
        mouthAccout = (TextView)view.findViewById(R.id.accout_2_mouth);
        allAccout = (TextView)view.findViewById(R.id.accout_2_all);
        usableAccout = (TextView)view.findViewById(R.id.accout_2_coin_to_cash);
        Button leftBtn = (Button) view.findViewById(R.id.coin_to_cash_record);
        Button rightBtn = (Button) view.findViewById(R.id.go_to_coin_to_cash_record);

        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getHolder(),CoinToCashRecordActivity.class));
            }
        });
        rightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getHolder(),CoinToCashActivity.class));
            }
        });

    }


    @Override
    public void showRefreshData(List<AccountBean> data) {
        bindData(data);
        refreshAndLoadUtil.stopRefresh();
    }

    @Override
    public void showLoadMoreData(List<AccountBean> data, boolean isEnd) {

    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onRefresh() {
        accountImpl.postRefresh(id,type);
    }

    private void bindData(List<AccountBean> data) {
        dayAccout.setText(String.format(getHolder().getResources().getString(R.string.accout_2_day),data.get(0).account));
        weekAccout.setText(String.format(getHolder().getResources().getString(R.string.accout_2_week),data.get(1).account));
        mouthAccout.setText(String.format(getHolder().getResources().getString(R.string.accout_2_mouth),data.get(2).account));
        allAccout.setText(String.format(getHolder().getResources().getString(R.string.accout_2_all),data.get(3).account));
        usableAccout.setText(data.get(4).account);
    }

}
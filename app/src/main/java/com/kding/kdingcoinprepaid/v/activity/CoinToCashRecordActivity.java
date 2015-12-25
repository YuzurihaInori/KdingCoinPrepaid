package com.kding.kdingcoinprepaid.v.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kding.kdingcoinprepaid.R;
import com.kding.kdingcoinprepaid.bean.AccountBean;
import com.kding.kdingcoinprepaid.bean.CTCRecordBean;
import com.kding.kdingcoinprepaid.p.callback.IRefreshAndLoadMoreData;
import com.kding.kdingcoinprepaid.p.callback.IRefreshView;
import com.kding.kdingcoinprepaid.p.impl.AccountImpl;
import com.kding.kdingcoinprepaid.p.impl.CTCRecordImpl;
import com.kding.kdingcoinprepaid.utils.RefreshAndLoadUtil;
import com.kding.kdingcoinprepaid.v.adapter.AccountAdapter;
import com.kding.kdingcoinprepaid.v.adapter.CTCRecordAdapter;
import com.kding.kdingcoinprepaid.v.adapter.RecyclerItemClickListener;
import com.kding.kdingcoinprepaid.v.fragment.AccountFragment;
import com.kding.kdingcoinprepaid.v.fragment.BaseFragment;

import java.util.List;

public class CoinToCashRecordActivity extends BaseCompatActivity implements IRefreshView,IRefreshAndLoadMoreData<CTCRecordBean>{

    private CTCRecordAdapter cTCRecordAdapter;
    private CTCRecordImpl cTCRecordImpl;
    private SwipeRefreshLayout mSwipeRefreshWidget;
    private RefreshAndLoadUtil refreshUtil;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_to_cash_record);

        initView();

        refreshUtil = new RefreshAndLoadUtil(this, cTCRecordAdapter, this);
        refreshUtil.bindRefresh(mSwipeRefreshWidget);
        refreshUtil.bindLoadMore(recyclerView);

        cTCRecordImpl = new CTCRecordImpl(this);

        refreshUtil.autoRefresh();
    }


    private void initView() {

        setTooblBar(R.id.common_toolbar,R.string.coin_to_cash_record_title,true,true);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);

        mSwipeRefreshWidget = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_widget);
        mSwipeRefreshWidget.setColorSchemeResources(R.color.colorPrimary);

        cTCRecordAdapter = new CTCRecordAdapter(this);

        cTCRecordAdapter.setHasMoreData(true);
        recyclerView.setAdapter(cTCRecordAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(mContext, CTCRecordDetailActivity.class));
            }
        }));
    }

    @Override
    public void onLoadMore() {
        cTCRecordImpl.postLoadMore();
    }

    @Override
    public void onRefresh() {
        cTCRecordImpl.postRefresh();
    }

    @Override
    public void showRefreshData(List<CTCRecordBean> data) {
        refreshUtil.stopRefresh();
        cTCRecordAdapter.clear();
        cTCRecordAdapter.appendToList(data);
        cTCRecordAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoadMoreData(List<CTCRecordBean> data,boolean isEnd) {
        cTCRecordAdapter.appendToList(data);
        refreshUtil.stopLoadMore(isEnd);
    }
}

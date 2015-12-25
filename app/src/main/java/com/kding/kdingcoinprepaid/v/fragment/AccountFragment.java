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

import com.kding.kdingcoinprepaid.R;
import com.kding.kdingcoinprepaid.bean.AccountBean;
import com.kding.kdingcoinprepaid.p.callback.IRefreshAndLoadMoreData;
import com.kding.kdingcoinprepaid.p.callback.IRefreshView;
import com.kding.kdingcoinprepaid.p.impl.AccountImpl;
import com.kding.kdingcoinprepaid.utils.RefreshAndLoadUtil;
import com.kding.kdingcoinprepaid.v.activity.TradeDetailActivity;
import com.kding.kdingcoinprepaid.v.adapter.AccountAdapter;
import com.kding.kdingcoinprepaid.v.adapter.RecyclerItemClickListener;

import java.util.List;


/**
 * Created by sunger on 2015/10/23.
 */
public class AccountFragment extends BaseFragment implements IRefreshView,IRefreshAndLoadMoreData<AccountBean>{
    private static final String KEY_VIDEO_ID = "id";
    private static final String KEY_VIDEO_TYPE = "type";
    private String id;
    private String type;
    private AccountAdapter accountAdapter;
    private AccountImpl accountImpl;
    private SwipeRefreshLayout mSwipeRefreshWidget;
    private RefreshAndLoadUtil refreshUtil;
    private RecyclerView recyclerView;

    public static Fragment newInstance(String id, String type) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_VIDEO_ID, id);
        bundle.putString(KEY_VIDEO_TYPE, type);
        Fragment fragment = new AccountFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_account,container,false);
        initView(view);

        refreshUtil = new RefreshAndLoadUtil(getHolder(), accountAdapter, this);
        refreshUtil.bindRefresh(mSwipeRefreshWidget);
        refreshUtil.bindLoadMore(recyclerView);

        accountImpl = new AccountImpl(this);

        refreshUtil.autoRefresh();

        return view;
    }

    private void initView(View view) {

        id = getArguments().getString(KEY_VIDEO_ID);
        type = getArguments().getString(KEY_VIDEO_TYPE);

        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);

        mSwipeRefreshWidget = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_widget);
        mSwipeRefreshWidget.setColorSchemeResources(R.color.colorPrimary);

        accountAdapter = new AccountAdapter(getHolder());

        accountAdapter.setHasMoreData(true);
        recyclerView.setAdapter(accountAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getHolder()));
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getHolder(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                // TODO: 2015/12/25
                getHolder().startActivity(new Intent(getHolder(), TradeDetailActivity.class));
            }
        }));
    }

    @Override
    public void onLoadMore() {
        accountImpl.postLoadMore(id, type);
    }

    @Override
    public void onRefresh() {
        accountImpl.postRefresh(id,type);
    }

    @Override
    public void showRefreshData(List<AccountBean> data) {
        refreshUtil.stopRefresh();
        accountAdapter.clear();
        accountAdapter.appendToList(data);
        accountAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoadMoreData(List<AccountBean> data,boolean isEnd) {
        accountAdapter.appendToList(data);
        refreshUtil.stopLoadMore(isEnd);
    }
}
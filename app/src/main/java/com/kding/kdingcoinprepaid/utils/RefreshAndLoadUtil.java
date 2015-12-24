package com.kding.kdingcoinprepaid.utils;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;

import com.kding.kdingcoinprepaid.R;
import com.kding.kdingcoinprepaid.p.callback.IRefreshView;
import com.kding.kdingcoinprepaid.v.activity.BaseCompatActivity;
import com.kding.kdingcoinprepaid.v.adapter.BaseLoadMoreAdapter;
import com.kding.kdingcoinprepaid.widget.SolidToast;

/**
 * Created by Toast-pc on 2015/12/24.
 */
public class RefreshAndLoadUtil {

    protected final static int STATE_LOAD = 0;
    protected final static int STATE_NORMAL = 1;
    protected final static int STATE_REFRESH = 2;
    private final BaseCompatActivity activity;
    private final IRefreshView callBack;
    private final BaseLoadMoreAdapter mAdapter;
    protected int currentState = STATE_NORMAL;
    private SwipeRefreshLayout refresh;

    public RefreshAndLoadUtil(final BaseCompatActivity activity, BaseLoadMoreAdapter mAdapter, final IRefreshView callBack) {
        this.activity = activity;
        this.callBack = callBack;
        this.mAdapter = mAdapter;
    }

    public void bindRefresh(SwipeRefreshLayout refresh){
        this.refresh = refresh;
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (currentState == STATE_NORMAL) {
                    currentState = STATE_REFRESH;
                    mAdapter.setHasFooter(true);
                    callBack.onRefresh();
                } else {
                    SolidToast.make(activity, activity.getString(R.string.msg_waitting_loading), Gravity.BOTTOM).setBackgroundColorId(R.color.colorPrimaryDark).show();
                }
            }
        });
    }

    public void bindLoadMore(final RecyclerView mRecyclerView){
        mRecyclerView.addOnScrollListener(new OnRecycleViewScrollListener() {
            @Override
            public void onLoadMore() {
                if (currentState == STATE_NORMAL) {
                    currentState = STATE_LOAD;
                    mAdapter.setHasFooter(true);
                    mRecyclerView.scrollToPosition(mAdapter.getItemCount() - 1);
                    callBack.onLoadMore();
                } else {
                    SolidToast.make(activity, activity.getString(R.string.msg_waitting_loading), Gravity.BOTTOM).setBackgroundColorId(R.color.colorPrimaryDark).show();
                }
            }
        });
    }

    public void autoRefresh() {
        getSwipeRefreshWidget().postDelayed(new Runnable() {
            @Override
            public void run() {
                currentState = STATE_REFRESH;
                getSwipeRefreshWidget().setRefreshing(true);
                callBack.onRefresh();
            }
        }, 600);
    }

    public void stopRefresh() {
        currentState = STATE_NORMAL;
        getSwipeRefreshWidget().setRefreshing(false);
    }

    public void stopLoadMore(boolean isEnd) {
        currentState = STATE_NORMAL;
        mAdapter.setHasMoreData(isEnd);
    }

    public SwipeRefreshLayout getSwipeRefreshWidget() {
        return refresh;
    }
}

package com.kding.kdingcoinprepaid.v.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kding.kdingcoinprepaid.R;
import com.kding.kdingcoinprepaid.bean.TradeDetailBean;
import com.kding.kdingcoinprepaid.p.callback.IResultCallBack;
import com.kding.kdingcoinprepaid.p.impl.TradeDetailImpl;
import com.kding.kdingcoinprepaid.utils.MToast;
import com.kding.kdingcoinprepaid.v.adapter.CTCRecordDetailAdapter;
import com.kding.kdingcoinprepaid.v.adapter.TradeDetailAdapter;

import java.util.List;

public class CTCRecordDetailActivity extends BaseCompatActivity implements IResultCallBack<TradeDetailBean>{

    private CTCRecordDetailAdapter cTCRecordDetailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade_detail);

        initView();

        TradeDetailImpl tradeDetailImpl = new TradeDetailImpl(this);
        tradeDetailImpl.getDataFromServer();
    }

    private void initView() {

        setTooblBar(R.id.common_toolbar,R.string.trade_detail_title,true,true);

        RecyclerView listView = (RecyclerView) findViewById(R.id.trande_detail_listview);

        cTCRecordDetailAdapter = new CTCRecordDetailAdapter(this);

        listView.setLayoutManager(new LinearLayoutManager(this));
        listView.setAdapter(cTCRecordDetailAdapter);
    }


    @Override
    public void resultSuc(List<TradeDetailBean> result) {
        cTCRecordDetailAdapter.addAll(result);
    }

    @Override
    public void resultFail(String errorMsg) {
        MToast.showShort(mContext, errorMsg);
    }
}

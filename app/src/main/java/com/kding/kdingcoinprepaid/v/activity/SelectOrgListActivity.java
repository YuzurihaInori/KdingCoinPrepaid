package com.kding.kdingcoinprepaid.v.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;
import com.kding.kdingcoinprepaid.R;
import com.kding.kdingcoinprepaid.bean.SelectBean;
import com.kding.kdingcoinprepaid.consts.ConstantTag;
import com.kding.kdingcoinprepaid.p.callback.IResultCallBack;
import com.kding.kdingcoinprepaid.p.callback.ListviewOnclickListener;
import com.kding.kdingcoinprepaid.p.callback.SelectListChangeObsever;
import com.kding.kdingcoinprepaid.p.impl.SelectOrgListImpl;
import com.kding.kdingcoinprepaid.utils.MToast;
import com.kding.kdingcoinprepaid.v.adapter.AnimalsHeadersAdapter;
import com.kding.kdingcoinprepaid.v.adapter.RecyclerItemClickListener;
import com.kding.kdingcoinprepaid.v.adapter.decoration.DividerDecoration;

import java.text.Collator;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


public class SelectOrgListActivity extends BaseCompatActivity
        implements ListviewOnclickListener,SelectListChangeObsever,
        IResultCallBack<SelectBean>{
    private TextView chooseGames;
    private SelectOrgListImpl selectOrgListImpl;
    private String selectOrgKey = "";
    private AnimalsHeadersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_org_list);


        selectOrgListImpl = new SelectOrgListImpl(this,this,this);


        initView();

        Intent intent = getIntent();

        if (intent !=null){
            selectOrgKey = intent.getStringExtra(ConstantTag.SELECT_ORG_KEY);
            selectOrgListImpl.getDataFromServer(selectOrgKey);
        }
    }

    private void initView() {

        setTooblBar(R.id.common_toolbar, R.string.select_org_list_title, true, true);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.listview_select);
        chooseGames = (TextView) findViewById(R.id.textview_top_banner);
        Button buttonSubmit = (Button) findViewById(R.id.button_select_list_submit);

        // Set adapter populated with example dummy data
        adapter = new AnimalsHeadersAdapter(this);
        recyclerView.setAdapter(adapter);

        // Set layout manager
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // Add the sticky headers decoration
        final StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(adapter);
        recyclerView.addItemDecoration(headersDecor);

        // Add decoration for dividers between list items
        recyclerView.addItemDecoration(new DividerDecoration(this));

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                // TODO: 2015/12/21

            }
        }));
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                headersDecor.invalidateHeaders();
            }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectOrgListImpl.postGames();
            }
        });

    }

    @Override
    public void onListChange(String games) {
        chooseGames.setText("已选"+games+"游戏");
    }


    @Override
    public void resultSuc(List<SelectBean> result) {
        adapter.addAll(result);
    }

    @Override
    public void resultFail(String errorMsg) {
        MToast.showShort(mContext,errorMsg);
    }

    @Override
    public void onClick(View view) {
        SelectBean selectBean = (SelectBean)view.getTag();
        selectOrgListImpl.addSelectData(selectBean);
    }
}

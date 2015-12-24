package com.kding.kdingcoinprepaid.v.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kding.kdingcoinprepaid.R;
import com.kding.kdingcoinprepaid.bean.CashManagerBean;
import com.kding.kdingcoinprepaid.bean.FragmentBean;
import com.kding.kdingcoinprepaid.p.ICashManger;
import com.kding.kdingcoinprepaid.p.callback.ICashManegerCallBack;
import com.kding.kdingcoinprepaid.p.impl.CashManagerImpl;
import com.kding.kdingcoinprepaid.v.adapter.FragmentAdapter;

public class CashManagerActivity extends BaseCompatActivity implements ICashManegerCallBack{

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private FragmentAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_manager);
        
        initView();

        ICashManger cashManager = new CashManagerImpl(this);
        cashManager.getDataFromServer();
    }

    private void initView() {
        setTooblBar(R.id.common_toolbar,R.string.cash_manager_title,true,true);

        mTabLayout = (TabLayout) this.findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) this.findViewById(R.id.view_pager);
        mAdapter = new FragmentAdapter(getSupportFragmentManager());
    }

    @Override
    public void initFragments(FragmentBean fragmentBean) {
        mAdapter.addItem(fragmentBean);
        mTabLayout.addTab(mTabLayout.newTab());
    }

    @Override
    public void bindData() {
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(mAdapter);
    }
}

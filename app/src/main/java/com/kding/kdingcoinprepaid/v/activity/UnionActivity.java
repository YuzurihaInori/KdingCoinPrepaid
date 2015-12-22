package com.kding.kdingcoinprepaid.v.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.kding.kdingcoinprepaid.R;
import com.kding.kdingcoinprepaid.bean.UnionFinalBean;
import com.kding.kdingcoinprepaid.bean.UnionInfoBean;
import com.kding.kdingcoinprepaid.p.callback.IUnionInitCallBack;
import com.kding.kdingcoinprepaid.p.callback.ListviewOnclickListener;
import com.kding.kdingcoinprepaid.p.impl.UnionImpl;
import com.kding.kdingcoinprepaid.utils.MToast;
import com.kding.kdingcoinprepaid.v.adapter.UnionAdapter;
import com.kding.kdingcoinprepaid.v.adapter.decoration.DividerDecoration;

import java.util.List;

public class UnionActivity extends BaseCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener ,
        IUnionInitCallBack,ListviewOnclickListener{

    private TextView presidentIncome;
    private TextView presidentAllIncome;
    private TextView presidentCoin;
    private TextView presidentCoinToCash;
    private TextView memberCoin;
    private UnionAdapter unionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_union);

        UnionImpl unionImpl = new UnionImpl(this);

        unionImpl.post();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.union_info){

        }else if (id == R.id.union_check){

        }else if (id == R.id.change_pwd){

        }else if (id == R.id.bandle_phone){

        }else if (id == R.id.about_cash){

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void initView4President() {
        findViewById(R.id.content_union_4_president).setVisibility(View.VISIBLE);
        presidentIncome = (TextView)findViewById(R.id.president_income_num);
        presidentAllIncome = (TextView)findViewById(R.id.president_all_income_num);
        presidentCoin = (TextView)findViewById(R.id.president_coin_num);
        presidentCoinToCash = (TextView)findViewById(R.id.president_coin_to_cash_num);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
        initView();
    }

    @Override
    public void initView4Member(String tips) {
        findViewById(R.id.content_union_4_member).setVisibility(View.VISIBLE);
        memberCoin = (TextView)findViewById(R.id.union_member_coin_num);
        TextView memberTitle = (TextView) findViewById(R.id.union_member_title);
        memberTitle.setText(tips);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().removeGroup(R.id.grp3);
        navigationView.getMenu().removeItem(R.id.union_check);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);

        initView();
    }

    @Override
    public void initData4President(UnionInfoBean unionInfoBean, List<UnionFinalBean> unionFinalBeans) {
        presidentIncome.setText(unionInfoBean.income);
        presidentAllIncome.setText(unionInfoBean.allIncome);
        presidentCoin.setText(unionInfoBean.coin);
        presidentCoinToCash.setText(unionInfoBean.coinToCash);
        bindAdapter(unionFinalBeans);
    }


    @Override
    public void initData4Member(UnionInfoBean unionInfoBean, List<UnionFinalBean> unionFinalBeans) {
        memberCoin.setText(unionInfoBean.coin);
        bindAdapter(unionFinalBeans);
    }

    private void initView(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        unionAdapter = new UnionAdapter(this,this);
        RecyclerView unionListview = (RecyclerView) findViewById(R.id.union_listview);
        unionListview.setLayoutManager(new LinearLayoutManager(this));
        unionListview.addItemDecoration(new DividerDecoration(mContext));
        unionListview.setAdapter(unionAdapter);

    }


    private void bindAdapter(List<UnionFinalBean> unionFinalBeans) {
        unionAdapter.clear();
        unionAdapter.addAll(unionFinalBeans);
    }

    @Override
    public void onClick(View view) {
        // TODO: 2015/12/22
        MToast.showShort(this,"click");
    }
}

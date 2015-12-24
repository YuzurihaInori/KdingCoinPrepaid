package com.kding.kdingcoinprepaid.v.adapter;

/**
 * Created by sunger on 2015/10/23.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.kding.kdingcoinprepaid.bean.CashManagerBean;
import com.kding.kdingcoinprepaid.bean.FragmentBean;
import com.kding.kdingcoinprepaid.v.fragment.AccountFragment;


import java.util.ArrayList;
import java.util.List;

public class FragmentAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mFragments = new ArrayList<>();
    private List<FragmentBean> fragmentBeans = new ArrayList<>();

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }


    public void addItem(FragmentBean fragmentBean) {
        fragmentBeans.add(fragmentBean);
        mFragments.add(AccountFragment.newInstance(fragmentBean.id, fragmentBean.type));
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentBeans.get(position).name;
    }

}
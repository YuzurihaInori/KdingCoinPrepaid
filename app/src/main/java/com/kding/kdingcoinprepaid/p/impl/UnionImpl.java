package com.kding.kdingcoinprepaid.p.impl;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.kding.kdingcoinprepaid.R;
import com.kding.kdingcoinprepaid.bean.UnionFinalBean;
import com.kding.kdingcoinprepaid.bean.UnionInfoBean;
import com.kding.kdingcoinprepaid.bean.UserInfoBean;
import com.kding.kdingcoinprepaid.consts.ConstantTag;
import com.kding.kdingcoinprepaid.p.IUnion;
import com.kding.kdingcoinprepaid.p.callback.IUnionInitCallBack;
import com.kding.kdingcoinprepaid.v.activity.CarryOverActivity;
import com.kding.kdingcoinprepaid.v.activity.CashManagerActivity;
import com.kding.kdingcoinprepaid.v.activity.CoinToCashActivity;
import com.kding.kdingcoinprepaid.v.activity.GamesActivity;
import com.kding.kdingcoinprepaid.v.activity.MDiscountActivity;
import com.kding.kdingcoinprepaid.v.activity.RechargeActivity;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Toast-pc on 2015/12/22.
 */
public class UnionImpl implements IUnion{


    private final UserInfoBean userInfo;
    private final IUnionInitCallBack initCallback;
    private final Context context;
    private String discount;

    public UnionImpl(Context context,IUnionInitCallBack initCallback) {
        this.context = context;
        this.initCallback = initCallback;

        userInfo = UserInfoImpl.getInstance().getUserInfo();
        if (userInfo.userType.equals(ConstantTag.USER_TYPE_PRESIDENT)){
            initCallback.initView4President();
        }else if (userInfo.userType.equals(ConstantTag.USER_TYPE_MEMBER)){
            initCallback.initView4Member("土豪，求交往，求抱大腿~");
        }
    }

    @Override
    public void post() {

        final UnionInfoBean unionInfoBean = new UnionInfoBean();
        unionInfoBean.income = "7777.77";
        unionInfoBean.allIncome = "8888.77";
        unionInfoBean.coin = "2000.77";
        unionInfoBean.coinToCash = "6666.77";
        unionInfoBean.discount = "60%";


        final List<UnionFinalBean> unionFinalBeans = getUnionFinalBean(unionInfoBean.discount);

        if (userInfo.userType.equals(ConstantTag.USER_TYPE_PRESIDENT)){
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    initCallback.initData4President(unionInfoBean, unionFinalBeans);
                }
            }, 4000);
        }else if (userInfo.userType.equals(ConstantTag.USER_TYPE_MEMBER)){
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    initCallback.initData4Member(unionInfoBean, unionFinalBeans);
                }
            }, 4000);
        }

    }

    @Override
    public void gotoActivity(UnionFinalBean unionFinalBean) {

        Intent intent = new Intent();

        switch (unionFinalBean.type){
            case ConstantTag.UNION_ITEM_TYPE_RECHAGE:
                KLog.d(ConstantTag.UNION_ITEM_TYPE_RECHAGE);

                intent.setClass(context, RechargeActivity.class);
                intent.putExtra(ConstantTag.CURRENT_DISCOUNT_KEY,discount);

                context.startActivity(intent);

                break;
            case ConstantTag.UNION_ITEM_TYPE_CARRY_OVER:
                KLog.d(ConstantTag.UNION_ITEM_TYPE_CARRY_OVER);

                intent.setClass(context, CarryOverActivity.class);
                context.startActivity(intent);
                break;
            case ConstantTag.UNION_ITEM_TYPE_M_DISCOUNT:
                KLog.d(ConstantTag.UNION_ITEM_TYPE_M_DISCOUNT);
                intent.putExtra(ConstantTag.CURRENT_DISCOUNT_KEY, discount);
                intent.setClass(context, MDiscountActivity.class);
                context.startActivity(intent);
                break;
            case ConstantTag.UNION_ITEM_TYPE_CASH_MANAGER:
                KLog.d(ConstantTag.UNION_ITEM_TYPE_CASH_MANAGER);

                intent.setClass(context, CashManagerActivity.class);
                context.startActivity(intent);
                break;
            case ConstantTag.UNION_ITEM_TYPE_COIN_TO_CASH:
                KLog.d(ConstantTag.UNION_ITEM_TYPE_COIN_TO_CASH);

                intent.setClass(context, CoinToCashActivity.class);
                context.startActivity(intent);
                break;
            case ConstantTag.UNION_ITEM_TYPE_UNION_GAMES:
                KLog.d(ConstantTag.UNION_ITEM_TYPE_UNION_GAMES);

                intent.setClass(context, GamesActivity.class);
                context.startActivity(intent);
                break;
            default:
                break;

        }
    }

    private List<UnionFinalBean> getUnionFinalBean(String discount) {

        this.discount = discount;

        List<UnionFinalBean> unionFinalBeans = new ArrayList<>();

        if (userInfo.userType.equals(ConstantTag.USER_TYPE_PRESIDENT)) {

            UnionFinalBean bean1 = new UnionFinalBean();
            bean1.unionLeftImg = R.mipmap.union_recharge;
            bean1.unionLeft = "匣币进货";
            bean1.unionRight = "当前折扣"+discount;
            bean1.type = ConstantTag.UNION_ITEM_TYPE_RECHAGE;
            UnionFinalBean bean2 = new UnionFinalBean();
            bean2.unionLeftImg = R.mipmap.union_carry_over;
            bean2.unionLeft = "手动充值";
            bean2.unionRight = "划拨匣币给玩家";
            bean2.type = ConstantTag.UNION_ITEM_TYPE_CARRY_OVER;
            UnionFinalBean bean3 = new UnionFinalBean();
            bean3.unionLeftImg = R.mipmap.union_m_discount;
            bean3.unionLeft = "工会折扣";
            bean3.unionRight = "设置工会成员折扣";
            bean3.type = ConstantTag.UNION_ITEM_TYPE_M_DISCOUNT;
            UnionFinalBean bean4 = new UnionFinalBean();
            bean4.unionLeftImg = R.mipmap.union_cash_manager;
            bean4.unionLeft = "账务管理";
            bean4.unionRight = "查看您的收入支出";
            bean4.type = ConstantTag.UNION_ITEM_TYPE_CASH_MANAGER;
            UnionFinalBean bean5 = new UnionFinalBean();
            bean5.unionLeftImg = R.mipmap.union_coin_to_cash;
            bean5.unionLeft = "资金提现";
            bean5.unionRight = "提现您的销售收入";
            bean5.type = ConstantTag.UNION_ITEM_TYPE_COIN_TO_CASH;
            UnionFinalBean bean6 = new UnionFinalBean();
            bean6.unionLeftImg = R.mipmap.union_games;
            bean6.unionLeft = "平台游戏";
            bean6.unionRight = "平台合作游戏";
            bean6.type = ConstantTag.UNION_ITEM_TYPE_UNION_GAMES;

            unionFinalBeans.add(bean1);
            unionFinalBeans.add(bean2);
            unionFinalBeans.add(bean3);
            unionFinalBeans.add(bean4);
            unionFinalBeans.add(bean5);
            unionFinalBeans.add(bean6);
        }else if (userInfo.userType.equals(ConstantTag.USER_TYPE_MEMBER)){

            UnionFinalBean bean1 = new UnionFinalBean();
            bean1.unionLeftImg = R.mipmap.union_recharge;
            bean1.unionLeft = "匣币进货";
            bean1.unionRight = "当前折扣"+discount;
            bean1.type = ConstantTag.UNION_ITEM_TYPE_RECHAGE;
            UnionFinalBean bean2 = new UnionFinalBean();
            bean2.unionLeftImg = R.mipmap.union_cash_manager;
            bean2.unionLeft = "账务管理";
            bean2.unionRight = "查看您的收入支出";
            bean2.type = ConstantTag.UNION_ITEM_TYPE_CASH_MANAGER;
            UnionFinalBean bean3 = new UnionFinalBean();
            bean3.unionLeftImg = R.mipmap.union_games;
            bean3.unionLeft = "平台游戏";
            bean3.unionRight = "平台合作游戏";
            bean3.type = ConstantTag.UNION_ITEM_TYPE_UNION_GAMES;

            unionFinalBeans.add(bean1);
            unionFinalBeans.add(bean2);
            unionFinalBeans.add(bean3);
        }

        return unionFinalBeans;

    }

}

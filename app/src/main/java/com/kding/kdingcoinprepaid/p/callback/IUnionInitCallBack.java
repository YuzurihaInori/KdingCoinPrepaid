package com.kding.kdingcoinprepaid.p.callback;

import com.kding.kdingcoinprepaid.bean.UnionFinalBean;
import com.kding.kdingcoinprepaid.bean.UnionInfoBean;

import java.util.List;

/**
 * Created by Toast-pc on 2015/12/22.
 */
public interface IUnionInitCallBack {

    void initView4President();
    void initView4Member(String tips);
    void initData4President(UnionInfoBean unionInfoBean, List<UnionFinalBean> unionFinalBeans);
    void initData4Member(UnionInfoBean unionInfoBean, List<UnionFinalBean> unionFinalBeans);
}

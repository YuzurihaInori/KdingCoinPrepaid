package com.kding.kdingcoinprepaid.consts;


/**
 * Created by Toast-pc on 2015/12/28.
 */
public class HttpUrl {


    public static final String URL_ROOT = "http://sdk.7xz.com/guild/";

    //注册
    public static final String URL_REG = URL_ROOT+"reg";
    //登录
    public static final String URL_LOGIN = URL_ROOT+"login";
    //绑定手机号
    public static final String URL_BINDCELLPHONE = URL_ROOT+"bindcellphone";
    //解绑手机号
    public static final String URL_UNBINDCELLPHONE = URL_ROOT+"unbindcellphone";
    //忘记密码
    public static final String URL_FORGETPW = URL_ROOT+"forgetpw";
    //游戏列表(应用列表)（是否需要分页？？）
    public static final String URL_GETGUILDS = URL_ROOT+"getguilds";
    //公会列表
    public static final String URL_GETAPPS = URL_ROOT+"getapps";
    //重置密码(已通过手机号验证后才能被调用)
    public static final String URL_RESETPW = URL_ROOT+"resetpw";
    //按游戏取有入驻公会列表
    public static final String URL_GETGUILDSBYAPP = URL_ROOT+"getguildsbyapp";
    //渠道用户创建公会
    public static final String URL_ADDGUILD = URL_ROOT+"addguild";
    //会长修改公会信息(公会名称&会员折扣)
    public static final String URL_EDITGUILD = URL_ROOT+"editguild";
    //会员申请加入公会
    public static final String URL_JOINGUILD = URL_ROOT+"joinguild";
    //会长登录主界面
    public static final String URL_DEACONINDEX = URL_ROOT+"deaconindex";
    //匣币转移(会长划转给会员)
    public static final String URL_COINTRANSFER = URL_ROOT+"cointransfer";
    //充值流水列表(RMB充匣币)
    public static final String URL_RECHARGELIST = URL_ROOT+"rechargelist";
    //充值流水详情
    public static final String URL_RECHARGEDETAIL = URL_ROOT+"rechargedetail";
    //进货流水列表(会长自己用低折扣充值)
    public static final String URL_STOCKLIST = URL_ROOT+"stocklist";
    //进货流水详情
    public static final String URL_STOCKDETAIL = URL_ROOT+"stockdetail";
    //会长返点流水列表
    public static final String URL_COINFANLIST = URL_ROOT+"coinfanlist";
    //会长返点流水详情
    public static final String URL_COINFANDETAIL = URL_ROOT+"coinfandetail";
    //会长提现申请
    public static final String URL_DEACONEXTRACT = URL_ROOT+"deaconextract";
    //会长提现记录
    public static final String URL_EXTRACTLIST = URL_ROOT+"extractlist";
    //提现详情
    public static final String URL_EXTRACTDETAIL = URL_ROOT+"extractdetail";
    //提现资料管理(会长信息编辑)
    public static final String URL_EDITEXTRACTINFO = URL_ROOT+"editextractinfo";
    //会员登录主界面
    public static final String URL_USERINDEX = URL_ROOT+"userindex";
    //修改密码
    public static final String URL_EDITPW = URL_ROOT+"editpw";
    //手机密保
    public static final String URL_MOBILEPREMIUM = URL_ROOT+"mobilepremium";
    //会员入会申请列表
    public static final String URL_APPLYLIST = URL_ROOT+"applylist";
    //会长审核入会申请(0忽略,1通过)
    public static final String URL_APPLYCHECK = URL_ROOT+"applycheck";
    //支付请求采集接口(RMB充匣币） （充值得到匣币计算公式    intval(floor($money * 10 * 100 / $discount))   ）
    public static final String URL_RECHARGE = URL_ROOT+"recharge";
    //支付请求采集接口(玩家消费)
    public static final String URL_PAY = URL_ROOT+"pay";
    //虚拟货币消费接口(玩家消费)
    public static final String URL_COINPAY = URL_ROOT+"coinpay";
    //支付宝支付结果异步通知
    public static final String URL_ALIPAY_NOTIFY = URL_ROOT+"alipay_notify";
    //银联支付结果异步通知
    public static final String URL_UNION_NOTIFY = URL_ROOT+"union_notify";
}

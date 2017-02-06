package cn.wwah.basekit.base.entity;

import android.content.Context;
import android.content.Intent;

import com.google.gson.Gson;

import cn.wwah.basekit.base.activity.BaseActivity;
import cn.wwah.basekit.base.entity.PassException;
import cn.wwah.common.ActivityManagerUtil;
import cn.wwah.common.JLog;

/**
 * author：LiuShenEn on 2016/5/17 11:07
 */
public class ExceptionCase {
    public Gson gson;

    /**
     * 异常类型确定分发
     *
     * @param ex
     * @return
     */
    public PassException ExceptionHandle(Throwable ex) {
        PassException pe = null;
        gson = new Gson();
        JLog.e(ex.getMessage().toString());
        if (ex == null || ex.getMessage() == null) {
            pe = new PassException(PassException.MESSAGE_NULL, "异常消息为空", ex.getClass());
            return pe;
        }

        switch (ex.getMessage().toString()) {
            case "java.net.SocketTimeoutException":
                pe = new PassException(PassException.ERROR_NET, "IP连接失败，请核对", ex.getClass());
                return pe;
            case "java.net.ConnectException":
            case "java.net.UnknownHostException":
                pe = new PassException(PassException.ERROR_NET, "网络未连接", ex.getClass());
                return pe;
            case "Call remote service timeout!":
                pe = new PassException(PassException.ERROR_SYSTEM, "服务器超时异常", ex.getClass());
                return pe;

            /**
             * 服务器异常走默认的
             */
            default:
                try {
                    try {   //TODO：特殊情况，错误消息可能只是一个字符串，并非标准的jsonObject
                        pe = gson.fromJson(ex.getMessage(), PassException.class);
                    } catch (Exception e) {
                        pe = gson.fromJson(ex.toString(), PassException.class);
                    }
                    switch (pe.getCode()) {
                        case PassException.TOKEN_NULLERROR:
                            pe = new PassException(PassException.TOKEN_NULLERROR, "token为空，请重新登录", ex.getClass());
                            return pe;
                        case PassException.TOKEN_INVALID:
                            pe = new PassException(PassException.TOKEN_INVALID, "登陆已过时，请重新登录", ex.getClass());
                            return pe;
                        case PassException.UserNUll:
                            pe = new PassException(PassException.ERROR_SYSTEM, "无法查询到该用户", ex.getClass());
                            return pe;
                        case PassException.ConnectError:
                            pe = new PassException(PassException.ERROR_SYSTEM, "IP连接失败，请核对", ex.getClass());
                            return pe;
                        case PassException.NoInventory_Code:
                            pe = new PassException(PassException.NoInventory, "商品无库存", ex.getClass());
                            return pe;
                        case PassException.Store_NoGoods_Code:
                            pe = new PassException(PassException.Store_NoGoods, "门店无该商品", ex.getClass());
                            return pe;
                        default:
                            if (null == pe)
                                pe = new PassException(PassException.ERROR_UNKNOWN, "未知错误", ex.getClass());
                            return pe;
                    }
                } catch (Exception e) {
                    if (null == pe)
                        pe = new PassException(PassException.ERROR_UNKNOWN, "未知错误", ex.getClass());
                    return pe;
                }

        }
    }

    /**
     * 异常处理
     *
     * @param mContext
     * @param mPassException
     */
    public void solveException(Context mContext, PassException mPassException) {
        BaseActivity mBaseActivity = (BaseActivity) mContext;
        if (mPassException == null || mPassException.getCode() == null) {
            mBaseActivity.mToast.show("mPassException为空异常。致命性错误，请重启应用。");
            return;
        }
        switch (mPassException.getCode()) {
            case PassException.TOKEN_NULLERROR:   //token为空，请重新登录
                ActivityManagerUtil.getInstance().appExit();
                mBaseActivity.mToast.show("请联系益丰电商移动开发组，告知token为空异常。");
                break;
            case PassException.TOKEN_INVALID:  //登陆已过时，请重新登录
                ActivityManagerUtil.getInstance().appExit();
                mBaseActivity.mToast.show("对不起，您已被强制下线。请重新登陆！");
                break;
            case PassException.MESSAGE_NULL:  //消息为空
                mBaseActivity.mToast.show("对不起，错误类型为空。");
                break;

            default:
                mBaseActivity.mToast.show(mPassException.getMessage());
                break;
        }
    }
}

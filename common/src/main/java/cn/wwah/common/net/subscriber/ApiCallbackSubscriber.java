package cn.wwah.common.net.subscriber;

import android.content.Context;

import cn.wwah.common.net.callback.ApiCallback;
import cn.wwah.common.net.exception.ApiException;

/**
 * @Description: 包含回调的订阅者，如果订阅这个，上层在不使用订阅者的情况下可获得回调
 * @author: jeasinlee
 * @date: 2017-01-05 09:35
 */
public class ApiCallbackSubscriber<T> extends ApiSubscriber<T> {

    protected ApiCallback<T> callBack;

    public ApiCallbackSubscriber(Context context, ApiCallback<T> callBack) {
        super(context);
        if (callBack == null) {
            throw new NullPointerException("this callback is null!");
        }
        this.callBack = callBack;
    }

    @Override
    public void onStart() {
        super.onStart();
        callBack.onStart();
    }

    @Override
    public void onError(ApiException e) {
        callBack.onError(e);
    }

    @Override
    public void onCompleted() {
        callBack.onCompleted();
    }

    @Override
    public void onNext(T t) {
        callBack.onNext(t);
    }
}

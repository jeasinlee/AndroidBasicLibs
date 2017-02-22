package cn.wwah.common.net.callback;

import cn.wwah.common.net.exception.ApiException;

/**
 * @Description: API操作回调
 * @author: jeasinlee
 * @date: 2017-01-05 09:39
 */
public abstract class ApiCallback<T> {
    public abstract void onStart();

    public abstract void onError(ApiException e);

    public abstract void onCompleted();

    public abstract void onNext(T t);
}

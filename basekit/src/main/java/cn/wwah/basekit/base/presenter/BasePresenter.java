package cn.wwah.basekit.base.presenter;

import android.content.Context;
import cn.wwah.basekit.base.entity.ExceptionCase;
import cn.wwah.basekit.base.entity.PassException;

import com.trello.rxlifecycle.ActivityLifecycleProvider;

abstract public class BasePresenter {

    protected Context mContext;
    protected ExceptionCase mExceptionCase;

    public BasePresenter(Context context) {
        mContext = context;
        mExceptionCase = new ExceptionCase();
    }


    public void doDestroy() {
        mContext = null;
        // TODO do destroy
    }

    protected ActivityLifecycleProvider getActivityLifecycleProvider() {
        ActivityLifecycleProvider provider = null;
        if (null != mContext && mContext instanceof ActivityLifecycleProvider) {
            provider = (ActivityLifecycleProvider) mContext;
        }
        return provider;
    }

    public PassException doError(Throwable e) {
        ExceptionCase exceptionCase = new ExceptionCase();
        PassException passException = exceptionCase.ExceptionHandle(e);
        return passException;
    }
}

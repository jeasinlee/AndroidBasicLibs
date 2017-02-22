package cn.wwah.basekit.base.presenter;

import android.content.Context;

import com.trello.rxlifecycle.ActivityLifecycleProvider;

abstract public class BasePresenter {

    protected Context mContext;

    public BasePresenter(Context context) {
        mContext = context;
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

    /**
     * 业务异常处理
     * @param e
     * @return
     */
    abstract public Throwable doError(Throwable e);
}

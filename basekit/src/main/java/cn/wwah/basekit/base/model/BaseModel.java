package cn.wwah.basekit.base.model;

import android.content.Context;

import com.trello.rxlifecycle.ActivityEvent;
import com.trello.rxlifecycle.ActivityLifecycleProvider;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * author：jeasinlee on 2015/7/12
 */
public class BaseModel {
    protected Context mContext;

    public BaseModel(Context context) {
        mContext = context;
    }

    protected ActivityLifecycleProvider getActivityLifecycleProvider() {
        ActivityLifecycleProvider provider = null;
        if (null != mContext && mContext instanceof ActivityLifecycleProvider) {
            provider = (ActivityLifecycleProvider) mContext;
        }
        return provider;
    }

    public Observable subscribe(Observable mObservable, Observer observer) {
        mObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(getActivityLifecycleProvider().bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(observer);
        return mObservable;
    }

}

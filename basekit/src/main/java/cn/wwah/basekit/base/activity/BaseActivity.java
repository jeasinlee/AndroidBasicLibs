package cn.wwah.basekit.base.activity;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.CheckResult;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.view.WindowManager;
import com.trello.rxlifecycle.ActivityEvent;
import com.trello.rxlifecycle.ActivityLifecycleProvider;
import com.trello.rxlifecycle.LifecycleTransformer;
import com.trello.rxlifecycle.RxLifecycle;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.List;

import cn.wwah.basekit.R;
import cn.wwah.basekit.base.entity.PassException;
import cn.wwah.basekit.base.iview.IBaseView;
import cn.wwah.common.ActivityManagerUtil;
import cn.wwah.common.DrawerToast;
import cn.wwah.common.JLog;
import rx.Observable;
import rx.subjects.BehaviorSubject;

/**
 * Created by Jixiang_Li on 2017/2/4.
 */

public class BaseActivity extends AutoLayoutActivity implements ActivityLifecycleProvider, IBaseView {
    public ActivityManagerUtil activityManagerUtil;
    public Activity mActivity;
    public DrawerToast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        mActivity = this;
        activityManagerUtil = ActivityManagerUtil.getInstance();
        activityManagerUtil.pushOneActivity(this);

        lifecycleSubject.onNext(ActivityEvent.CREATE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mToast = DrawerToast.getInstance(getApplicationContext());
    }

    private final BehaviorSubject<ActivityEvent> lifecycleSubject = BehaviorSubject.create();
    public String mThisFragmentTAG;
    public String mEndFragmentTAG;


    @Override
    @NonNull
    @CheckResult
    public final Observable<ActivityEvent> lifecycle() {
        return lifecycleSubject.asObservable();
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindUntilEvent(@NonNull ActivityEvent event) {
        return RxLifecycle.bindUntilEvent(lifecycleSubject, event);
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecycle.bindActivity(lifecycleSubject);
    }

    /**
     * 恢复fragmnet正常次序           有问题这个方法不对 ，先保留
     *
     * @param savedInstanceState
     */
    public void fragmentRe(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            List<Fragment> fragmentList = fm.getFragments();
            if (fragmentList != null && fragmentList.size() != 0) {
                mEndFragmentTAG = savedInstanceState.getString("mEndFragmentTAG"); //最后一个的fragment
                for (Fragment fragment : fragmentList) {
                    if (fragment == null) {
                        continue;
                    }
                    if (fragment.getClass().getName().equals(mEndFragmentTAG)) {
                        ft = ft.show(fragment);
                    } else {
                        ft = ft.hide(fragment);
                    }
                }
                ft.commit();
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    @CallSuper
    protected void onStart() {
        super.onStart();
        lifecycleSubject.onNext(ActivityEvent.START);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            JLog.e("   现在是横屏");
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            JLog.e("   现在是竖屏");
        }
    }

    @Override
    @CallSuper
    protected void onResume() {
        super.onResume();
        lifecycleSubject.onNext(ActivityEvent.RESUME);
    }

    @Override
    @CallSuper
    protected void onPause() {
        lifecycleSubject.onNext(ActivityEvent.PAUSE);
        super.onPause();
    }

    @Override
    @CallSuper
    protected void onStop() {
        lifecycleSubject.onNext(ActivityEvent.STOP);
        super.onStop();
    }

    /**
     * 根据标记隐藏上一个frament 并修改标记成当前要显示的
     *
     * @param showFragment
     */
    private void hidePreviousFragment(Fragment showFragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (mThisFragmentTAG != null && mThisFragmentTAG != showFragment.getClass().getName()) {
            Fragment fragmentByTag = fm.findFragmentByTag(mThisFragmentTAG);
            ft.hide(fragmentByTag);
            ft.commit();
        }

        mThisFragmentTAG = showFragment.getClass().getName();
    }

    /**
     * 显示fragment
     *
     * @param mFragment
     * @param isShow
     */


    public void showFragment(@IdRes int containerViewId, Fragment mFragment, boolean isShow) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (isShow) {
            ft.show(mFragment);

        } else {
            ft.add(containerViewId, mFragment, mFragment.getClass().getName());
        }
        ft.commitAllowingStateLoss();
        hidePreviousFragment(mFragment);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //结束Activity&从栈中移除该Activity
        activityManagerUtil.popOneActivity(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showException(PassException pe) {

    }
}

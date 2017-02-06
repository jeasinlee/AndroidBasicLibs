package cn.wwah.basekit;

import android.app.Application;
import cn.wwah.common.CrashHandlerUtil;

/**
 * Created by Jixiang_Li on 2017/2/4.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //崩溃处理
        CrashHandlerUtil crashHandlerUtil = CrashHandlerUtil.getInstance();
        crashHandlerUtil.init(this);
        crashHandlerUtil.setCrashTip("很抱歉，程序出现异常，即将退出！");
    }
}

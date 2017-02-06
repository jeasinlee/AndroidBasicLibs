package cn.wwah.common;

/**
 * 避免重复点击校验
 * Created by Jixiang_Li on 2016/9/2.
 */
public class SingleClick {
    private static final int DEFAULT_TIME = 1000;
    private static long lastTime;

    public static boolean isSingle() {
        boolean isSingle;
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTime <= DEFAULT_TIME) {
            isSingle = true;
        } else {
            isSingle = false;
        }
        lastTime = currentTime;

        return isSingle;
    }
}

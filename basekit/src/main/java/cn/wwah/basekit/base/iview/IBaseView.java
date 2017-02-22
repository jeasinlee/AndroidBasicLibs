package cn.wwah.basekit.base.iview;

/**
 * Created by jeasinlee on 2016/7/12.
 */
public interface IBaseView {
    /**
     * 展示加载条
     */
    void showLoading();

    /**
     * 隐藏加载条
     */
    void hideLoading();

    void showException(Throwable pe);
}

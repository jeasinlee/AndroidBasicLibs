package cn.wwah.common.loader;

/**
 * @Description: 加载工厂，可定制图片加载框架
 * @author: jeasinlee
 * @date: 2016-12-19 15:16
 */
public class LoaderFactory {
    private static ILoader loader;

    public static ILoader getLoader() {
        if (loader == null) {
            synchronized (LoaderFactory.class) {
                if (loader == null) {
                    loader = new GlideLoader();
                }
            }
        }
        return loader;
    }
}

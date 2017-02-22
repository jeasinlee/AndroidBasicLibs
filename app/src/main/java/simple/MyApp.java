package simple;

import cn.wwah.basekit.BaseKitApp;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * author：LiuShenEn on 2017/2/20 15:07
 */
public class MyApp extends BaseKitApp {
    private String baseUrl = "http://gank.io/";
    private Retrofit retrofit;
    private static MyApp myApp;

    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;
        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public static MyApp getApp() {
        return myApp;
    }
}

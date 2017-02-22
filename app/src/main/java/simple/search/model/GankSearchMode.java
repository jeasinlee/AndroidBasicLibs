package simple.search.model;

import android.content.Context;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.wwah.basekit.base.model.BaseModel;
import okhttp3.ResponseBody;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Func1;
import simple.MyApp;
import simple.search.entity.GankM;
import simple.search.entity.SearchM;

/**
 * author：LiuShenEn on 2017/2/21 11:07
 */
public class GankSearchMode extends BaseModel {


    private DBhelper dBhelper;

    public GankSearchMode(Context context) {
        super(context);
        dBhelper = new DBhelper();
    }

    /**
     * 根据内容查询
     *
     * @param content
     * @param mObservable
     */
    public void findContentForGank(final String content, Observer<List<GankM>> mObservable) {
        GankApi gankApi = MyApp.getApp().getRetrofit().create(GankApi.class);
        Observable<List<GankM>> responseBodyObservable = gankApi.Search(content).map(new Func1<ResponseBody, List<GankM>>() {
            @Override
            public List<GankM> call(ResponseBody responseBody) {
                ArrayList<GankM> listGankM = new ArrayList<>();
                try {
                    Document doc = Jsoup.parse(responseBody.string());
                    Elements total = doc.getElementsByTag("ol");
                    Elements items = total.select("li");
                    for (Element element : items) {
                        String title, type, source, url;
                        Elements a = element.getElementsByTag("a");
                        title = a.text();
                        url = a.attr("href");
                        type = element.getElementsByTag("small").text();
                        source = element.getElementsByClass("u-pull-right").text();
                        GankM item = new GankM(title, type, source, url);
                        listGankM.add(item);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return listGankM;
            }
        });

        subscribe(responseBodyObservable, mObservable);
    }

    /**
     * 存历史搜索记录
     *
     * @param mSearchM
     */
    public void saveHistoryKey(final SearchM mSearchM) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                dBhelper.saveHistoryKey(mSearchM);
            }
        }).start();

    }


    /**
     * 读取前N条历史搜索
     *
     * @param mObservable
     */
    public void selectHistoryKey(final int rank, Observer<List<SearchM>> mObservable) {
        Observable<List<SearchM>> listObservable = Observable.create(new Observable.OnSubscribe<List<SearchM>>() {
            @Override
            public void call(Subscriber<? super List<SearchM>> subscriber) {
                List<SearchM> searchMs = dBhelper.selectHistoryKey(rank);
                subscriber.onNext(searchMs);
            }
        });
        subscribe(listObservable, mObservable);

    }
    /**
     * 根据那内容查询
     *
     * @param mObservable
     */
    public void selectHistoryKey(final String key, Observer<List<SearchM>> mObservable) {
        Observable<List<SearchM>> listObservable = Observable.create(new Observable.OnSubscribe<List<SearchM>>() {
            @Override
            public void call(Subscriber<? super List<SearchM>> subscriber) {
                List<SearchM> searchMs = dBhelper.selectHistoryKey(key);
                subscriber.onNext(searchMs);
            }
        });
        subscribe(listObservable, mObservable);

    }
}

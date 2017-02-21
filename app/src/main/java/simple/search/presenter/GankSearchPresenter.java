package simple.search.presenter;

import android.content.Context;

import java.util.List;

import cn.wwah.basekit.base.presenter.BasePresenter;
import rx.Observer;
import simple.search.entity.GankM;
import simple.search.entity.SearchM;
import simple.search.iview.GankSearchIView;
import simple.search.model.GankSearchMode;

/**
 * author：LiuShenEn on 2017/2/21 11:08
 */
public class GankSearchPresenter extends BasePresenter {

    GankSearchMode gsm;
    GankSearchIView gsi;
    private boolean isLodin = false;

    public GankSearchPresenter(Context context, GankSearchIView gsi) {
        super(context);
        this.gsm = new GankSearchMode(context);
        this.gsi = gsi;
    }


    @Override
    public Throwable doError(Throwable e) {
        return null;
    }

    public void findContentForGank(String content) {
        if (isLodin) {
            return;
        }
        gsm.saveHistoryKey(new SearchM(null, content));
        gsi.showLoading();
        isLodin = true;
        gsm.findContentForGank(content, new Observer<List<GankM>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<GankM> gankMs) {
                gsi.showList(gankMs);
                isLodin = false;
            }
        });
    }


    /**
     * 排序查询
     *
     * @param rank
     */
    public void selectHistoryKey(int rank) {
        gsm.selectHistoryKey(10, new Observer<List<SearchM>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<SearchM> mList) {
                gsi.lodinHistory(mList);
            }
        });

    }

    /**
     * 根据关键字查询
     *
     * @param key
     */
    public void selectHistoryKey(String key) {
        gsm.selectHistoryKey(key, new Observer<List<SearchM>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<SearchM> mList) {
                gsi.lodinHistory(mList);
            }
        });

    }

}

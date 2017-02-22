package simple.search.iview;

import java.util.List;

import cn.wwah.basekit.base.iview.IBaseView;
import simple.search.entity.GankM;
import simple.search.entity.SearchM;

/**
 * author：LiuShenEn on 2017/2/21 10:49
 */
public interface GankSearchIView extends IBaseView {

    void showList(List<GankM> gankMs);

    void lodinHistory(List<SearchM> mList);

}

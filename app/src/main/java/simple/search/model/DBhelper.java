package simple.search.model;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import simple.MyApp;
import simple.search.entity.DaoMaster;
import simple.search.entity.DaoSession;
import simple.search.entity.SearchM;
import simple.search.entity.SearchMDao;

/**
 * author：LiuShenEn on 2017/2/21 15:39
 */
public class DBhelper {

    private final SearchMDao searchMDao;

    public DBhelper() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(MyApp.getApp(), "simple-db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        searchMDao = daoSession.getSearchMDao();
    }

    /**
     * 存历史搜索记录
     *
     * @param mSearchM
     */
    public void saveHistoryKey(SearchM mSearchM) {
        List<SearchM> list = searchMDao.queryBuilder()
                .where(SearchMDao.Properties.KeyName.eq(mSearchM.getKeyName()))
                .list();
        if (list == null || list.size() == 0) {
            searchMDao.insert(mSearchM);
        }
    }


    /**
     * 读取前N条历史搜索
     */

    public List<SearchM> selectHistoryKey(int rank) {
        List<SearchM> searchMs = searchMDao.queryBuilder().orderDesc(SearchMDao.Properties.Id).list();
        if (searchMs.size() > rank) {
            return searchMs.subList(0, rank);
        } else {
            return searchMs;
        }
    }

    /**
     * 根据内容查询
     */

    public List<SearchM> selectHistoryKey(String key) {
        List<SearchM> searchMs = searchMDao.queryBuilder().where(SearchMDao.Properties.KeyName.like("%" + key + "%")).orderDesc(SearchMDao.Properties.Id).list();
        return searchMs;
    }
}

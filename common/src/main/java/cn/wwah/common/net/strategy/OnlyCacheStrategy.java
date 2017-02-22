package cn.wwah.common.net.strategy;

import cn.wwah.common.net.core.ApiCache;
import cn.wwah.common.net.mode.CacheResult;
import rx.Observable;

/**
 * @Description: 缓存策略--只取缓存
 * @author: jeasinlee
 * @date: 16/12/31 14:29.
 */
public class OnlyCacheStrategy<T> extends CacheStrategy<T> {
    @Override
    public <T> Observable<CacheResult<T>> execute(ApiCache apiCache, String cacheKey, Observable<T> source, Class<T> clazz) {
        return loadCache(apiCache, cacheKey, clazz);
    }
}

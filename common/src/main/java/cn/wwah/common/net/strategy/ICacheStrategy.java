package cn.wwah.common.net.strategy;

import cn.wwah.common.net.core.ApiCache;
import cn.wwah.common.net.mode.CacheResult;
import rx.Observable;

/**
 * @Description: 缓存策略接口
 * @author: jeasinlee
 * @date: 16/12/31 14:21.
 */
public interface ICacheStrategy<T> {
    <T> Observable<CacheResult<T>> execute(ApiCache apiCache, String cacheKey, Observable<T> source, Class<T> clazz);
}

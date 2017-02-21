package simple.search.model;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * author：LiuShenEn on 2017/2/21 11:24
 */
public interface GankApi {
    @GET("search")
    Observable<ResponseBody> Search(@Query("q") String content);
}

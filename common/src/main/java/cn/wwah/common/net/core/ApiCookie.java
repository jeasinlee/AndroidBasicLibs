package cn.wwah.common.net.core;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import cn.wwah.common.net.mode.CookiesStore;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * @Description: Cookie
 * @author: jeasinlee
 * @date: 16/12/31 21:05.
 */
public class ApiCookie implements CookieJar {

    private final Context mContext;
    private CookiesStore cookieStore;

    public ApiCookie(Context context) {
        mContext = context;
        if (cookieStore == null) {
            cookieStore = new CookiesStore(mContext);
        }
    }

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        if (cookies != null && cookies.size() > 0) {
            for (Cookie item : cookies) {
                cookieStore.add(url, item);
            }
        }
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> cookies = cookieStore.get(url);
        return cookies != null ? cookies : new ArrayList<Cookie>();
    }

}

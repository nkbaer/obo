package android.obo.com.server;

import android.content.Context;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;

/**
 * Created by liuhaifeng on 2018/1/27.
 */

public class BaseAction {
    //TODO 服务器根目录地址
    private static final String DOMAIN = "";
    //TODO 根据实际类型修改
    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("text/json; charset=utf-8");
    protected final OkHttpClient okHttpClient;
    protected Context mContext;

    public BaseAction(Context context)
    {
        mContext = context;
        okHttpClient = new OkHttpClient();
    }

    /**
     * 获取完整URL方法
     *
     * @param url url
     */
    protected String getURL(String url) {
        return getURL(url, new String[] {});
    }

    /**
     * 获取完整URL方法
     *
     * @param url    url
     * @param params params
     */
    protected String getURL(String url, String... params) {
        StringBuilder urlBuilder = new StringBuilder(DOMAIN).append("/").append(url);
        if (params != null) {
            for (String param : params) {
                if (!urlBuilder.toString().endsWith("/")) {
                    urlBuilder.append("/");
                }
                urlBuilder.append(param);
            }
        }
        return urlBuilder.toString();
    }
}

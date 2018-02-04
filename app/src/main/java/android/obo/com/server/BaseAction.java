package android.obo.com.server;

import android.content.Context;
import android.obo.com.obo.App;
import android.obo.com.server.request.LoginRequest;
import android.obo.com.server.response.LoginResponse;
import android.obo.com.utils.JsonManager;
import android.text.TextUtils;
import android.util.Log;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

/**
 * Created by liuhaifeng on 2018/1/27.
 */

public class BaseAction {
    //TODO 服务器根目录地址
    private static final String DOMAIN = "http://192.168.1.7:8080/mweb/";
    //TODO 根据实际类型修改
    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");
    private static final String TAG = BaseAction.class.getSimpleName();
    protected final OkHttpClient okHttpClient;
    protected Context mContext;

    public BaseAction(Context context)
    {
        mContext = context;
        okHttpClient = new OkHttpClient();
    }

    public Object httpPost(String url,Object o,Class out) throws Exception
    {
        String uri = getURL(url);
        String jsonRequest = "";
        if (o != null)
        {
            jsonRequest = JsonManager.beanToJson(o);
        }
        Request.Builder okBuilder = new Request.Builder().url(uri);
        if (!TextUtils.isEmpty(App.getInstance().loginSessionId))
        {
            okBuilder.addHeader("cookie","loginSessionId="+App.getInstance().loginSessionId);
        }
        Request okRequest = okBuilder.post(RequestBody.create(MEDIA_TYPE_JSON, jsonRequest)).build();
        Log.d(TAG,"request:"+okRequest.toString());
        Response okResponse = okHttpClient.newCall(okRequest).execute();
        String responseJson = okResponse.body().string();
        Log.d(TAG,"response:"+responseJson);
        if (!okResponse.isSuccessful())
        {
            throw new Exception("unexpectedCode:" + okResponse);
        }
        Object result = null;
        if (!TextUtils.isEmpty(responseJson))
        {
            result = JsonManager.jsonToBean(responseJson, out);
        }
        return result;
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

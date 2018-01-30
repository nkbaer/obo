package android.obo.com.server;

import android.content.Context;
import android.obo.com.server.request.GetTokenRequest;
import android.obo.com.server.request.LoginRequest;
import android.obo.com.server.response.GetTokenResponse;
import android.obo.com.server.response.LoginResponse;
import android.obo.com.utils.JsonManager;
import android.text.TextUtils;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

/**
 * Created by liuhfa on 2018/1/25.
 */

public class OboAction extends BaseAction
{
    public OboAction(Context context)
    {
        super(context);
    }

    /**
     * 登录
     * @param phone 手机号
     * @param password 密码
     * @return
     * @throws Exception
     */
    public LoginResponse login(String phone, String password) throws Exception
    {
        //TODO login实际地址
        String uri = getURL("user/login");
        String json = JsonManager.beanToJson(new LoginRequest(phone, password));
        Request request = new Request.Builder().url(uri).post(RequestBody.create(MEDIA_TYPE_JSON, json)).build();
        Response response = okHttpClient.newCall(request).execute();
        String responseJson = response.body().string();
        if (!response.isSuccessful())
        {
            throw new Exception("unexpectedCode:" + response);
        }
        LoginResponse result = null;
        if (!TextUtils.isEmpty(responseJson))
        {
            result = JsonManager.jsonToBean(responseJson, LoginResponse.class);
        }
        return result;
    }

    /**
     * 获取融云token
     * @return
     */
    public GetTokenResponse getToken() throws Exception
    {
        String url = getURL("user/get_token");
        String json = JsonManager.beanToJson(new GetTokenRequest());
        Request request = new Request.Builder().url(url).post(RequestBody.create(MEDIA_TYPE_JSON, json)).build();
        Response response = okHttpClient.newCall(request).execute();
        String responseJson = response.body().string();

        GetTokenResponse result = null;
        if (!TextUtils.isEmpty(responseJson)) {
            result = JsonManager.jsonToBean(responseJson, GetTokenResponse.class);
        }
        return result;
    }

    /**
     * 获取用户信息
     * @param connectResultId
     * @return
     */
    public Object getUserInfoById(String connectResultId)
    {
        return null;
    }

    /**
     * 检查手机号码是否可用
     * @param prefix
     * @param phone
     * @return
     */
    public Object checkPhoneAvailable(String prefix, String phone)
    {
        return null;
    }

    /**
     * 获取验证码
     * @param prefix
     * @param phone
     * @return
     */
    public Object sendCode(String prefix, String phone)
    {
        return null;
    }

    /**
     * 检验验证码
     * @param prefix
     * @param phone
     * @param code
     * @return
     */
    public Object verifyCode(String prefix, String phone, String code)
    {
        return null;
    }

    /**
     * 注册
     * @param nickname
     * @param password
     * @param codetoken
     * @return
     */
    public Object register(String nickname, String password, String codetoken)
    {
        return null;
    }
}

package android.obo.com.server;

import android.content.Context;
import android.obo.com.server.request.GetTokenRequest;
import android.obo.com.server.request.LoginRequest;
import android.obo.com.server.request.RegisterRequest;
import android.obo.com.server.response.GetTokenResponse;
import android.obo.com.server.response.GetUserInfoByIdResponse;
import android.obo.com.server.response.LoginResponse;
import android.obo.com.server.response.RegisterResponse;
import android.obo.com.server.response.SendCodeResponse;
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
//        String uri = getURL("appservice/login");
//        String json = JsonManager.beanToJson(new LoginRequest(phone, password));
//        Request request = new Request.Builder().url(uri).post(RequestBody.create(MEDIA_TYPE_JSON, json)).build();
//        Response response = okHttpClient.newCall(request).execute();
//        String responseJson = response.body().string();
//        if (!response.isSuccessful())
//        {
//            throw new Exception("unexpectedCode:" + response);
//        }
//        LoginResponse result = null;
//        if (!TextUtils.isEmpty(responseJson))
//        {
//            result = JsonManager.jsonToBean(responseJson, LoginResponse.class);
//        }
//        return result;
        return (LoginResponse) httpPost("appservice/login",new LoginRequest(phone,password),LoginResponse.class);
    }

    /**
     * 获取融云token
     * @return
     */
    public GetTokenResponse getToken() throws Exception
    {
        return (GetTokenResponse) httpPost("appservice/getrongcloudtoken",null,GetTokenResponse.class);
    }

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    public GetUserInfoByIdResponse getUserInfoById(String userId) throws Exception
    {
        return (GetUserInfoByIdResponse) httpPost("appservice/getpreference/"+userId,null,GetUserInfoByIdResponse.class);
    }

    /**
     * 检查手机号码是否可用
     * @param prefix
     * @param phone
     * @return
     */
    public boolean checkPhoneAvailable(String prefix, String phone)
    {
        //检查后台检测词电话号码是否注册过
        return true;
    }

    /**
     * 获取验证码
     * @param prefix
     * @param phone
     * @return
     */
    public SendCodeResponse sendCode(String prefix, String phone) throws Exception
    {
        return (SendCodeResponse) httpPost("appservice/getmobilecode/"+phone,null, SendCodeResponse.class);
    }

    /**
     * 检验验证码
     * @param prefix
     * @param phone
     * @param code
     * @return
     */
    public boolean verifyCode(String prefix, String phone, String code)
    {
        //验证短信验证码
        return true;
    }

    /**
     * 注册
     * @param phone
     * @param password
     * @param mobilecode
     * @return
     */
    public RegisterResponse register(String phone, String password, String mobilecode) throws Exception
    {
        return (RegisterResponse) httpPost("appservice/register",new RegisterRequest(phone,password,mobilecode),RegisterResponse.class);
    }
}

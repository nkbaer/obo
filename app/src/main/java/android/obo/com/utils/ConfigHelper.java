package android.obo.com.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.obo.com.obo.App;

/**
 * Created by liuhfa on 2018/1/25.
 */

public class ConfigHelper
{
    public static SharedPreferences sp = App.getInstance().getSharedPreferences(ConfigConstant.CONFIG, Context.MODE_PRIVATE);

    /**
     * 设置登录token缓存
     * @param token
     */
    public static void setToken(String token)
    {
        sp.edit().putString(ConfigConstant.LOGIN_TOKEN,token).commit();
    }

    /**
     * 获取登录token缓存
     * @return
     */
    public static String getToken()
    {
       return sp.getString(ConfigConstant.LOGIN_TOKEN,"");
    }


    public static void setLoginId(String loginId)
    {
        sp.edit().putString(ConfigConstant.LOGIN_ID,loginId).commit();
    }

    /**
     * 获取上次登录的手机号
     * @return
     */
    public static String getOldPhone()
    {
        return sp.getString(ConfigConstant.LOGIN_OLD_PHONE,"");
    }

    /**
     * 获取上次登录的密码
     * @return
     */
    public static String getOldPassword()
    {
        return sp.getString(ConfigConstant.LOGIN_OLD_PWD,"");
    }

    public static void setLoginPhone(String phone)
    {
        sp.edit().putString(ConfigConstant.LOGIN_PHONE,phone).commit();
    }

    public static void setLoginPassword(String password)
    {
        sp.edit().putString(ConfigConstant.LOGIN_PWD,password).commit();
    }

    public static void setLoginName(String nickname)
    {
        sp.edit().putString(ConfigConstant.LOGIN_NICK_NAME,nickname).commit();
    }

    /**
     * 设置头像uri
     * @param portraitUri
     */
    public static void setPortraitUri(String portraitUri)
    {
        sp.edit().putString(ConfigConstant.PORTRAIT_URI,portraitUri).commit();
    }
}

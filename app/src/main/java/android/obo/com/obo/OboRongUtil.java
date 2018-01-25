package android.obo.com.obo;

import android.obo.com.utils.ConfigHelper;
import android.util.Log;

import io.rong.imlib.RongIMClient;

/**
 * 融云相关监听事件类合集
 * Created by liuhfa on 2018/1/25.
 */

public class OboRongUtil
{
    private static final String TAG = "OboRongUtil";
    private static OboRongUtil instance;

    private OboRongUtil()
    {}



    public static OboRongUtil getInstance()
    {
        if (instance == null)
        {
            instance = new OboRongUtil();
        }
        return instance;
    }


    public RongIMClient.ConnectCallback getConnectCallBack()
    {
        RongIMClient.ConnectCallback connectCallback = new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                Log.d(TAG, "ConnectCallback connect onTokenIncorrect");
            }

            @Override
            public void onSuccess(String s) {
                Log.d(TAG, "ConnectCallback connect onSuccess");
                ConfigHelper.setLoginId(s);
            }

            @Override
            public void onError(final RongIMClient.ErrorCode e) {
                Log.d(TAG, "ConnectCallback connect onError-ErrorCode=" + e);
            }
        };
        return connectCallback;
    }
}

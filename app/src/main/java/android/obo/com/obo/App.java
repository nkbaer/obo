package android.obo.com.obo;

import android.app.Application;
import android.obo.com.server.response.Account;
import android.os.Handler;
import android.util.Log;

import io.rong.imkit.RongIM;

/**
 * Created by liuhfa on 2018/1/24.
 */

public class App extends Application
{

    private static final String TAG = "App";
    public static volatile Handler applicationHandler = null;
    public String loginSessionId = "";
    public Account account;

    @Override
    public void onCreate()
    {
        super.onCreate();
        instance = this;
        Log.d(TAG,"onCreate");
        RongIM.init(this);
        applicationHandler = new Handler(this.getMainLooper());
    }

    private static App instance;

    public static App getInstance()
    {
        return instance;
    }
}

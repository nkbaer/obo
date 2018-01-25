package android.obo.com.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.obo.com.utils.ConfigHelper;
import android.obo.com.obo.OboRongUtil;
import android.obo.com.obo.R;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Window;

import io.rong.imkit.RongIM;

/**
 * Created by liuhfa on 2018/1/25.
 */

public class SplashActivity extends Activity
{
    private static final long SPLASH_DELAY_TIME = 800;
    private Handler mHandler = new Handler();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        String cacheToken = ConfigHelper.getToken();
        if (!TextUtils.isEmpty(cacheToken))
        {
            RongIM.connect(cacheToken, OboRongUtil.getInstance().getConnectCallBack());
            mHandler.postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    goToMain();
                }
            },SPLASH_DELAY_TIME);
        }
        else
        {
            mHandler.postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    goToLogin();
                }
            },SPLASH_DELAY_TIME);
        }
    }

    private void goToLogin()
    {
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        finish();
    }

    private void goToMain()
    {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }
}

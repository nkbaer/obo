package android.obo.com.obo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

/**
 * Created by liuhfa on 2018/1/24.
 */

public class MainActivity extends Activity implements View.OnClickListener
{

    private static final String TAG = "MainActivity";

    private boolean connectSuccess = false;

    private String token = "cW9DczZkNGRKUHaLk7KOi2+eu8ZN9Ibrmh4kJ1kG7JGHrhyqAUHh1d9gkLTzlvkVsa1AUxwotN/6fnmxQhzHLw==";
    private ViewPager container;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        connectRong();
        initUserInfoProvider();

        initView();

        initViewPager();
    }

    private void initView()
    {
        findViewById(R.id.btn_talk).setOnClickListener(this);
        container = findViewById(R.id.vp_container);
    }

    private void initViewPager()
    {
        List<View> views = new ArrayList<>();

        container.setAdapter(new MainPagerAdapter(views));
    }

    /**
     * 初始化用户信息提供者
     */
    private void initUserInfoProvider()
    {
        RongIM.setUserInfoProvider(new OboUserInfoProvider(),true);
    }

    /**
     * 连接融云服务器，通过我们的服务器获取token
     */
    private void connectRong()
    {
        RongIM.connect(token, new RongIMClient.ConnectCallback()
        {
            @Override
            public void onTokenIncorrect()
            {
                Log.e(TAG,"onTokenIncorrect");
                connectSuccess = false;
            }

            @Override
            public void onSuccess(String s)
            {
                Log.e(TAG,"onSuccess:"+s);
                connectSuccess = true;
                ToastUtils.makeToast(getResources().getString(R.string.get_conversation_success));
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode)
            {
                Log.e(TAG,"errorCode:"+errorCode.getMessage());
                connectSuccess = false;
            }
        });
    }

    private void goConversation()
    {
        if (connectSuccess)
        {
            RongIM.getInstance().startPrivateChat(this,"520","test");
        }
        else
        {
            ToastUtils.makeToast(getResources().getString(R.string.get_conversation_fail));
        }
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btn_talk:
                goConversation();
                break;
        }
    }
}

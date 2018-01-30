package android.obo.com.ui.activity;

import android.content.Context;
import android.media.AudioManager;
import android.obo.com.server.OboAction;
import android.obo.com.obo.R;
import android.obo.com.utils.ToastUtils;
import android.obo.com.server.async.AsyncTaskManager;
import android.obo.com.server.async.OnDataListener;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

/**
 * Created by liuhfa on 2018/1/25.
 */

public class BaseActivity extends FragmentActivity implements OnDataListener
{

    public AsyncTaskManager mAsyncTaskManager;
    protected OboAction action;
    protected Context context;
    protected static final int HTTP_RESPONSE_OK = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);// 使得音量键控制媒体声音

        context = this;
        mAsyncTaskManager = AsyncTaskManager.getInstance(getApplicationContext());
        // Activity管理
        action = new OboAction(this);
    }

    /**
     * 发送请求（需要检查网络）
     *
     * @param requestCode 请求码
     */
    public void request(int requestCode) {
        if (mAsyncTaskManager != null) {
            mAsyncTaskManager.request(requestCode, this);
        }
    }

    /**
     * 发送请求（需要检查网络）
     *
     * @param id 请求数据的用户ID或者groupID
     * @param requestCode 请求码
     */
    public void request(String id , int requestCode) {
        if (mAsyncTaskManager != null) {
            mAsyncTaskManager.request(id, requestCode, this);
        }
    }

    /**
     * 发送请求
     *
     * @param requestCode    请求码
     * @param isCheckNetwork 是否需检查网络，true检查，false不检查
     */
    public void request(int requestCode, boolean isCheckNetwork) {
        if (mAsyncTaskManager != null) {
            mAsyncTaskManager.request(requestCode, isCheckNetwork, this);
        }
    }

    /**
     * 取消所有请求
     */
    public void cancelRequest() {
        if (mAsyncTaskManager != null) {
            mAsyncTaskManager.cancelRequest();
        }
    }

    @Override
    public Object doInBackground(int requestCode, String parameter) throws Exception
    {
        return null;
    }

    @Override
    public void onSuccess(int requestCode, Object result)
    {

    }

    @Override
    public void onFailure(int requestCode, int state, Object result)
    {
        switch (state) {
            // 网络不可用给出提示
            case AsyncTaskManager.HTTP_NULL_CODE:
                ToastUtils.makeToast(R.string.network_unavailable);
                break;
            // 网络有问题给出提示
            case AsyncTaskManager.HTTP_ERROR_CODE:
                ToastUtils.makeToast(R.string.network_issue_try_again);
                break;
            // 请求有问题给出提示
            case AsyncTaskManager.REQUEST_ERROR_CODE:
                break;
        }
    }
}

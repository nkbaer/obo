package android.obo.com.obo;

import android.content.Context;
import android.util.Log;

import io.rong.push.notification.PushMessageReceiver;
import io.rong.push.notification.PushNotificationMessage;

/**
 * Created by liuhfa on 2018/1/24.
 */

public class RongNotiReceiver extends PushMessageReceiver
{
    private static final String TAG = "RongNotiReceiver";

    @Override
    public boolean onNotificationMessageArrived(Context context, PushNotificationMessage pushNotificationMessage)
    {
        Log.e(TAG,"onNotificationMessageArrived:"+pushNotificationMessage.getPushTitle());
        return false;

    }

    @Override
    public boolean onNotificationMessageClicked(Context context, PushNotificationMessage pushNotificationMessage)
    {
        Log.e(TAG,"onNotificationMessageArrived:"+pushNotificationMessage.getPushTitle());
        return false;
    }
}

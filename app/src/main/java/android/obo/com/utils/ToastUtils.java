package android.obo.com.utils;

import android.obo.com.obo.App;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by liuhfa on 2018/1/24.
 */

public class ToastUtils
{
    public static void makeToast(final String msg)
    {
        if (TextUtils.isEmpty(msg))
        {
            return;
        }
        App.applicationHandler.removeCallbacks(toastRunnable);
        toastRunnable = new Runnable()
        {
            @Override
            public void run()
            {
                Toast.makeText(App.getInstance(), msg, Toast.LENGTH_SHORT).show();
            }
        };
        App.applicationHandler.post(toastRunnable);
    }

    private static Runnable toastRunnable;

    public static void makeToast(int strId)
    {
        makeToast(App.getInstance().getString(strId));
    }
}

package android.obo.com.obo;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import java.util.List;

/**
 * Created by liuhfa on 2018/1/24.
 */

public class MainPagerAdapter extends PagerAdapter
{
    private List<View> mView;

    public MainPagerAdapter(List<View> views)
    {
        mView = views;
    }

    @Override
    public int getCount()
    {
        if (mView == null)
        {
            return 0;
        }
        return mView.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object)
    {

        if (mView != null && (!mView.contains(view) || !mView.contains(object)))
        {
            return false;
        }
        return view == object;
    }
}

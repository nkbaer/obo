package android.obo.com.obo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by liuhfa on 2018/1/24.
 */

public class ConversationActivity extends FragmentActivity
{
    private static final String TAG = "ConversationActivity";

    private TextView tvTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversation);
        Log.d(TAG,"onCreate");

        initViews();

        makeTitle();
    }

    private void makeTitle()
    {
        String title = getIntent().getData().getQueryParameter("title");
        Log.d(TAG,"makeTitle:"+title);
        tvTitle.setText(title);
    }

    private void initViews()
    {
        tvTitle = findViewById(R.id.tv_title);
    }
}

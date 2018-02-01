package android.obo.com.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.obo.com.obo.R;
import android.obo.com.server.response.GetTokenResponse;
import android.obo.com.server.response.GetUserInfoByIdResponse;
import android.obo.com.server.response.LoginResponse;
import android.obo.com.ui.widget.ClearWriteEditText;
import android.obo.com.ui.widget.LoadDialog;
import android.obo.com.utils.CommonUtils;
import android.obo.com.utils.ConfigHelper;
import android.obo.com.utils.ToastUtils;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;

/**
 * Created by liuhfa on 2018/1/25.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener
{
    private static final int LOGIN = 5;
    private static final int GET_TOKEN = 6;
    private static final int SYNC_USER_INFO = 9;
    private static final String TAG = "LoginActivity";

    private ClearWriteEditText cwet_login_phone, cwet_login_password;
    private Button btn_login_sign;
    private TextView tv_login_forgot, tv_login_register;
    private String phoneString;
    private String passwordString;
    private String connectResultId;
    private String loginToken;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView()
    {
        cwet_login_phone = findViewById(R.id.cwet_login_phone);
        cwet_login_password = findViewById(R.id.cwet_login_password);
        btn_login_sign = findViewById(R.id.btn_login_sign);
        tv_login_forgot = findViewById(R.id.tv_login_forgot);
        tv_login_register = findViewById(R.id.tv_login_register);
        btn_login_sign.setOnClickListener(this);
        tv_login_forgot.setOnClickListener(this);
        tv_login_register.setOnClickListener(this);
        cwet_login_phone.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                if (charSequence.length() == 11)
                {
                    CommonUtils.onInactive(context, cwet_login_phone);
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
        });
        String oldPhone = ConfigHelper.getOldPhone();
        String oldPassword = ConfigHelper.getOldPassword();
        if (!TextUtils.isEmpty(oldPhone) && !TextUtils.isEmpty(oldPassword))
        {
            cwet_login_phone.setText(oldPhone);
            cwet_login_password.setText(oldPassword);
        }

    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btn_login_sign:
                phoneString = cwet_login_phone.getText().toString().trim();
                passwordString = cwet_login_password.getText().toString().trim();

                if (TextUtils.isEmpty(phoneString))
                {
                    ToastUtils.makeToast(R.string.phone_number_is_null);
                    cwet_login_phone.setShakeAnimation();
                    return;
                }

                if (TextUtils.isEmpty(passwordString))
                {
                    ToastUtils.makeToast(R.string.password_is_null);
                    cwet_login_password.setShakeAnimation();
                    return;
                }
                if (passwordString.contains(" "))
                {
                    ToastUtils.makeToast(R.string.password_cannot_contain_spaces);
                    cwet_login_password.setShakeAnimation();
                    return;
                }
                LoadDialog.show(context);
                request(LOGIN, true);
                break;
            case R.id.tv_login_forgot:
                startActivityForResult(new Intent(this, ForgetPasswordActivity.class), 2);
                break;
            case R.id.tv_login_register:
                startActivityForResult(new Intent(this, RegisterActivity.class), 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == 2 && data != null)
        {
            String phone = data.getStringExtra("phone");
            String password = data.getStringExtra("password");
            cwet_login_phone.setText(phone);
            cwet_login_password.setText(password);
        }
        else if (data != null && requestCode == 1)
        {
            String phone = data.getStringExtra("phone");
            String password = data.getStringExtra("password");
            String id = data.getStringExtra("id");
            String nickname = data.getStringExtra("nickname");
            if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(id) && !TextUtils.isEmpty(nickname))
            {
                cwet_login_phone.setText(phone);
                cwet_login_password.setText(password);
                ConfigHelper.setLoginId(id);
                ConfigHelper.setLoginPhone(phone);
                ConfigHelper.setLoginPassword(password);
                ConfigHelper.setLoginName(nickname);
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public Object doInBackground(int requestCode, String parameter) throws Exception
    {
        switch (requestCode)
        {
            case LOGIN:
                return action.login(phoneString, passwordString);
            case GET_TOKEN:
                return action.getToken();
            case SYNC_USER_INFO:
                return action.getUserInfoById(connectResultId);
        }
        return null;
    }

    @Override
    public void onSuccess(int requestCode, Object result)
    {
        if (result != null)
        {
            switch (requestCode)
            {
                case LOGIN:
                    LoginResponse loginResponse = (LoginResponse) result;
                    if (loginResponse.getResultType() == HTTP_RESPONSE_OK)
                    {
                        //
                        Log.d(TAG,"login id:"+loginResponse.getResultType()+",desc:"+loginResponse.getDesc());
                        request(GET_TOKEN);
                    }
                    else if (loginResponse.getResultType() == 1)
                    {
                        LoadDialog.dismiss(context);
                        ToastUtils.makeToast(R.string.phone_or_psw_error);
                    }
                    break;
                case SYNC_USER_INFO:
                    GetUserInfoByIdResponse userInfoByIdResponse = (GetUserInfoByIdResponse) result;
                    if (userInfoByIdResponse.getCode() == HTTP_RESPONSE_OK)
                    {
                        if (TextUtils.isEmpty(userInfoByIdResponse.getResult().getPortraitUri()))
                        {
                            Log.e(TAG,"portraitUri is null");
                        }
                        String nickName = userInfoByIdResponse.getResult().getNickname();
                        String portraitUri = userInfoByIdResponse.getResult().getPortraitUri();
                        ConfigHelper.setLoginName(nickName);
                        ConfigHelper.setPortraitUri(portraitUri);
                        RongIM.getInstance().refreshUserInfoCache(new UserInfo(connectResultId, nickName, Uri.parse(portraitUri)));
                    }
                    //不继续在login界面同步好友,群组,群组成员信息
                    //TODO 获取好友信息
                    goToMain();
                    break;
                case GET_TOKEN:
                    GetTokenResponse tokenResponse = (GetTokenResponse) result;
                    if (tokenResponse.getCode() == HTTP_RESPONSE_OK)
                    {
                        String token = tokenResponse.getResult().getToken();
                        if (!TextUtils.isEmpty(token))
                        {
                            RongIM.connect(token, new RongIMClient.ConnectCallback()
                            {
                                @Override
                                public void onTokenIncorrect()
                                {
                                    Log.e(TAG, "reToken Incorrect");
                                }

                                @Override
                                public void onSuccess(String s)
                                {
                                    connectResultId = s;
                                    Log.e("connect", "onSuccess userid:" + s);
                                    ConfigHelper.setLoginId(s);
                                    request(SYNC_USER_INFO, true);
                                }

                                @Override
                                public void onError(RongIMClient.ErrorCode e)
                                {

                                }
                            });
                        }
                    }
                    break;
            }
        }
    }

    @Override
    public void onFailure(int requestCode, int state, Object result)
    {
        if (!CommonUtils.isNetworkConnected(context)) {
            LoadDialog.dismiss(context);
            ToastUtils.makeToast(getString(R.string.network_not_available));
            return;
        }
        switch (requestCode) {
            case LOGIN:
                LoadDialog.dismiss(context);
                ToastUtils.makeToast(R.string.login_api_fail);
                break;
            case SYNC_USER_INFO:
                LoadDialog.dismiss(context);
                ToastUtils.makeToast(R.string.sync_userinfo_api_fail);
                break;
            case GET_TOKEN:
                LoadDialog.dismiss(context);
                ToastUtils.makeToast(R.string.get_token_api_fail);
                break;
        }
    }

    private void reGetToken()
    {
        request(GET_TOKEN);
    }

    private void goToMain()
    {
        ConfigHelper.setLoginPhone(phoneString);
        ConfigHelper.setLoginPassword(passwordString);
        LoadDialog.dismiss(context);
        ToastUtils.makeToast(R.string.login_success);
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }
}

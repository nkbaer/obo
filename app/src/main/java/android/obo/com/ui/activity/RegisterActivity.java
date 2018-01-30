package android.obo.com.ui.activity;

import android.content.Intent;
import android.obo.com.obo.R;
import android.obo.com.server.response.CheckPhoneResponse;
import android.obo.com.server.response.RegisterResponse;
import android.obo.com.server.response.SendCodeResponse;
import android.obo.com.server.response.VerifyCodeResponse;
import android.obo.com.ui.widget.ClearWriteEditText;
import android.obo.com.ui.widget.LoadDialog;
import android.obo.com.utils.CommonUtils;
import android.obo.com.utils.ToastUtils;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by liuhfa on 2018/1/25.
 */

public class RegisterActivity extends BaseActivity implements View.OnClickListener
{
    private static final String TAG = "RegisterActivity";

    private static final int CHECK_PHONE = 1;
    private static final int SEND_CODE = 2;
    private static final int VERIFY_CODE = 3;
    private static final int REGISTER = 4;
    private static final int REGISTER_BACK = 1001;

    private ClearWriteEditText cwet_reg_username,cwet_reg_phone,cwet_reg_code,cwet_reg_password;
    private Button btn_reg_getcode,btn_reg;
    private TextView tv_reg_forget,tv_reg_login;

    private String mPhone, mCode, mNickName, mPassword, mCodeToken;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView()
    {
        cwet_reg_username = findViewById(R.id.cwet_reg_username);
        cwet_reg_phone = findViewById(R.id.cwet_reg_phone);
        cwet_reg_code = findViewById(R.id.cwet_reg_code);
        cwet_reg_password = findViewById(R.id.cwet_reg_password);
        btn_reg_getcode = findViewById(R.id.btn_reg_getcode);
        btn_reg = findViewById(R.id.btn_reg);
        tv_reg_forget = findViewById(R.id.tv_reg_forget);
        tv_reg_login = findViewById(R.id.tv_reg_login);

        btn_reg_getcode.setOnClickListener(this);
        btn_reg_getcode.setClickable(false);
        btn_reg.setOnClickListener(this);

        tv_reg_forget.setOnClickListener(this);
        tv_reg_login.setOnClickListener(this);

        addEditTextListener();
    }

    private void addEditTextListener()
    {
        cwet_reg_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 11) {
                    mPhone = s.toString().trim();
                    request(CHECK_PHONE, true);
                    CommonUtils.onInactive(context, cwet_reg_phone);
                } else {
                    btn_reg_getcode.setClickable(false);
                    btn_reg_getcode.setBackgroundDrawable(getResources().getDrawable(R.drawable.rs_select_btn_gray));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        cwet_reg_code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 6) {
                    CommonUtils.onInactive(context, cwet_reg_code);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        cwet_reg_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 5) {
                    btn_reg.setClickable(true);
                    btn_reg.setBackgroundDrawable(getResources().getDrawable(R.drawable.rs_select_btn_blue));
                } else {
                    btn_reg.setClickable(false);
                    btn_reg.setBackgroundDrawable(getResources().getDrawable(R.drawable.rs_select_btn_gray));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public Object doInBackground(int requestCode, String parameter) throws Exception
    {
        switch (requestCode) {
            case CHECK_PHONE:
                return action.checkPhoneAvailable("86", mPhone);
            case SEND_CODE:
                return action.sendCode("86", mPhone);
            case VERIFY_CODE:
                return action.verifyCode("86", mPhone, mCode);
            case REGISTER:
                return action.register(mNickName, mPassword, mCodeToken);
        }
        return super.doInBackground(requestCode, parameter);
    }

    @Override
    public void onSuccess(int requestCode, Object result)
    {
        if (result != null) {
            switch (requestCode) {
                case CHECK_PHONE:
                    CheckPhoneResponse cprres = (CheckPhoneResponse) result;
                    if (cprres.getCode() == HTTP_RESPONSE_OK) {
                        if (cprres.isResult()) {
                            btn_reg_getcode.setClickable(true);
                            btn_reg_getcode.setBackgroundDrawable(getResources().getDrawable(R.drawable.rs_select_btn_blue));
                            ToastUtils.makeToast(R.string.phone_number_available);
                        } else {
                            btn_reg_getcode.setClickable(false);
                            btn_reg_getcode.setBackgroundDrawable(getResources().getDrawable(R.drawable.rs_select_btn_gray));
                            ToastUtils.makeToast(R.string.phone_number_has_been_registered);
                        }
                    }
                    break;
                case SEND_CODE:
                    SendCodeResponse scrres = (SendCodeResponse) result;
                    if (scrres.getCode() == HTTP_RESPONSE_OK) {
                        ToastUtils.makeToast(R.string.messge_send);
                    } else if (scrres.getCode() == 5000) {
                        ToastUtils.makeToast(R.string.message_frequency);
                    }
                    break;

                case VERIFY_CODE:
                    VerifyCodeResponse vcres = (VerifyCodeResponse) result;
                    switch (vcres.getCode()) {
                        case HTTP_RESPONSE_OK:
                            mCodeToken = vcres.getResult().getVerification_token();
                            if (!TextUtils.isEmpty(mCodeToken)) {
                                request(REGISTER);
                            } else {
                                ToastUtils.makeToast("code token is null");
                                LoadDialog.dismiss(context);
                            }
                            break;
                        case 1000:
                            //验证码错误
                            ToastUtils.makeToast(R.string.verification_code_error);
                            LoadDialog.dismiss(context);
                            break;
                        case 2000:
                            //验证码过期
                            ToastUtils.makeToast(R.string.verification_code_timeout);
                            LoadDialog.dismiss(context);
                            break;
                    }
                    break;

                case REGISTER:
                    RegisterResponse rres = (RegisterResponse) result;
                    switch (rres.getCode()) {
                        case 200:
                            LoadDialog.dismiss(context);
                            ToastUtils.makeToast(R.string.register_success);
                            Intent data = new Intent();
                            data.putExtra("phone", mPhone);
                            data.putExtra("password", mPassword);
                            data.putExtra("nickname", mNickName);
                            data.putExtra("id", rres.getResult().getId());
                            setResult(REGISTER_BACK, data);
                            this.finish();
                            break;
                    }
                    break;
            }
        }
    }

    @Override
    public void onFailure(int requestCode, int state, Object result)
    {
        switch (requestCode) {
            case CHECK_PHONE:
                ToastUtils.makeToast(R.id.check_phone_fail);
                break;
            case SEND_CODE:
                ToastUtils.makeToast(R.id.request_code_fail);
                break;
            case VERIFY_CODE:
                LoadDialog.dismiss(context);
                ToastUtils.makeToast(R.id.verify_code_fail);
                break;
            case REGISTER:
                LoadDialog.dismiss(context);
                ToastUtils.makeToast(R.id.reg_request_fail);
                break;
        }
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId()) {
            case R.id.tv_reg_login:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.tv_reg_forget:
                startActivity(new Intent(this, ForgetPasswordActivity.class));
                break;
            case R.id.btn_reg_getcode:
                if (TextUtils.isEmpty(cwet_reg_phone.getText().toString().trim())) {
                    ToastUtils.makeToast(R.string.phone_number_is_null);
                } else {
                    request(SEND_CODE);
                }
                break;
            case R.id.btn_reg:
                mPhone = cwet_reg_phone.getText().toString().trim();
                mCode = cwet_reg_code.getText().toString().trim();
                mNickName = cwet_reg_username.getText().toString().trim();
                mPassword = cwet_reg_password.getText().toString().trim();

                if (TextUtils.isEmpty(mNickName)) {
                    ToastUtils.makeToast(R.string.name_is_null);
                    cwet_reg_username.setShakeAnimation();
                    return;
                }
                if (mNickName.contains(" ")) {
                    ToastUtils.makeToast(R.string.name_contain_spaces);
                    cwet_reg_username.setShakeAnimation();
                    return;
                }

                if (TextUtils.isEmpty(mPhone)) {
                    ToastUtils.makeToast(R.string.phone_number_is_null);
                    cwet_reg_phone.setShakeAnimation();
                    return;
                }
                if (TextUtils.isEmpty(mCode)) {
                    ToastUtils.makeToast(R.string.code_is_null);
                    cwet_reg_code.setShakeAnimation();
                    return;
                }
                if (TextUtils.isEmpty(mPassword)) {
                    ToastUtils.makeToast(R.string.password_is_null);
                    cwet_reg_password.setShakeAnimation();
                    return;
                }
                if (mPassword.contains(" ")) {
                    ToastUtils.makeToast(R.string.password_cannot_contain_spaces);
                    cwet_reg_password.setShakeAnimation();
                    return;
                }

                LoadDialog.show(context);
                request(VERIFY_CODE, true);
                break;
        }
    }
}

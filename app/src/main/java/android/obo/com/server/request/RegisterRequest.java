package android.obo.com.server.request;

import android.obo.com.utils.JsonManager;

/**
 * Created by liuhaifeng on 2018/2/3.
 */

public class RegisterRequest {
    public String loginname;
    public String password;
    public String mobilecode;

    public RegisterRequest(String loginname, String password, String mobilecode) {
        this.loginname = loginname;
        this.password = password;
        this.mobilecode = mobilecode;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobilecode() {
        return mobilecode;
    }

    public void setMobilecode(String mobilecode) {
        this.mobilecode = mobilecode;
    }

    public static void main(String[] args) throws Exception
    {
        RegisterRequest request = new RegisterRequest("15922092222","123456","123344");

        System.out.println(JsonManager.beanToJson(request));
    }
}

package android.obo.com.server.request;

import android.obo.com.utils.JsonManager;

/**
 * Created by liuhfa on 2018/1/30.
 */

public class LoginRequest
{
    private String loginname;
    private String password;

    public LoginRequest(String loginname, String password) {
        this.loginname = loginname;
        this.password = password;
    }

    public String getLoginname()
    {
        return loginname;
    }

    public void setLoginname(String loginname)
    {
        this.loginname = loginname;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public static void main(String[] args) throws Exception
    {
        LoginRequest request = new LoginRequest("12345","123456");

        System.out.println(JsonManager.beanToJson(request));
    }
}

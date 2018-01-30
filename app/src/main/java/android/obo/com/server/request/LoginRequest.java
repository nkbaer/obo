package android.obo.com.server.request;

import android.obo.com.utils.JsonManager;

/**
 * Created by liuhfa on 2018/1/30.
 */

public class LoginRequest
{
    private String phone;
    private String password;

    public LoginRequest(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
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

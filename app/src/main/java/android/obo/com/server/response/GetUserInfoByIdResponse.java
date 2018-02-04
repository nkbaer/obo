package android.obo.com.server.response;


/**
 * Created by liuhaifeng on 2018/1/27.
 */

public class GetUserInfoByIdResponse extends ServiceResult{
    private Account account;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}

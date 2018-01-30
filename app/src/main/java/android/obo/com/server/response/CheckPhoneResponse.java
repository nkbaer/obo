package android.obo.com.server.response;

/**
 * Created by liuhfa on 2018/1/30.
 */

public class CheckPhoneResponse
{
    private int code;

    private boolean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}

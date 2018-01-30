package android.obo.com.server.response;

/**
 * Created by liuhfa on 2018/1/30.
 */

public class VerifyCodeResponse
{
    private int code;

    private ResultEntity result;

    public void setCode(int code) {
        this.code = code;
    }

    public void setResult(ResultEntity result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public ResultEntity getResult() {
        return result;
    }

    public static class ResultEntity {
        private String verification_token;

        public void setVerification_token(String verification_token) {
            this.verification_token = verification_token;
        }

        public String getVerification_token() {
            return verification_token;
        }
    }
}

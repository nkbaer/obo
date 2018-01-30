package android.obo.com.server.response;

/**
 * Created by liuhaifeng on 2018/1/27.
 */

public class LoginResponse {

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
        private String id;
        private String token;

        public void setId(String id) {
            this.id = id;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getId() {
            return id;
        }

        public String getToken() {
            return token;
        }
    }
}

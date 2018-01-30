package android.obo.com.server.response;

/**
 * Created by liuhaifeng on 2018/1/27.
 */

public class GetUserInfoByIdResponse {

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
        private String nickname;
        private String portraitUri;

        public void setId(String id) {
            this.id = id;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public void setPortraitUri(String portraitUri) {
            this.portraitUri = portraitUri;
        }

        public String getId() {
            return id;
        }

        public String getNickname() {
            return nickname;
        }

        public String getPortraitUri() {
            return portraitUri;
        }
    }
}

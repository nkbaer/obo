package android.obo.com.server.response;

/**
 * Created by liuhfa on 2018/1/31.
 */

public class ServiceResult
{
    private int resultType = 0;
    private String desc = "";

    public int getResultType() {
        return resultType;
    }
    public void setResultType(int resultType) {
        this.resultType = resultType;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
}

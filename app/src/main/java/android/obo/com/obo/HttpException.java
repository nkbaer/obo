package android.obo.com.obo;

/**
 * Created by liuhfa on 2018/1/25.
 */

public class HttpException extends Exception
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 4010634120321127684L;

    public HttpException() {
        super();
    }

    public HttpException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public HttpException(String detailMessage) {
        super(detailMessage);
    }

    public HttpException(Throwable throwable) {
        super(throwable);
    }
}

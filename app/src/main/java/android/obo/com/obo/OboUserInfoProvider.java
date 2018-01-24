package android.obo.com.obo;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;

/**
 * Created by liuhfa on 2018/1/24.
 */

public class OboUserInfoProvider implements RongIM.UserInfoProvider
{
    @Override
    public UserInfo getUserInfo(String userId)
    {
        UserInfo info = new UserInfo(userId,"520",null);
        return info;
    }
}

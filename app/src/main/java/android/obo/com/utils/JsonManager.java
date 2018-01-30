package android.obo.com.utils;

import android.obo.com.server.response.LoginResponse;

import com.google.gson.Gson;

/**
 * Created by liuhfa on 2018/1/30.
 */

public class JsonManager
{
    private static Gson gson = new Gson();

    /**
     * 将json字符串转换成java对象
     *
     * @param json
     * @param cls
     * @return
     * @throws Exception
     */
    public static <T> T jsonToBean(String json, Class<T> cls) throws Exception
    {
        return gson.fromJson(json, cls);
    }

    /**
     * 将bean对象转化成json字符串
     *
     * @param obj
     * @return
     * @throws Exception
     */
    public static String beanToJson(Object obj) throws Exception
    {
        return gson.toJson(obj);
    }

    public static void main(String[] args) throws Exception
    {
        LoginResponse response = new LoginResponse();
        response.setCode(100);
        LoginResponse.ResultEntity entity = new LoginResponse.ResultEntity();
        entity.setId("123");
        entity.setToken("12431212");
        response.setResult(entity);
        String json = beanToJson(response);
        System.out.println(json);

        LoginResponse result = jsonToBean(json,LoginResponse.class);
        System.out.println(result.getCode()+","+result.getResult().getId()+","+result.getResult().getToken());
    }
}

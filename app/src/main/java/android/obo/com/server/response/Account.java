package android.obo.com.server.response;


/**
 * Created by liuhaifeng on 2018/2/4.
 */

public class Account {
    private int id;

    //登录信息
	/*
	 * 登录名，原则上应该是手机号
	 * */
    private String loginName;
    /*
     * 密码
     * */
    private String password;

    //统计及状态信息
	/*
	 * 跟当前好友最后交谈时间
	 * */
    private long lastChatTime;
    /*
     * 活跃等级
     * */
    private int activityLevel;
    /*
     * 当前好友ID
     * */
    private int currentFriendId;
    /*
     * 当前好友建立时间
     * */
    private long relationTime;

    //基本信息
	/*
	 * 出生日期
	 * */
    private String birthDate;
    /*
     * 昵称
     * */
    private String nickname;
    /*
     * 性别，0：未设置；1：男性；2：女性
     * */
    private int sex;
    /*
     * 自我介绍，500字以内
     * */
    private String slefIntro;

    /*
     * 城市ID
     * */
    private int cityId;
    /*
     * 头像URL
     * */
    private String headPicUrl;

    //交友偏好
	/*
	 * 交友类型，0：异性交友；1：同性交友；2：SM交友；
	 * */
    private int friendType;
    /*
     * 交友类型具体设置。
     * 当friendType==0时，0：严肃婚恋；1：其它；
     * 当friendType==1时，0：找攻；1：找受；
     * 当friendType==2时，0：找男S；1：找男M；2：找女S；3：找女M；
     * */
    private int friendTypeSetting;
    /*
     * 区域类型，0：不限；1：同城；
     * */
    private int areaType;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getLoginName() {
        return loginName;
    }
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public int getSex() {
        return sex;
    }
    public void setSex(int sex) {
        this.sex = sex;
    }
    public String getSlefIntro() {
        return slefIntro;
    }
    public void setSlefIntro(String slefIntro) {
        this.slefIntro = slefIntro;
    }
    public int getFriendType() {
        return friendType;
    }
    public void setFriendType(int friendType) {
        this.friendType = friendType;
    }
    public int getFriendTypeSetting() {
        return friendTypeSetting;
    }
    public void setFriendTypeSetting(int friendTypeSetting) {
        this.friendTypeSetting = friendTypeSetting;
    }
    public int getAreaType() {
        return areaType;
    }
    public void setAreaType(int areaType) {
        this.areaType = areaType;
    }
    public int getCityId() {
        return cityId;
    }
    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
    public String getHeadPicUrl() {
        return headPicUrl;
    }
    public void setHeadPicUrl(String headPicUrl) {
        this.headPicUrl = headPicUrl;
    }
    public long getLastChatTime() {
        return lastChatTime;
    }
    public void setLastChatTime(long lastChatTime) {
        this.lastChatTime = lastChatTime;
    }
    public int getActivityLevel() {
        return activityLevel;
    }
    public void setActivityLevel(int activityLevel) {
        this.activityLevel = activityLevel;
    }
    public int getCurrentFriendId() {
        return currentFriendId;
    }
    public void setCurrentFriendId(int currentFriendId) {
        this.currentFriendId = currentFriendId;
    }
    public long getRelationTime() {
        return relationTime;
    }
    public void setRelationTime(long relationTime) {
        this.relationTime = relationTime;
    }
}

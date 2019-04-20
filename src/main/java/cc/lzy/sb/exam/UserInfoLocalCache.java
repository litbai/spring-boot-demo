package cc.lzy.sb.exam;

/**
 * @author chengyu
 * @version 19/3/9
 */
public interface UserInfoLocalCache {
    void initUserInfoCache();

    UserInfo getUserInfoFromCacheById(String userId);

    void updateUserInfoCache(String userId);
}

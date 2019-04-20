package cc.lzy.sb.exam;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户缓存本地实现
 *
 * @author chengyu
 * @version 19/3/9
 */
public class SimpleUserInfoLocalCache implements UserInfoLocalCache {
    private int maxSize;
    private int expire;
    private int size;
    private Map<String, UserInfoCacheNode> userInfoMap = new HashMap<>();
    private AccessQueue<UserInfoCacheNode> accessQueue = new AccessQueue<>();

    private static final int DEFAULT_EXPIRE = 30 * 60;

    public SimpleUserInfoLocalCache(int maxSize) {
        this(maxSize, DEFAULT_EXPIRE);
    }

    public SimpleUserInfoLocalCache(int maxSize, int expire) {
        this.maxSize = maxSize;
        this.expire = expire;
    }


    public void initUserInfoCache() {
        List<UserInfo> userInfoList = queryAllFromDB();
        synchronized (this) {
            userInfoList.forEach(user -> doPut(user));
        }
    }

    public synchronized UserInfo getUserInfoFromCacheById(String userId) {
        if (userId == null) {
            return null;
        }

        UserInfoCacheNode cacheVal = userInfoMap.get(userId);
        if (cacheVal != null && !isExpire(cacheVal)) {
            accessQueue.access(cacheVal);
            return cacheVal.getValue();
        }

        updateUserInfoCache(userId);
        return getUserInfoFromCacheById(userId);
    }

    public void updateUserInfoCache(String userId) {
        UserInfo userInfo = queryFromDB(userId);
        doPut(userInfo);
    }

    private synchronized void doPut(UserInfo userInfo) {
        if (++size > maxSize) {
            evict();
        }
        UserInfoCacheNode newNode = UserInfoCacheNode.newNode(userInfo);
        userInfoMap.put(userInfo.getUserId(), newNode);
    }

    private void evict() {
        UserInfoCacheNode last = accessQueue.unlinkLast();
        userInfoMap.remove(last.getValue().getUserId());
    }

    private List<UserInfo> queryAllFromDB() {
        // 返回真实DB数据，由于本地内存容量有限，可以只加载热数据
        return Collections.emptyList();
    }

    private UserInfo queryFromDB(String userId) {
        return new UserInfo(userId, "", ""); // mock 数据
    }

    private boolean isExpire(UserInfoCacheNode cache) {
        return DateUtil.currentSeconds() - cache.getLastUpdateTime() > expire;
    }

}




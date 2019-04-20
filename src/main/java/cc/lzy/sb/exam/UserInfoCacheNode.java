package cc.lzy.sb.exam;

/**
 * 用户信息缓存
 *
 * @author chengyu
 * @version 19/3/9
 */

public class UserInfoCacheNode {
    private UserInfo value;
    private long lastAccessTime;
    private long lastUpdateTime;

    private UserInfoCacheNode prev;
    private UserInfoCacheNode next;

    private static final UserInfoCacheNode UN_SET = new UserInfoCacheNode();

    public static UserInfoCacheNode newNode(UserInfo value) {
        UserInfoCacheNode node = new UserInfoCacheNode();
        node.value = value;
        node.lastAccessTime = DateUtil.currentSeconds();
        node.prev = UN_SET;
        node.next = UN_SET;

        return node;
    }

    public UserInfo getValue() {
        return value;
    }

    public void setValue(UserInfo value) {
        this.value = value;
    }

    public long getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(long lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public UserInfoCacheNode getPrev() {
        return prev;
    }

    public void setPrev(UserInfoCacheNode prev) {
        this.prev = prev;
    }

    public UserInfoCacheNode getNext() {
        return next;
    }

    public void setNext(UserInfoCacheNode next) {
        this.next = next;
    }
}

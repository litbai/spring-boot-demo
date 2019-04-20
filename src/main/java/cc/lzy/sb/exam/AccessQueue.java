package cc.lzy.sb.exam;

import java.util.AbstractQueue;
import java.util.Iterator;

/**
 * 访问队列
 *
 * @author chengyu
 * @version 19/3/9
 */
public class AccessQueue<T> extends AbstractQueue<T> {
    private int size;
    private UserInfoCacheNode head;
    private UserInfoCacheNode tail;

    // 实现Deque...
    // ......

    // 将node放到链表头部
    public void access(UserInfoCacheNode node) {
        node.setLastAccessTime(DateUtil.currentSeconds());

        // ... 链接元素
    }

    // 去除队列尾部元素
    public UserInfoCacheNode unlinkLast() {
        // .. 去除最后一个元素
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean offer(T t) {
        return false;
    }

    @Override
    public T poll() {
        return null;
    }

    @Override
    public T peek() {
        return null;
    }
}

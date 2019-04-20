package cc.lzy.sb.exam;

/**
 * 用户信息
 *
 * @author chengyu
 * @version 19/3/9
 */
public class UserInfo {
    private String userId;
    private String userName;
    private String cardNo;

    public UserInfo() {
    }

    public UserInfo(String userId, String userName, String cardNo) {
        this.userId = userId;
        this.userName = userName;
        this.cardNo = cardNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
}

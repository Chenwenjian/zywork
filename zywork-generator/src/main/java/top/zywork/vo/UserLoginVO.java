package top.zywork.vo;

/**
 * 用户登录信息VO<br/>
 * 创建于2018-01-19
 *
 * @author 王振宇
 * @version 1.0
 */
public class UserLoginVO extends BaseVO {

    private static final long serialVersionUID = -8124738089207668538L;

    private String account;
    private String password;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

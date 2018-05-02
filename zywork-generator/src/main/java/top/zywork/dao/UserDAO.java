package top.zywork.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.zywork.query.PageQuery;
import top.zywork.query.UserAccountPasswordQuery;

import java.util.List;

/**
 * UserDAO数据访问接口<br/>
 *
 * 创建于2018-05-02<br/>
 *
 * @author http://zywork.top 王振宇
 * @version 1.0
 */
@Repository
public interface UserDAO extends BaseDAO {

    @Override
    List<Object> listPageByCondition(@Param("pager") PageQuery pageQuery, @Param("query") Object queryObj);

    @Override
    Long countByCondition(@Param("query") Object queryObj);


    /**
     * 根据用户账号及密码查询用户
     * @param userAccountPasswordQuery 用户账号及密码组成的查询对象，账号可以是邮箱，手机号，账户名
     * @return 指定账号及密码的用户对象
     */
    Object getByAccountPassword(UserAccountPasswordQuery userAccountPasswordQuery);

    /**
     * 根据用户名获取用户密码
     * @param username 用户名
     * @return 用户密码
     */
    String getPassword(String username);
}

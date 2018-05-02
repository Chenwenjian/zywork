package top.zywork.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.zywork.query.PageQuery;

import java.util.List;

/**
 * PermissionDAO数据访问接口<br/>
 *
 * 创建于2018-05-02<br/>
 *
 * @author http://zywork.top 王振宇
 * @version 1.0
 */
@Repository
public interface PermissionDAO extends BaseDAO {

    @Override
    List<Object> listPageByCondition(@Param("pager") PageQuery pageQuery, @Param("query") Object queryObj);

    @Override
    Long countByCondition(@Param("query") Object queryObj);

    /**
     * 根据模块id查找模块相关的权限
     * @param moduleId 模块编号
     * @return 指定模块下的所有权限
     */
    List<Object> listByModuleId(String moduleId);

    /**
     * 根据单个角色id查找角色相关的权限
     * @param roleId 角色编号
     * @return 指定角色下的所有权限
     */
    List<Object> listByRoleId(String roleId);

    /**
     * 根据多个角色id查找角色相关的权限
     * @param roleIds 角色编号列表
     * @return 多个角色下的所有权限，并剔除了重复的权限
     */
    List<Object> listByRoleIds(List<Long> roleIds);

    /**
     * 根据用户名查找用户相关的权限
     * @param account 用户名，可以是账户号，手机号，邮箱
     * @return 指定用户名下的所有权限，并剔除了重复的权限
     */
    List<Object> listByAccount(String account);
}

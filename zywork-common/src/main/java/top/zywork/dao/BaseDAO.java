package top.zywork.dao;


import top.zywork.query.PageQuery;
import top.zywork.query.StatusQuery;

import java.io.Serializable;
import java.util.List;

/**
 * DAO接口，定义了常用的DAO方法，实现CRUD操作<br />
 * 由Service调用DAO时，需要传递DO对象进来，并返回DO对象到Service<br />
 * 创建于2017-08-23
 *
 * @author 王振宇
 * @version 1.0
 */
public interface BaseDAO {

    /**
     * 添加数据到数据库中
     * @param dataObj DO实体类
     */
    void save(Object dataObj);

    /**
     * 根据DO实体类从数据库中删除数据
     * @param dataObj DO实体类
     */
    void remove(Object dataObj);

    /**
     * 根据主键从数据库中删除数据
     * @param id 主键ID
     */
    void removeById(Serializable id);

    /**
     * 根据对象更新数据库中的数据
     * @param dataObj DO实体类
     */
    void update(Object dataObj);

    /**
     * 根据StatusQuery查询对象更新is_active状态值
     * @param statusQuery 状态查询对象，包括id和status
     */
    void updateActiveStatus(StatusQuery statusQuery);

    /**
     * 根据主键id查找数据
     * @param id 主键字段值
     * @return 主键字段对应记录的DO对象
     */
    Object getById(Serializable id);

    /**
     * 查找所有记录数据
     * @return 所有记录数据DO对象组成的List列表
     */
    List<Object> listAll();

    /**
     * 根据PageQuery分页查询对象分页查找记录数据
     * @param pageQuery 分页查询对象
     * @return 分页数据DO对象所组成的List列表
     */
    List<Object> listPage(PageQuery pageQuery);

    /**
     * 返回记录数
     * @return 记录数
     */
    Long count();

    /**
     * 根据分页查询对象和条件查询对象查找数据
     * @param pageQuery 分页查询对象
     * @param queryObj 条件查询对象
     * @return 按照条件的分页数据DO对象所组成的List列表
     */
    List<Object> listPageByCondition(PageQuery pageQuery, Object queryObj);

    /**
     * 根据条件查询对象计数
     * @param queryObj 条件查询对象
     * @return 按照条件查询对象的记录数
     */
    Long countByCondition(Object queryObj);

    /**
     * 查找所有记录数据，多表关联
     * @return 所有记录数据DTO对象组成的List列表
     */
    List<Object> listAllDTO();

    /**
     * 根据PageQuery分页查询对象分页查找记录数据
     * @param pageQuery 分页查询对象
     * @return 分页数据DTO对象所组成的List列表
     */
    List<Object> listPageDTO(PageQuery pageQuery);

    /**
     * 返回记录数，多表关联
     * @return 记录数
     */
    Long countDTO();

    /**
     * 根据分页查询对象和条件查询对象查找数据，多表关联
     * @param pageQuery 分页查询对象
     * @param queryObj 条件查询对象
     * @return 按照条件的分页数据DTO对象所组成的List列表
     */
    List<Object> listPageDTOByCondition(PageQuery pageQuery, Object queryObj);

    /**
     * 根据条件查询对象计数，多表关联
     * @param queryObj 条件查询对象
     * @return 按照条件查询对象的记录数
     */
    Long countDTOByCondition(Object queryObj);
}

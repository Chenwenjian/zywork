package top.zywork.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import top.zywork.query.PageQuery;
import top.zywork.query.StatusQuery;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * BaseDAO接口的抽象实现类，所有DAO实现类中只需要再次实现额外的接口方法<br />
 * 同时需要把操作的实体类通过PostConstructor注解和init方法注入进来
 *
 * 创建于2017-12-02
 *
 * @author 王振宇
 * @version 1.0
 */
public abstract class AbstractBaseDAO extends HibernateDaoSupport implements BaseDAO {

    private Class<?> doClass;

    /**
     * 把sessionFactory组件注入到HibernateDaoSupport的sessionFactory属性
     * @param sessionFactory SessionFactory对象
     */
    @Resource(name = "sessionFactory")
    public void setAppSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public void save(Object dataObj) {
        getHibernateTemplate().save(dataObj);
    }

    @Override
    public void remove(Object dataObj) {
        getHibernateTemplate().delete(dataObj);
    }

    @Override
    public void removeById(Serializable id) {}

    @Override
    public void update(Object dataObj) {
        getHibernateTemplate().update(dataObj);
    }

    @Override
    public void updateActiveStatus(StatusQuery statusQuery) {

    }

    @Override
    public Object getById(Serializable id) {
        return getHibernateTemplate().get(doClass, id);
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public List<Object> listAll() {
        return (List<Object>) getHibernateTemplate().loadAll(doClass);
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public List<Object> listPage(PageQuery pageQuery) {
        return getHibernateTemplate().execute(new HibernateCallback<List<Object>>() {
            @Override
            public List<Object> doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery("from " + doClass.getSimpleName());
                query.setFirstResult(pageQuery.getBeginIndex());
                query.setMaxResults(pageQuery.getPageSize());
                return query.list();
            }
        });
    }

    @Override
    public Long count() {
        return getHibernateTemplate().execute(new HibernateCallback<Long>() {
            @Override
            public Long doInHibernate(Session session) throws HibernateException {
                return (Long) session.createQuery("select count(*) from " + doClass.getSimpleName()).uniqueResult();
            }
        });
    }

    @Override
    public List<Object> listPageByCondition(PageQuery pageQuery, Object queryObj) {
        return null;
    }

    @Override
    public Long countByCondition(Object queryObj) {
        return null;
    }

    public void init(Class<?> doClass) {
        this.doClass = doClass;
    }
}

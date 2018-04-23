package top.zywork.common;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.zywork.query.BaseQuery;
import top.zywork.query.PageQuery;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class HibernateUtils {

    private static final Logger logger = LoggerFactory.getLogger(HibernateUtils.class);

    @SuppressWarnings({"unchecked"})
    public static List<Object> listPageByCondition(Session session, Class<?> doClass, PageQuery pageQuery, BaseQuery queryObj) {
        CriteriaQuery criteriaQuery = buildRowsCriteriaQuery(session, doClass, queryObj);
        Query query = (Query) session.createQuery(criteriaQuery);
        query.setFirstResult(pageQuery.getBeginIndex());
        query.setMaxResults(pageQuery.getPageSize());
        return query.list();
    }

    public static Long countByCondition(Session session, Class<?> doClass, BaseQuery queryObj, String countField) {
        CriteriaQuery<Long> criteriaQuery = buildCountCriteriaQuery(session, doClass, queryObj, countField);
        return ((Query<Long>) session.createQuery(criteriaQuery)).uniqueResult();
    }

    public static Long countByCondition(Session session, Class<?> doClass, BaseQuery queryObj) {
        return countByCondition(session, doClass, queryObj, null);
    }

    public static <DO> CriteriaQuery<DO> buildRowsCriteriaQuery(Session session, Class<DO> doClass, BaseQuery queryObj) {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<DO> criteriaQuery = criteriaBuilder.createQuery(doClass);
        Root<DO> root = criteriaQuery.from(doClass);
        criteriaQuery = buildCriteriaQuery(criteriaQuery, criteriaBuilder, root, queryObj);
        return criteriaQuery;
    }

    public static <DO> CriteriaQuery<Long> buildCountCriteriaQuery(Session session, Class<DO> doClass, BaseQuery queryObj, String countField) {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<DO> root = criteriaQuery.from(doClass);
        if (countField == null) {
            countField = "id";
        }
        criteriaQuery.select(criteriaBuilder.count(root.get(countField)));
        return buildCriteriaQuery(criteriaQuery, criteriaBuilder, root, queryObj);
    }

    public static <DO> CriteriaQuery<Long> buildCountCriteriaQuery(Session session, Class<DO> doClass, BaseQuery queryObj) {
        return buildCountCriteriaQuery(session, doClass, queryObj, "id");
    }

    private static <DO> CriteriaQuery buildCriteriaQuery(CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder, Root<DO> root, BaseQuery queryObj) {
        if (queryObj != null) {
            Field[] fields = queryObj.getClass().getDeclaredFields();
            List<Predicate> predicateList = new ArrayList<>();
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                try {
                    Object value = field.get(queryObj);
                    if (value != null && value instanceof String) {
                        predicateList.add(criteriaBuilder.like(root.get(fieldName), "%" + value + "%"));
                    }
                } catch (IllegalAccessException e) {
                    logger.error(e.getMessage());
                }
            }
            if (predicateList.size() > 0) {
                criteriaQuery.where(criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()])));
            }
        }
        return criteriaQuery;
    }

    /**
     * 把data里面的数据封装到指定的t对象中，并且返回T对象所组成的数组
     * @param data
     * @param doClass
     * @param <DO>
     * @return
     */
    public static <DO> List<DO> buildBean(List<Object[]> data, String[] properties, Class<DO> doClass) {
        List<DO> list = new ArrayList<>();
        try {
            for (Object[] objects : data) {
                DO t = doClass.newInstance();
                for (int i = 0, len = objects.length; i < len; i++) {
                    String fieldName = properties[i];
                    Field field = doClass.getDeclaredField(fieldName);
                    field.setAccessible(true);
                    if (objects[i] instanceof BigInteger) {
                        field.set(t, ((BigInteger)objects[i]).longValue());
                    } else {
                        field.set(t, objects[i]);
                    }
                }
                list.add(t);
            }
        } catch (InstantiationException | IllegalAccessException | NoSuchFieldException e) {
            logger.error(e.getMessage());
        }
        return list;
    }

    /**
     * 传递进来的sql语句请加上where 1 = 1
     * @param sql
     * @param t
     * @param <T>
     * @return
     */
    public static <T> String buildCriteriaSQL(String sql, T t) {
        StringBuilder sb = new StringBuilder(sql);
        if (t != null) {
            Field[] fields = t.getClass().getDeclaredFields();
            try {
                for (Field field : fields) {
                    field.setAccessible(true);
                    String property = field.getName();
                    Object value = field.get(t);
                    if (value != null && value instanceof String) {
                        sb.append(" and ").append(PropertyUtils.propertyToColumn(property)).append(" like '%").append((String) value).append("%'");
                    }
                }
            } catch (IllegalAccessException e) {
                logger.error(e.getMessage());
            }
        }
        return sb.toString();
    }

}

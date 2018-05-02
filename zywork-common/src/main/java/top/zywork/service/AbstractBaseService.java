package top.zywork.service;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import top.zywork.common.DozerMapperUtils;
import top.zywork.common.ExceptionUtils;
import top.zywork.dao.BaseDAO;
import top.zywork.dto.PagerDTO;
import top.zywork.query.PageQuery;
import top.zywork.query.StatusQueries;
import top.zywork.query.StatusQuery;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * BaseService接口的抽象实现类，所有Service类中只需要再次实现一些额外定义的接口方法<br />
 * 在Service实现类中需要把具体的DAO通过Resource或Autowired注解和setBaseDAO方法注入进来，<br />
 * 同时需要把DO和DTO类通过PostConstruct注解和init方法注入进来<br />
 * 
 * 创建于2017-12-02
 *
 * @author 王振宇
 * @version 1.0
 */
public abstract class AbstractBaseService implements BaseService {

    private BaseDAO baseDAO;
    private Class<?> doClass;
    private Class<?> dtoClass;
    private Mapper beanMapper;

    @Override
    public void save(Object dataTransferObj) {
        try {
            baseDAO.save(beanMapper.map(dataTransferObj, doClass));
        } catch (RuntimeException e) {
            throw ExceptionUtils.serviceException(e);
        }
    }

    @Override
    public void remove(Object dataTransferObj) {
        try {
            baseDAO.remove(beanMapper.map(dataTransferObj, doClass));
        } catch (RuntimeException e) {
            throw ExceptionUtils.serviceException(e);
        }
    }

    @Override
    public void removeById(Serializable id) {
        try {
            baseDAO.removeById(id);
        } catch (RuntimeException e) {
            throw ExceptionUtils.serviceException(e);
        }
    }

    @Override
    public void removeByIds(Serializable[] ids) {
        try {
            baseDAO.removeByIds(ids);
        } catch (RuntimeException e) {
            throw ExceptionUtils.serviceException(e);
        }
    }

    @Override
    public void update(Object dataTransferObj) {
        try {
            baseDAO.update(beanMapper.map(dataTransferObj, doClass));
        } catch (RuntimeException e) {
            throw ExceptionUtils.serviceException(e);
        }
    }

    @Override
    public void updateActiveStatus(StatusQuery statusQuery) {
        try {
            baseDAO.updateActiveStatus(statusQuery);
        } catch (RuntimeException e) {
            throw ExceptionUtils.serviceException(e);
        }
    }

    @Override
    public void updateActiveStatuses(StatusQueries statusQueries) {
        try {
            baseDAO.updateActiveStatuses(statusQueries);
        } catch (RuntimeException e) {
            throw ExceptionUtils.serviceException(e);
        }
    }

    @Override
    public Object getById(Serializable id) {
        try {
            Object doObject = baseDAO.getById(id);
            Object dtoObject = null;
            if (doObject != null) {
                dtoObject = beanMapper.map(doObject, dtoClass);
            }
            return dtoObject;
        } catch (RuntimeException e) {
            throw ExceptionUtils.serviceException(e);
        }
    }

    @Override
    public List<Object> listAll() {
        try {
            List<Object> doObjList = baseDAO.listAll();
            List<Object> dtoObjList = new ArrayList<>();
            if (doObjList != null && doObjList.size() > 0) {
                dtoObjList = DozerMapperUtils.mapList(beanMapper, doObjList, dtoClass);
            }
            return dtoObjList;
        } catch (RuntimeException e) {
            throw ExceptionUtils.serviceException(e);
        }
    }

    @Override
    public PagerDTO listPage(PageQuery pageQuery) {
        PagerDTO pagerDTO = new PagerDTO(pageQuery.getPageNo(), pageQuery.getPageSize());
        try {
            Long count = baseDAO.count();
            pagerDTO.setTotal(count);
            if (count > 0) {
                List<Object> doObjList = baseDAO.listPage(pageQuery);
                pagerDTO.setRows(DozerMapperUtils.mapList(beanMapper, doObjList, dtoClass));
            } else {
                pagerDTO.setRows(new ArrayList<>());
            }
            return pagerDTO;
        } catch (RuntimeException e) {
            throw ExceptionUtils.serviceException(e);
        }
    }

    @Override
    public PagerDTO listPageByCondition(PageQuery pageQuery, Object queryObj) {
        PagerDTO pagerDTO = new PagerDTO(pageQuery.getPageNo(), pageQuery.getPageSize());
        try {
            Long count = baseDAO.countByCondition(queryObj);
            pagerDTO.setTotal(count);
            if (count > 0) {
                List<Object> doObjList = baseDAO.listPageByCondition(pageQuery, queryObj);
                pagerDTO.setRows(DozerMapperUtils.mapList(beanMapper, doObjList, dtoClass));
            } else {
                pagerDTO.setRows(new ArrayList<>());
            }
            return pagerDTO;
        } catch (RuntimeException e) {
            throw ExceptionUtils.serviceException(e);
        }
    }

    @Autowired
    public void setBeanMapper(Mapper beanMapper) {
        this.beanMapper = beanMapper;
    }

    public Mapper getBeanMapper() {
        return beanMapper;
    }

    public Class<?> getDtoClass() {
        return dtoClass;
    }

    public void setBaseDAO(BaseDAO baseDAO) {
        this.baseDAO = baseDAO;
    }

    public void init(Class<?> doClass, Class<?> dtoClass) {
        this.doClass = doClass;
        this.dtoClass = dtoClass;
    }
}

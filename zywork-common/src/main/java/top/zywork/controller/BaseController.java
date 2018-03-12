package top.zywork.controller;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 所有Controller类的父类<br/>
 *
 * 创建于2018-03-12<br/>
 *
 * @author 王振宇
 * @version 1.0
 */
public class BaseController {

    private Mapper beanMapper;

    @Autowired
    public void setBeanMapper(Mapper beanMapper) {
        this.beanMapper = beanMapper;
    }

    public Mapper getBeanMapper() {
        return beanMapper;
    }

}

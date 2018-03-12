package top.zywork.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zywork.dao.{beanName}DAO;
import top.zywork.dos.{beanName}DO;
import top.zywork.dto.{beanName}DTO;
import top.zywork.service.AbstractBaseService;
import top.zywork.service.{beanName}Service;

import javax.annotation.PostConstruct;

/**
 * {beanName}ServiceImpl服务接口实现类<br/>
 *
 * 创建于{createDate}<br/>
 *
 * @author {author}
 * @version 1.0
 */
@Service(value = "{beanNameLowerCase}Service")
public class {beanName}ServiceImpl extends AbstractBaseService implements {beanName}Service {

    private {beanName}DAO {beanNameLowerCase}DAO;

    @Autowired
    public void set{beanName}DAO({beanName}DAO {beanNameLowerCase}DAO) {
        super.setBaseDAO({beanNameLowerCase}DAO);
        this.{beanNameLowerCase}DAO = {beanNameLowerCase}DAO;
    }

    @PostConstruct
    public void init() {
        super.init({beanName}DO.class, {beanName}DTO.class);
    }
}

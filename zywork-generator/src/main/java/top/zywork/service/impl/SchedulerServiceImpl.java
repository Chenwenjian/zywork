package top.zywork.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zywork.dao.SchedulerDAO;
import top.zywork.dos.SchedulerDO;
import top.zywork.dto.SchedulerDTO;
import top.zywork.service.AbstractBaseService;
import top.zywork.service.SchedulerService;

import javax.annotation.PostConstruct;

/**
 * SchedulerServiceImpl服务接口实现类<br/>
 *
 * 创建于2018-05-02<br/>
 *
 * @author http://zywork.top 王振宇
 * @version 1.0
 */
@Service(value = "schedulerService")
public class SchedulerServiceImpl extends AbstractBaseService implements SchedulerService {

    private SchedulerDAO schedulerDAO;

    @Autowired
    public void setSchedulerDAO(SchedulerDAO schedulerDAO) {
        super.setBaseDAO(schedulerDAO);
        this.schedulerDAO = schedulerDAO;
    }

    @PostConstruct
    public void init() {
        super.init(SchedulerDO.class, SchedulerDTO.class);
    }
}

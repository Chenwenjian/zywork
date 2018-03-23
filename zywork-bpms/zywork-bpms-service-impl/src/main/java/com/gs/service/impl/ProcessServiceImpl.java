package com.gs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zywork.dao.ProcessDAO;
import top.zywork.dos.ProcessDO;
import top.zywork.dto.ProcessDTO;
import top.zywork.query.ProcessDeployQuery;
import top.zywork.service.AbstractBaseService;
import top.zywork.service.ProcessService;

import javax.annotation.PostConstruct;

/**
 * 流程引擎Service服务实现类<br />
 * 创建于2017-10-15
 *
 * @author 王振宇
 * @version 1.0
 */
@Service
public class ProcessServiceImpl extends AbstractBaseService implements ProcessService {

    private ProcessDAO processDAO;

    @Override
    public ProcessDTO getByName(String name) {
        ProcessDO processDO = processDAO.getByName(name);
        return processDO == null ? null : getBeanMapper().map(processDO, ProcessDTO.class);
    }

    @Override
    public void updateDeploy(ProcessDeployQuery processDeployQuery) {
        ProcessDO processDO = processDAO.getByName(processDeployQuery.getProcessName());
        processDeployQuery.setProcessId(processDO.getId());
        processDAO.updateDeploy(processDeployQuery);
    }

    @Autowired
    public void setProcessDAO(ProcessDAO processDAO) {
        super.setBaseDAO(processDAO);
        this.processDAO = processDAO;
    }

    @PostConstruct
    public void init() {
        super.init(ProcessDO.class, ProcessDTO.class);
    }
}

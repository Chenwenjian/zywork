package top.zywork.controller;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.zywork.common.DozerMapperUtils;
import top.zywork.common.StringUtils;
import top.zywork.dto.PagerDTO;
import top.zywork.dto.{beanName}DTO;
import top.zywork.exception.ServiceException;
import top.zywork.query.PageQuery;
import top.zywork.query.StatusQuery;
import top.zywork.query.{beanName}Query;
import top.zywork.service.{beanName}Service;
import top.zywork.vo.ControllerStatusVO;
import top.zywork.vo.PagerVO;
import top.zywork.vo.{beanName}VO;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * {beanName}Controller控制器类<br/>
 *
 * 创建于{createDate}<br/>
 *
 * @author {author}
 * @version 1.0
 */
@Controller
@RequestMapping("/{moduleName}")
public class {beanName}Controller extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger({beanName}Controller.class);

    private {beanName}Service {beanNameLowerCase}Service;

    @RequestMapping("one/{id}")
    @ResponseBody
    public {beanName}VO getById(@PathVariable("id") Long id) {
        {beanName}VO {beanNameLowerCase}VO = new {beanName}VO();
        try {
            Object obj = {beanNameLowerCase}Service.getById(id);
            if (obj != null) {
                {beanNameLowerCase}VO = getBeanMapper().map(obj, {beanName}VO.class);
            }
        } catch (ServiceException e) {
            logger.error("返回单个对象JSON数据失败：{}", e.getMessage());
        }
        return {beanNameLowerCase}VO;
    }

    @GetMapping("all")
    @ResponseBody
    public List<{beanName}VO> listAll() {
        List<{beanName}VO> {beanNameLowerCase}VOList = new ArrayList<>();
        try {
            List<Object> objectList = {beanNameLowerCase}Service.listAll();
            {beanNameLowerCase}VOList =  DozerMapperUtils.map(getBeanMapper(), objectList, {beanName}VO.class);
        } catch (ServiceException e) {
            logger.error("返回所有对象JSON数据失败：{}", e.getMessage());
        }
        return {beanNameLowerCase}VOList;
    }

    @PostMapping("page")
    @ResponseBody
    public PagerVO listPage(PageQuery pageQuery) {
        PagerVO pagerVO = new PagerVO();
        try {
            PagerDTO pagerDTO = {beanNameLowerCase}Service.listPage(pageQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), {beanName}VO.class));
        } catch (ServiceException e) {
            logger.error("返回分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    @PostMapping("page-cond")
    @ResponseBody
    public PagerVO listPageByCondition(PageQuery pageQuery, {beanName}Query {beanNameLowerCase}Query) {
        PagerVO pagerVO = new PagerVO();
        try {
            PagerDTO pagerDTO = {beanNameLowerCase}Service.listPageByCondition(pageQuery, {beanNameLowerCase}Query);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), {beanName}VO.class));
        } catch (ServiceException e) {
            logger.error("返回指定条件的分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    @Resource
    public void set{beanName}Service({beanName}Service {beanNameLowerCase}Service) {
        this.{beanNameLowerCase}Service = {beanNameLowerCase}Service;
    }
}

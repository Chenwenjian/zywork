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

    @GetMapping("page")
    public String page() {
        return "{beanName}/{beanName}";
    }

    @GetMapping("add-modal")
    public String addModal() {
        return "{beanName}/{addModalPage}";
    }

    @GetMapping("edit-modal")
    public String editModal() {
        return "{beanName}/{editModalPage}";
    }

    @GetMapping("search-modal")
    public String searchModal() {
        return "{beanName}/{searchModalPage}";
    }

    @PostMapping("save")
    @ResponseBody
    public ControllerStatusVO save({beanName}VO {beanNameLowerCase}VO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            {beanNameLowerCase}Service.save(getBeanMapper().map({beanNameLowerCase}VO, {beanName}DTO.class));
            statusVO.okStatus(200, "添加成功");
        } catch (ServiceException e) {
            logger.error("添加失败：{}", e.getMessage());
            statusVO.errorStatus(500, "添加失败");
        }
        return statusVO;
    }

    @PostMapping("remove")
    @ResponseBody
    public ControllerStatusVO remove({beanName}VO {beanNameLowerCase}VO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            {beanNameLowerCase}Service.remove(getBeanMapper().map({beanNameLowerCase}VO, {beanName}DTO.class));
            statusVO.okStatus(200, "删除成功");
        } catch (ServiceException e) {
            logger.error("删除失败：{}", e.getMessage());
            statusVO.errorStatus(500, "删除失败");
        }
        return statusVO;
    }

    @GetMapping("remove/{id}")
    @ResponseBody
    public ControllerStatusVO removeById(@PathVariable("id") Long id) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            {beanNameLowerCase}Service.removeById(id);
            statusVO.okStatus(200, "删除成功");
        } catch (ServiceException e) {
            logger.error("删除失败：{}", e.getMessage());
            statusVO.errorStatus(500, "删除失败");
        }
        return statusVO;
    }

    @PostMapping("batch-remove")
    @ResponseBody
    public ControllerStatusVO removeByIds(String ids) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            {beanNameLowerCase}Service.removeByIds(StringUtils.strToLongArray(ids, ","));
            statusVO.okStatus(200, "批量删除成功");
        } catch (ServiceException e) {
            logger.error("批量删除失败：{}", e.getMessage());
            statusVO.errorStatus(500, "批量删除失败");
        }
        return statusVO;
    }

    @PostMapping("update")
    @ResponseBody
    public ControllerStatusVO update({beanName}VO {beanNameLowerCase}VO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            {beanNameLowerCase}Service.update(getBeanMapper().map({beanNameLowerCase}VO, {beanName}DTO.class));
            statusVO.okStatus(200, "更新成功");
        } catch (ServiceException e) {
            logger.error("更新失败：{}", e.getMessage());
            statusVO.errorStatus(500, "更新失败");
        }
        return statusVO;
    }

    @PostMapping("active")
    @ResponseBody
    public ControllerStatusVO updateActiveStatus(StatusQuery statusQuery) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            {beanNameLowerCase}Service.updateActiveStatus(statusQuery);
            statusVO.okStatus(200, statusQuery.getStatus() == 0 ? "激活成功" : "冻结成功");
        } catch (ServiceException e) {
            logger.error("激活或冻结失败：{}", e.getMessage());
            statusVO.errorStatus(500, statusQuery.getStatus() == 0 ? "激活失败" : "冻结失败");
        }
        return statusVO;
    }

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

    @RequestMapping("pager")
    @ResponseBody
    public PagerVO listPage(int offset, int limit, PageQuery pageQuery) {
        PagerVO pagerVO = new PagerVO();
        pageQuery.setPageNo(offset / limit + 1);
        pageQuery.setPageSize(limit);
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

    @RequestMapping("pager-cond")
    @ResponseBody
    public PagerVO listPageByCondition(int offset, int limit, PageQuery pageQuery, {beanName}Query {beanNameLowerCase}Query) {
        PagerVO pagerVO = new PagerVO();
        pageQuery.setPageNo(offset / limit + 1);
        pageQuery.setPageSize(limit);
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

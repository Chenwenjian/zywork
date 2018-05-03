package top.zywork.generator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.zywork.generator.bean.ColumnDetail;
import top.zywork.generator.bean.JDBC;
import top.zywork.generator.bean.Select2;
import top.zywork.generator.bean.TableColumns;
import top.zywork.generator.common.JDBCUtils;
import top.zywork.vo.PagerVO;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取表及表字段信息的控制器<br/>
 *
 * 创建于2018-03-22<br/>
 *
 * @author 王振宇
 * @version 1.0
 */
@Controller
@RequestMapping("/generator/table")
public class TableController {

    @GetMapping("all")
    @ResponseBody
    public List<Select2> allTables(HttpServletRequest request) {
        ServletContext servletContext = request.getServletContext();
        List<TableColumns> tableColumnsList;
        Object obj = servletContext.getAttribute("tableColumnsList");
        if (obj == null) {
            JDBC jdbc = (JDBC) servletContext.getAttribute("jdbc");
            JDBCUtils jdbcUtils = new JDBCUtils();
            jdbcUtils.connect(jdbc.getDriverClassName(), jdbc.getUrl(), jdbc.getUsername(), jdbc.getPassword());
            tableColumnsList = jdbcUtils.getTableColumns();
            servletContext.setAttribute("tableColumnsList", tableColumnsList);
        } else {
            tableColumnsList = (List<TableColumns>) obj;
        }
        List<Select2> select2List = new ArrayList<>();
        for (TableColumns tableColumns : tableColumnsList) {
            select2List.add(new Select2(tableColumns.getTableName(), tableColumns.getTableName(), false));
        }
        return select2List;
    }

    @GetMapping("columns/{tableName}")
    @ResponseBody
    public PagerVO tableColumns(@PathVariable("tableName") String tableName, HttpServletRequest request) {
        ServletContext servletContext = request.getServletContext();
        Object obj = servletContext.getAttribute("tableColumnsList");
        List<TableColumns> tableColumnsList = null;
        if (obj != null) {
            tableColumnsList = (List<TableColumns>) obj;
        }
        for (TableColumns tableColumns : tableColumnsList) {
            if (tableName.equals(tableColumns.getTableName())) {
                List<ColumnDetail> columnDetailList = tableColumns.getColumns();
                PagerVO pagerVO = new PagerVO(1, 1);
                pagerVO.setRows((List) columnDetailList);
                pagerVO.setTotal((long) columnDetailList.size());
                return pagerVO;
            }
        }
        return null;
    }

    @GetMapping("column-details/{tableName}")
    @ResponseBody
    public List<ColumnDetail> columnDetails(@PathVariable("tableName") String tableName, HttpServletRequest request) {
        ServletContext servletContext = request.getServletContext();
        Object obj = servletContext.getAttribute("tableColumnsList");
        List<TableColumns> tableColumnsList = null;
        if (obj != null) {
            tableColumnsList = (List<TableColumns>) obj;
        }
        for (TableColumns tableColumns : tableColumnsList) {
            if (tableName.equals(tableColumns.getTableName())) {
                return tableColumns.getColumns();
            }
        }
        return null;
    }


}

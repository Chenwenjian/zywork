package top.zywork.common;

import top.zywork.bean.TableColumns;

import java.util.List;

/**
 * 实体类，DAO, Mapper, Service, Controller全部代码自动生成封装类<br/>
 *
 * 创建于2018-03-12<br/>
 *
 * @author 王振宇
 * @version 1.0
 */
public class CodeGenerator {

    /**
     * 根据指定的数据库生成所有表对应的DO, DTO, VO, Query, DAO, Mapper映射，Service和Controller
     * @param driver 驱动程序
     * @param url url
     * @param username 用户名
     * @param password 密码
     */
    public static void generateCodes(String driver, String url, String username, String password) {
        JDBCUtils jdbcUtils = new JDBCUtils();
        jdbcUtils.connect(driver, url, username, password);
        List<TableColumns> tableColumnsList = jdbcUtils.getTableColumns();
        BeanGenerator.generateDOs(tableColumnsList);
        BeanGenerator.generateDTOs(tableColumnsList);
        BeanGenerator.generateVOs(tableColumnsList);
        BeanGenerator.generateQuerys(tableColumnsList);

        DAOGenerator.generateDAOs(tableColumnsList);
        MapperGenerator.generateMappers(tableColumnsList);

        ServiceGenerator.generateServices(tableColumnsList);
        ServiceGenerator.generateServiceImpls(tableColumnsList);

        ControllerGenerator.generateControllers(tableColumnsList);
    }

}

package top.zywork.generator.generator;

import top.zywork.generator.bean.Generator;
import top.zywork.generator.bean.JDBC;
import top.zywork.generator.bean.TableColumns;
import top.zywork.generator.common.JDBCUtils;

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
     * @param jdbc JDBC封装类
     * @param generator generator封装类
     */
    public static void generateCodes(JDBC jdbc, Generator generator) {
        JDBCUtils jdbcUtils = new JDBCUtils();
        jdbcUtils.connect(jdbc.getDriverClassName(), jdbc.getUrl(), jdbc.getUsername(), jdbc.getPassword());
        List<TableColumns> tableColumnsList = jdbcUtils.getTableColumns();
        generateCodes(generator, tableColumnsList);
    }

    /**
     * 根据Generator和所有表字段信息生成所有单表的代码
     * @param generator Generator实例
     * @param tableColumnsList 所有表字段信息列表
     */
    public static void generateCodes(Generator generator, List<TableColumns> tableColumnsList) {
        BeanGenerator.generateDOs(generator, tableColumnsList);
        BeanGenerator.generateDTOs(generator, tableColumnsList);
        BeanGenerator.generateVOs(generator, tableColumnsList);
        BeanGenerator.generateQuerys(generator, tableColumnsList);

        DAOGenerator.generateDAOs(generator, tableColumnsList);
        MapperGenerator.generateMappers(generator, tableColumnsList);

        ServiceGenerator.generateServices(generator, tableColumnsList);
        ServiceGenerator.generateServiceImpls(generator, tableColumnsList);

        ControllerGenerator.generateControllers(generator, tableColumnsList);

        ViewGenerator.generateViews(generator, tableColumnsList);
        ViewGenerator.generateJss(generator, tableColumnsList);
    }

    /**
     * 根据Generator和单表的字段信息生成指定的一张表的代码
     * @param generator Generator实例
     * @param tableColumns 单个表的字段信息
     */
    public static void generateCode(Generator generator, TableColumns tableColumns) {
        BeanGenerator.generateDO(generator, tableColumns);
        BeanGenerator.generateDTO(generator, tableColumns);
        BeanGenerator.generateVO(generator, tableColumns);
        BeanGenerator.generateQuery(generator, tableColumns);

        DAOGenerator.generateDAO(generator, tableColumns);
        MapperGenerator.generateMapper(generator, tableColumns);

        ServiceGenerator.generateService(generator, tableColumns);
        ServiceGenerator.generateServiceImpl(generator, tableColumns);

        ControllerGenerator.generateController(generator, tableColumns);

        ViewGenerator.generateView(generator, tableColumns);
        ViewGenerator.generateJs(generator, tableColumns);
    }

    /**
     * 生成关联表的代码
     * @param beanName bean名称
     * @param mappingUrl 控制器映射url
     * @param jdbc JDBC实例
     * @param generator Generator实例
     * @param primaryTable 主表名称
     * @param columns 所选表字段
     * @param joinWhereClause 关联条件
     */
    public static void generateJoinCodes(String beanName, String mappingUrl, JDBC jdbc,
                                         Generator generator, String primaryTable, String[] columns, String joinWhereClause) {
        JDBCUtils jdbcUtils = new JDBCUtils();
        jdbcUtils.connect(jdbc.getDriverClassName(), jdbc.getUrl(), jdbc.getUsername(), jdbc.getPassword());
        List<TableColumns> tableColumnsList = jdbcUtils.getTableColumns();
        BeanGenerator.generateJoinDO(beanName, generator, columns, tableColumnsList);
        BeanGenerator.generateJoinDTO(beanName, generator, columns, tableColumnsList);
        BeanGenerator.generateJoinVO(beanName, generator, columns, tableColumnsList);
        BeanGenerator.generateJoinQuery(beanName, generator, columns, tableColumnsList);

        DAOGenerator.generateJoinDAO(beanName, generator);
        MapperGenerator.generateJoinMapper(beanName, generator, primaryTable, columns, joinWhereClause);

        ServiceGenerator.generateJoinService(beanName, generator);
        ServiceGenerator.generateJoinServiceImpl(beanName, generator);

        ControllerGenerator.generateJoinController(beanName, mappingUrl, generator);
    }

    /**
     * 生成关联表的代码
     * @param beanName bean名称
     * @param mappingUrl 控制器映射url
     * @param generator Generator实例
     * @param primaryTable 主表名称
     * @param columns 所选表字段
     * @param joinWhereClause 关联条件
     */
    public static void generateJoinCodes(String beanName, String mappingUrl, Generator generator, String primaryTable,
                                         String[] columns, List<TableColumns> tableColumnsList, String joinWhereClause) {
        BeanGenerator.generateJoinDO(beanName, generator, columns, tableColumnsList);
        BeanGenerator.generateJoinDTO(beanName, generator, columns, tableColumnsList);
        BeanGenerator.generateJoinVO(beanName, generator, columns, tableColumnsList);
        BeanGenerator.generateJoinQuery(beanName, generator, columns, tableColumnsList);

        DAOGenerator.generateJoinDAO(beanName, generator);
        MapperGenerator.generateJoinMapper(beanName, generator, primaryTable, columns, joinWhereClause);

        ServiceGenerator.generateJoinService(beanName, generator);
        ServiceGenerator.generateJoinServiceImpl(beanName, generator);

        ControllerGenerator.generateJoinController(beanName, mappingUrl, generator);
    }

}

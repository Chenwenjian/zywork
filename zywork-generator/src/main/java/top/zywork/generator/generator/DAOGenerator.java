package top.zywork.generator.generator;

import top.zywork.common.DateFormatUtils;
import top.zywork.enums.DatePatternEnum;
import top.zywork.generator.bean.Generator;
import top.zywork.generator.bean.TableColumns;
import top.zywork.generator.common.GeneratorUtils;
import top.zywork.generator.constant.TemplateConstants;

import java.util.Calendar;
import java.util.List;

/**
 * DAO自动生成代码封装类<br/>
 *
 * 创建于2018-03-12<br/>
 *
 * @author 王振宇
 * @version 1.0
 */
public class DAOGenerator {

    /**
     * 生成DAO接口
     * @param generator
     * @param tableColumns 表数据
     */
    public static void generateDAO(Generator generator, TableColumns tableColumns) {
        String beanName = GeneratorUtils.tableNameToClassName(tableColumns.getTableName(), generator.getTablePrefix());
        generateJoinDAO(beanName, generator);
    }

    /**
     * 生成关联表的DAO接口
     * @param beanName bean名称
     * @param generator Generator实例
     */
    public static void generateJoinDAO(String beanName, Generator generator) {
        String packagePath = GeneratorUtils.createPackage(generator, generator.getDaoPackage());
        String fileContent = GeneratorUtils.readTemplate(generator, TemplateConstants.DAO_TEMPLATE);
        fileContent = fileContent.replace(TemplateConstants.CREATE_DATE,
                DateFormatUtils.format(Calendar.getInstance(), DatePatternEnum.DATE.getValue()))
                .replace(TemplateConstants.AUTHOR, generator.getAuthor())
                .replace(TemplateConstants.BEAN_NAME, beanName);
        GeneratorUtils.writeFile(fileContent, packagePath, beanName + generator.getDaoSuffix());
    }

    /**
     * 生成所有DAO接口
     * @param generator
     * @param tableColumnsList 所有表数据
     */
    public static void generateDAOs(Generator generator, List<TableColumns> tableColumnsList) {
        for (TableColumns tableColumns : tableColumnsList) {
            generateDAO(generator, tableColumns);
        }
    }

}

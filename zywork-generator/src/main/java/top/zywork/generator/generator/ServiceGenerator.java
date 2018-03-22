package top.zywork.generator.generator;

import org.apache.commons.lang3.StringUtils;
import top.zywork.generator.bean.Generator;
import top.zywork.generator.bean.TableColumns;
import top.zywork.generator.common.GeneratorUtils;
import top.zywork.generator.constant.TemplateConstants;
import top.zywork.common.DateFormatUtils;
import top.zywork.enums.DatePatternEnum;

import java.util.Calendar;
import java.util.List;

/**
 * Service自动生成代码封装类<br/>
 *
 * 创建于2018-03-12<br/>
 *
 * @author 王振宇
 * @version 1.0
 */
public class ServiceGenerator {

    /**
     * 生成Service接口
     * @param generator
     * @param tableColumns 表数据
     */
    public static void generateService(Generator generator, TableColumns tableColumns) {
        String beanName = GeneratorUtils.tableNameToClassName(tableColumns.getTableName(), generator.getTablePrefix());
        generateJoinService(beanName, generator);
    }

    public static void generateJoinService(String beanName, Generator generator) {
        String packagePath = GeneratorUtils.createPackage(generator, generator.getServicePackage());
        String fileContent = GeneratorUtils.readTemplate(generator, TemplateConstants.SERVICE_TEMPLATE);
        fileContent = fileContent.replace(TemplateConstants.CREATE_DATE,
                DateFormatUtils.format(Calendar.getInstance(), DatePatternEnum.DATE.getValue()))
                .replace(TemplateConstants.AUTHOR, generator.getAuthor())
                .replace(TemplateConstants.BEAN_NAME, beanName);
        GeneratorUtils.writeFile(fileContent, packagePath, beanName + generator.getServiceSuffix());
    }

    /**
     * 生成所有Service接口
     * @param generator
     * @param tableColumnsList 所有表数据
     */
    public static void generateServices(Generator generator, List<TableColumns> tableColumnsList) {
        for (TableColumns tableColumns : tableColumnsList) {
            generateService(generator, tableColumns);
        }
    }

    /**
     * 生成Service接口实现类
     * @param generator
     * @param tableColumns 表数据
     */
    public static void generateServiceImpl(Generator generator, TableColumns tableColumns) {
        String beanName = GeneratorUtils.tableNameToClassName(tableColumns.getTableName(), generator.getTablePrefix());
        generateJoinServiceImpl(beanName, generator);
    }

    public static void generateJoinServiceImpl(String beanName, Generator generator) {
        String packagePath = GeneratorUtils.createPackage(generator, generator.getServiceImplPackage());
        String fileContent = GeneratorUtils.readTemplate(generator, TemplateConstants.SERVICE_IMPL_TEMPLATE);
        fileContent = fileContent.replace(TemplateConstants.CREATE_DATE,
                DateFormatUtils.format(Calendar.getInstance(), DatePatternEnum.DATE.getValue()))
                .replace(TemplateConstants.AUTHOR, generator.getAuthor())
                .replace(TemplateConstants.BEAN_NAME, beanName)
                .replace(TemplateConstants.BEAN_NAME_LOWER_CASE, StringUtils.uncapitalize(beanName));
        GeneratorUtils.writeFile(fileContent, packagePath, beanName + generator.getServiceImplSuffix());
    }

    /**
     * 生成所有Service接口实现类
     * @param generator
     * @param tableColumnsList 所有实现类
     */
    public static void generateServiceImpls(Generator generator, List<TableColumns> tableColumnsList) {
        for (TableColumns tableColumns : tableColumnsList) {
            generateServiceImpl(generator, tableColumns);
        }
    }

}

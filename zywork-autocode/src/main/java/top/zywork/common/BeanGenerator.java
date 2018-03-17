package top.zywork.common;

import top.zywork.bean.ColumnDetail;
import top.zywork.bean.TableColumns;
import top.zywork.constant.GeneratorConstants;
import top.zywork.constant.TemplateConstants;
import top.zywork.enums.DatePatternEnum;

import java.util.Calendar;
import java.util.List;

/**
 * 实体类自动生成封装类<br/>
 *
 * 创建于2018-03-12<br/>
 *
 * @author 王振宇
 * @version 1.0
 */
public class BeanGenerator {

    /**
     * 生成表对应的DO实体类
     * @param tableColumns 表数据
     */
    public static void generateDO(TableColumns tableColumns) {
        String beanName = GeneratorUtils.tableNameToClassName(tableColumns.getTableName(), GeneratorConstants.TABLE_PREFIX);
        String packagePath = GeneratorUtils.createPackage(GeneratorConstants.DO_PACKAGE);
        String fileContent = GeneratorUtils.readTemplate(GeneratorConstants.DO_TEMPLATE);
        fileContent = fileContent.replace(TemplateConstants.CREATE_DATE,
                DateFormatUtils.format(Calendar.getInstance(), DatePatternEnum.DATE.getValue()))
                .replace(TemplateConstants.AUTHOR, GeneratorConstants.AUTHOR)
                .replace(TemplateConstants.BEAN_NAME, beanName)
                .replace(TemplateConstants.SERIAL_VERSION_ID, GeneratorUtils.generateSerialVersionId() + "");
        fileContent = generateFields(fileContent, tableColumns);
        fileContent = generatorConstructorParams(fileContent, tableColumns);
        fileContent = generatorConstructor(fileContent, tableColumns);
        fileContent = generateGetterSetters(fileContent, tableColumns);
        fileContent = generateToString(fileContent, tableColumns);
        GeneratorUtils.writeFile(fileContent, packagePath, beanName + GeneratorConstants.DO_JAVA_SUFFIX);
    }

    /**
     * 生成所有表数据对应的DO实体类
     * @param tableColumnsList 所有表数据
     */
    public static void generateDOs(List<TableColumns> tableColumnsList) {
        for (TableColumns tableColumns : tableColumnsList) {
            generateDO(tableColumns);
        }
    }

    /**
     * 生成DTO传输对象类
     * @param tableColumns 表数据
     */
    public static void generateDTO(TableColumns tableColumns) {
        String beanName = GeneratorUtils.tableNameToClassName(tableColumns.getTableName(), GeneratorConstants.TABLE_PREFIX);
        String packagePath = GeneratorUtils.createPackage(GeneratorConstants.DTO_PACKAGE);
        String fileContent = GeneratorUtils.readTemplate(GeneratorConstants.DTO_TEMPLATE);
        fileContent = fileContent.replace(TemplateConstants.CREATE_DATE,
                DateFormatUtils.format(Calendar.getInstance(), DatePatternEnum.DATE.getValue()))
                .replace(TemplateConstants.AUTHOR, GeneratorConstants.AUTHOR)
                .replace(TemplateConstants.BEAN_NAME, beanName)
                .replace(TemplateConstants.SERIAL_VERSION_ID, GeneratorUtils.generateSerialVersionId() + "");
        fileContent = generateFields(fileContent, tableColumns);
        fileContent = generatorConstructorParams(fileContent, tableColumns);
        fileContent = generatorConstructor(fileContent, tableColumns);
        fileContent = generateGetterSetters(fileContent, tableColumns);
        fileContent = generateToString(fileContent, tableColumns);
        GeneratorUtils.writeFile(fileContent, packagePath, beanName + GeneratorConstants.DTO_JAVA_SUFFIX);
    }

    /**
     * 生成所有DTO传输对象类
     * @param tableColumnsList 所有表数据
     */
    public static void generateDTOs(List<TableColumns> tableColumnsList) {
        for (TableColumns tableColumns : tableColumnsList) {
            generateDTO(tableColumns);
        }
    }

    /**
     * 生成VO值对象类
     * @param tableColumns 表数据
     */
    public static void generateVO(TableColumns tableColumns) {
        String beanName = GeneratorUtils.tableNameToClassName(tableColumns.getTableName(), GeneratorConstants.TABLE_PREFIX);
        String packagePath = GeneratorUtils.createPackage(GeneratorConstants.VO_PACKAGE);
        String fileContent = GeneratorUtils.readTemplate(GeneratorConstants.VO_TEMPLATE);
        fileContent = fileContent.replace(TemplateConstants.CREATE_DATE,
                DateFormatUtils.format(Calendar.getInstance(), DatePatternEnum.DATE.getValue()))
                .replace(TemplateConstants.AUTHOR, GeneratorConstants.AUTHOR)
                .replace(TemplateConstants.BEAN_NAME, beanName)
                .replace(TemplateConstants.SERIAL_VERSION_ID, GeneratorUtils.generateSerialVersionId() + "");
        fileContent = generateFields(fileContent, tableColumns);
        fileContent = generatorConstructorParams(fileContent, tableColumns);
        fileContent = generatorConstructor(fileContent, tableColumns);
        fileContent = generateGetterSetters(fileContent, tableColumns);
        fileContent = generateToString(fileContent, tableColumns);
        GeneratorUtils.writeFile(fileContent, packagePath, beanName + GeneratorConstants.VO_JAVA_SUFFIX);
    }

    /**
     * 生成所有VO值对象类
     * @param tableColumnsList 所有表数据
     */
    public static void generateVOs(List<TableColumns> tableColumnsList) {
        for (TableColumns tableColumns : tableColumnsList) {
            generateVO(tableColumns);
        }
    }

    /**
     * 生成Query查询对象类
     * @param tableColumns 表数据
     */
    public static void generateQuery(TableColumns tableColumns) {
        String beanName = GeneratorUtils.tableNameToClassName(tableColumns.getTableName(), GeneratorConstants.TABLE_PREFIX);
        String packagePath = GeneratorUtils.createPackage(GeneratorConstants.QUERY_PACKAGE);
        String fileContent = GeneratorUtils.readTemplate(GeneratorConstants.QUERY_TEMPLATE);
        fileContent = fileContent.replace(TemplateConstants.CREATE_DATE,
                DateFormatUtils.format(Calendar.getInstance(), DatePatternEnum.DATE.getValue()))
                .replace(TemplateConstants.AUTHOR, GeneratorConstants.AUTHOR)
                .replace(TemplateConstants.BEAN_NAME, beanName)
                .replace(TemplateConstants.SERIAL_VERSION_ID, GeneratorUtils.generateSerialVersionId() + "");
        fileContent = generateFields(fileContent, tableColumns);
        fileContent = generatorConstructorParams(fileContent, tableColumns);
        fileContent = generatorConstructor(fileContent, tableColumns);
        fileContent = generateGetterSetters(fileContent, tableColumns);
        // fileContent = generateToString(fileContent, tableColumns);
        fileContent = fileContent.replace(TemplateConstants.TO_STRING, "");
        GeneratorUtils.writeFile(fileContent, packagePath, beanName + GeneratorConstants.QUERY_JAVA_SUFFIX);
    }

    /**
     * 生成所有的Query查询对象类
     * @param tableColumnsList 所有表数据
     */
    public static void generateQuerys(List<TableColumns> tableColumnsList) {
        for (TableColumns tableColumns : tableColumnsList) {
            generateQuery(tableColumns);
        }
    }

    /**
     * 生成表对应的所有属性
     * @param fileContent 文件内容
     * @param tableColumns 表数据
     * @return 添加了所有属性的文件内容
     */
    private static String generateFields(String fileContent, TableColumns tableColumns) {
        List<ColumnDetail> columnDetailList = tableColumns.getColumns();
        StringBuilder fields = new StringBuilder("");
        for (ColumnDetail columnDetail : columnDetailList) {
            fields.append("/**\n")
                    .append("\t * ")
                    .append(columnDetail.getComment())
                    .append("\n")
                    .append("\t */\n")
                    .append("\tprivate ")
                    .append(columnDetail.getJavaTypeName())
                    .append(" ")
                    .append(PropertyUtils.columnToProperty(columnDetail.getName()))
                    .append(";\n\t");
        }
        return fileContent.replace(TemplateConstants.FIELDS, fields.toString());
    }

    /**
     * 生成有参构造方法的所有参数
     * @param fileContent 文件内容
     * @param tableColumns 表数据
     * @return 添加了有参构造方法的参数的文件内容
     */
    private static String generatorConstructorParams(String fileContent, TableColumns tableColumns) {
        List<ColumnDetail> columnDetailList = tableColumns.getColumns();
        StringBuilder constructorParams = new StringBuilder("");
        for (ColumnDetail columnDetail : columnDetailList) {
            constructorParams.append(", ")
                    .append(columnDetail.getJavaTypeName())
                    .append(" ")
                    .append(PropertyUtils.columnToProperty(columnDetail.getName()));
        }
        return fileContent.replace(TemplateConstants.CONSTRUCTOR_PARAMS, constructorParams.toString().replaceFirst(", ", ""));
    }

    /**
     * 生成有参构造方法体
     * @param fileContent 文件内容
     * @param tableColumns 表数据
     * @return 添加了有参构造方法体的文件内容
     */
    private static String generatorConstructor(String fileContent, TableColumns tableColumns) {
        List<ColumnDetail> columnDetailList = tableColumns.getColumns();
        StringBuilder constructor = new StringBuilder("");
        for (ColumnDetail columnDetail : columnDetailList) {
            String field = PropertyUtils.columnToProperty(columnDetail.getName());
            constructor.append("this.")
                    .append(field)
                    .append(" = ").append(field).append(";\n\t\t");
        }
        return fileContent.replace(TemplateConstants.CONSTRUCTOR, constructor.toString());
    }

    /**
     * 生成所有属性的getter/setter方法
     * @param fileContent 文件内容
     * @param tableColumns 表数据
     * @return 添加了所有属性的getter/setter方法的文件内容
     */
    private static String generateGetterSetters(String fileContent, TableColumns tableColumns) {
        List<ColumnDetail> columnDetailList = tableColumns.getColumns();
        StringBuilder getterSetters = new StringBuilder("");
        for (ColumnDetail columnDetail : columnDetailList) {
            String field = PropertyUtils.columnToProperty(columnDetail.getName());
            // getter
            getterSetters.append("public ")
                    .append(columnDetail.getJavaTypeName())
                    .append(" ")
                    .append(PropertyUtils.getter(field))
                    .append("()")
                    .append(" {\n")
                    .append("\t\treturn ")
                    .append(field)
                    .append(";\n")
                    .append("\t}\n\n")
                    // setter
                    .append("\tpublic void ")
                    .append(PropertyUtils.setter(field))
                    .append("(")
                    .append(columnDetail.getJavaTypeName())
                    .append(" ")
                    .append(field)
                    .append(") {\n")
                    .append("\t\tthis.")
                    .append(field)
                    .append(" = ")
                    .append(field)
                    .append(";\n")
                    .append("\t}\n\n\t");
        }
        return fileContent.replace(TemplateConstants.FIELDS_GETTER_SETTER, getterSetters.toString());
    }

    /**
     * 生成toString方法
     * @param fileContent 文件内容
     * @param tableColumns 表数据
     * @return 添加了toString方法的文件内容
     */
    private static String generateToString(String fileContent, TableColumns tableColumns) {
        List<ColumnDetail> columnDetailList = tableColumns.getColumns();
        StringBuilder toString = new StringBuilder("");
        for (ColumnDetail columnDetail : columnDetailList) {
            String field = PropertyUtils.columnToProperty(columnDetail.getName());
            toString.append("\", ").append(field).append(" = \" + ").append(field).append(" + ").append("\n\t\t\t\t");
        }
        return fileContent.replace(TemplateConstants.TO_STRING, toString.toString().replaceFirst(", ", ""));
    }

}

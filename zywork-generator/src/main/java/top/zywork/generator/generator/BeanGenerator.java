package top.zywork.generator.generator;

import org.apache.commons.lang3.StringUtils;
import top.zywork.common.DateFormatUtils;
import top.zywork.enums.DatePatternEnum;
import top.zywork.generator.bean.ColumnDetail;
import top.zywork.generator.bean.FieldDetail;
import top.zywork.generator.bean.Generator;
import top.zywork.generator.bean.TableColumns;
import top.zywork.generator.common.GeneratorUtils;
import top.zywork.generator.common.PropertyUtils;
import top.zywork.generator.constant.TemplateConstants;

import java.util.ArrayList;
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

    private static List<FieldDetail> fieldDetailList = new ArrayList<>();

    /**
     * 生成表对应的DO实体类
     * @param generator
     * @param tableColumns 表数据
     */
    public static void generateDO(Generator generator, TableColumns tableColumns) {
        String beanName = GeneratorUtils.tableNameToClassName(tableColumns.getTableName(), generator.getTablePrefix());
        String packagePath = GeneratorUtils.createPackage(generator, generator.getDoPackage());
        String fileContent = GeneratorUtils.readTemplate(generator, TemplateConstants.DO_TEMPLATE);
        fileContent = fileContent.replace(TemplateConstants.CREATE_DATE,
                DateFormatUtils.format(Calendar.getInstance(), DatePatternEnum.DATE.getValue()))
                .replace(TemplateConstants.AUTHOR, generator.getAuthor())
                .replace(TemplateConstants.BEAN_NAME, beanName)
                .replace(TemplateConstants.SERIAL_VERSION_ID, GeneratorUtils.generateSerialVersionId() + "");
        fileContent = generateFields(fileContent, tableColumns);
        fileContent = generatorConstructorParams(fileContent);
        fileContent = generatorConstructor(fileContent);
        fileContent = generateGetterSetters(fileContent);
        fileContent = generateToString(fileContent);
        GeneratorUtils.writeFile(fileContent, packagePath, beanName + generator.getDoSuffix());
    }

    public static void generateJoinDO(String beanName, Generator generator, String[] columns, List<TableColumns> tableColumnsList) {
        String packagePath = GeneratorUtils.createPackage(generator, generator.getDoPackage());
        String fileContent = GeneratorUtils.readTemplate(generator, TemplateConstants.DO_TEMPLATE);
        fileContent = fileContent.replace(TemplateConstants.CREATE_DATE,
                DateFormatUtils.format(Calendar.getInstance(), DatePatternEnum.DATE.getValue()))
                .replace(TemplateConstants.AUTHOR, generator.getAuthor())
                .replace(TemplateConstants.BEAN_NAME, beanName)
                .replace(TemplateConstants.SERIAL_VERSION_ID, GeneratorUtils.generateSerialVersionId() + "");
        fileContent = generateJoinFields(generator, fileContent, columns, tableColumnsList);
        fileContent = generatorJoinConstructorParams(fileContent);
        fileContent = generatorJoinConstructor(fileContent);
        fileContent = generateJoinGetterSetters(fileContent);
        fileContent = generateJoinToString(fileContent);
        GeneratorUtils.writeFile(fileContent, packagePath, beanName + generator.getDoSuffix());
    }

    /**
     * 生成所有表数据对应的DO实体类
     * @param generator
     * @param tableColumnsList 所有表数据
     */
    public static void generateDOs(Generator generator, List<TableColumns> tableColumnsList) {
        for (TableColumns tableColumns : tableColumnsList) {
            generateDO(generator, tableColumns);
        }
    }

    /**
     * 生成DTO传输对象类
     * @param generator
     * @param tableColumns 表数据
     */
    public static void generateDTO(Generator generator, TableColumns tableColumns) {
        String beanName = GeneratorUtils.tableNameToClassName(tableColumns.getTableName(), generator.getTablePrefix());
        String packagePath = GeneratorUtils.createPackage(generator, generator.getDtoPackage());
        String fileContent = GeneratorUtils.readTemplate(generator, TemplateConstants.DTO_TEMPLATE);
        fileContent = fileContent.replace(TemplateConstants.CREATE_DATE,
                DateFormatUtils.format(Calendar.getInstance(), DatePatternEnum.DATE.getValue()))
                .replace(TemplateConstants.AUTHOR, generator.getAuthor())
                .replace(TemplateConstants.BEAN_NAME, beanName)
                .replace(TemplateConstants.SERIAL_VERSION_ID, GeneratorUtils.generateSerialVersionId() + "");
        fileContent = generateFields(fileContent, tableColumns);
        fileContent = generatorConstructorParams(fileContent);
        fileContent = generatorConstructor(fileContent);
        fileContent = generateGetterSetters(fileContent);
        fileContent = generateToString(fileContent);
        GeneratorUtils.writeFile(fileContent, packagePath, beanName + generator.getDtoSuffix());
    }

    public static void generateJoinDTO(String beanName, Generator generator, String[] columns, List<TableColumns> tableColumnsList) {
        String packagePath = GeneratorUtils.createPackage(generator, generator.getDtoPackage());
        String fileContent = GeneratorUtils.readTemplate(generator, TemplateConstants.DTO_TEMPLATE);
        fileContent = fileContent.replace(TemplateConstants.CREATE_DATE,
                DateFormatUtils.format(Calendar.getInstance(), DatePatternEnum.DATE.getValue()))
                .replace(TemplateConstants.AUTHOR, generator.getAuthor())
                .replace(TemplateConstants.BEAN_NAME, beanName)
                .replace(TemplateConstants.SERIAL_VERSION_ID, GeneratorUtils.generateSerialVersionId() + "");
        fileContent = generateJoinFields(generator, fileContent, columns, tableColumnsList);
        fileContent = generatorJoinConstructorParams(fileContent);
        fileContent = generatorJoinConstructor(fileContent);
        fileContent = generateJoinGetterSetters(fileContent);
        fileContent = generateJoinToString(fileContent);
        GeneratorUtils.writeFile(fileContent, packagePath, beanName + generator.getDtoSuffix());
    }

    /**
     * 生成所有DTO传输对象类
     * @param generator
     * @param tableColumnsList 所有表数据
     */
    public static void generateDTOs(Generator generator, List<TableColumns> tableColumnsList) {
        for (TableColumns tableColumns : tableColumnsList) {
            generateDTO(generator, tableColumns);
        }
    }

    /**
     * 生成VO值对象类
     * @param generator
     * @param tableColumns 表数据
     */
    public static void generateVO(Generator generator, TableColumns tableColumns) {
        String beanName = GeneratorUtils.tableNameToClassName(tableColumns.getTableName(), generator.getTablePrefix());
        String packagePath = GeneratorUtils.createPackage(generator, generator.getVoPackage());
        String fileContent = GeneratorUtils.readTemplate(generator, TemplateConstants.VO_TEMPLATE);
        fileContent = fileContent.replace(TemplateConstants.CREATE_DATE,
                DateFormatUtils.format(Calendar.getInstance(), DatePatternEnum.DATE.getValue()))
                .replace(TemplateConstants.AUTHOR, generator.getAuthor())
                .replace(TemplateConstants.BEAN_NAME, beanName)
                .replace(TemplateConstants.SERIAL_VERSION_ID, GeneratorUtils.generateSerialVersionId() + "");
        fileContent = generateFields(fileContent, tableColumns);
        fileContent = generatorConstructorParams(fileContent);
        fileContent = generatorConstructor(fileContent);
        fileContent = generateGetterSetters(fileContent);
        fileContent = generateToString(fileContent);
        GeneratorUtils.writeFile(fileContent, packagePath, beanName + generator.getVoSuffix());
    }

    public static void generateJoinVO(String beanName, Generator generator, String[] columns, List<TableColumns> tableColumnsList) {
        String packagePath = GeneratorUtils.createPackage(generator, generator.getVoPackage());
        String fileContent = GeneratorUtils.readTemplate(generator, TemplateConstants.VO_TEMPLATE);
        fileContent = fileContent.replace(TemplateConstants.CREATE_DATE,
                DateFormatUtils.format(Calendar.getInstance(), DatePatternEnum.DATE.getValue()))
                .replace(TemplateConstants.AUTHOR, generator.getAuthor())
                .replace(TemplateConstants.BEAN_NAME, beanName)
                .replace(TemplateConstants.SERIAL_VERSION_ID, GeneratorUtils.generateSerialVersionId() + "");
        fileContent = generateJoinFields(generator, fileContent, columns, tableColumnsList);
        fileContent = generatorJoinConstructorParams(fileContent);
        fileContent = generatorJoinConstructor(fileContent);
        fileContent = generateJoinGetterSetters(fileContent);
        fileContent = generateJoinToString(fileContent);
        GeneratorUtils.writeFile(fileContent, packagePath, beanName + generator.getVoSuffix());
    }

    /**
     * 生成所有VO值对象类
     * @param generator
     * @param tableColumnsList 所有表数据
     */
    public static void generateVOs(Generator generator, List<TableColumns> tableColumnsList) {
        for (TableColumns tableColumns : tableColumnsList) {
            generateVO(generator, tableColumns);
        }
    }

    /**
     * 生成Query查询对象类
     * @param generator
     * @param tableColumns 表数据
     */
    public static void generateQuery(Generator generator, TableColumns tableColumns) {
        String beanName = GeneratorUtils.tableNameToClassName(tableColumns.getTableName(), generator.getTablePrefix());
        String packagePath = GeneratorUtils.createPackage(generator, generator.getQueryPackage());
        String fileContent = GeneratorUtils.readTemplate(generator, TemplateConstants.QUERY_TEMPLATE);
        fileContent = fileContent.replace(TemplateConstants.CREATE_DATE,
                DateFormatUtils.format(Calendar.getInstance(), DatePatternEnum.DATE.getValue()))
                .replace(TemplateConstants.AUTHOR, generator.getAuthor())
                .replace(TemplateConstants.BEAN_NAME, beanName)
                .replace(TemplateConstants.SERIAL_VERSION_ID, GeneratorUtils.generateSerialVersionId() + "");
        fileContent = generateFields(fileContent, tableColumns);
        fileContent = generatorConstructorParams(fileContent);
        fileContent = generatorConstructor(fileContent);
        fileContent = generateGetterSetters(fileContent);
        // fileContent = generateToString(fileContent, tableColumns);
        fileContent = fileContent.replace(TemplateConstants.TO_STRING, "");
        GeneratorUtils.writeFile(fileContent, packagePath, beanName + generator.getQuerySuffix());
    }

    public static void generateJoinQuery(String beanName, Generator generator, String[] columns, List<TableColumns> tableColumnsList) {
        String packagePath = GeneratorUtils.createPackage(generator, generator.getQueryPackage());
        String fileContent = GeneratorUtils.readTemplate(generator, TemplateConstants.QUERY_TEMPLATE);
        fileContent = fileContent.replace(TemplateConstants.CREATE_DATE,
                DateFormatUtils.format(Calendar.getInstance(), DatePatternEnum.DATE.getValue()))
                .replace(TemplateConstants.AUTHOR, generator.getAuthor())
                .replace(TemplateConstants.BEAN_NAME, beanName)
                .replace(TemplateConstants.SERIAL_VERSION_ID, GeneratorUtils.generateSerialVersionId() + "");
        fileContent = generateJoinFields(generator, fileContent, columns, tableColumnsList);
        fileContent = generatorJoinConstructorParams(fileContent);
        fileContent = generatorJoinConstructor(fileContent);
        fileContent = generateJoinGetterSetters(fileContent);
        // fileContent = generateJoinToString(fileContent, tableColumns);
        fileContent = fileContent.replace(TemplateConstants.TO_STRING, "");
        GeneratorUtils.writeFile(fileContent, packagePath, beanName + generator.getQuerySuffix());
    }

    /**
     * 生成所有的Query查询对象类
     * @param generator
     * @param tableColumnsList 所有表数据
     */
    public static void generateQuerys(Generator generator, List<TableColumns> tableColumnsList) {
        for (TableColumns tableColumns : tableColumnsList) {
            generateQuery(generator, tableColumns);
        }
    }

    /**
     * 生成表对应的所有属性
     * @param fileContent 文件内容
     * @param tableColumns 表数据
     * @return 添加了所有属性的文件内容
     */
    private static String generateFields(String fileContent, TableColumns tableColumns) {
        fieldDetailList.clear();
        List<ColumnDetail> columnDetailList = tableColumns.getColumns();
        StringBuilder fields = new StringBuilder("");
        for (ColumnDetail columnDetail : columnDetailList) {
            String field = columnDetail.getFieldName();
            String javaType = columnDetail.getJavaTypeName();
            fieldDetailList.add(new FieldDetail(field, javaType));
            fields.append("/**\n")
                    .append("\t * ")
                    .append(columnDetail.getComment())
                    .append("\n")
                    .append("\t */\n")
                    .append("\tprivate ")
                    .append(javaType)
                    .append(" ")
                    .append(field)
                    .append(";\n\t");
        }
        return fileContent.replace(TemplateConstants.FIELDS, fields.toString());
    }

    private static String generateJoinFields(Generator generator, String fileContent, String[] columns, List<TableColumns> tableColumnsList) {
        fieldDetailList.clear();
        StringBuilder fields = new StringBuilder("");
        String lastTableName = null;
        for (String column : columns) {
            String[] tableNameAndColumn = column.split("-");
            String tableName = tableNameAndColumn[0];
            String columnName = tableNameAndColumn[1];
            // 对所表字段进行循环
            for (TableColumns tableColumns : tableColumnsList) {
                // 如果是指定的表
                if (tableColumns.getTableName().equals(tableName)) {
                    if (!tableName.equals(lastTableName)) {
                        fields.append("//")
                                .append(tableName)
                                .append("表的字段对应的属性\n\t");
                        lastTableName = tableName;
                    }
                    // 获取指定表的所有字段信息
                    List<ColumnDetail> columnDetailList = tableColumns.getColumns();
                    for (ColumnDetail columnDetail : columnDetailList) {
                        // 如果是想要查询的字段
                        if (columnDetail.getName().equals(columnName)) {
                            String field = StringUtils.uncapitalize(GeneratorUtils.tableNameToClassName(tableName, generator.getTablePrefix()))
                                    + StringUtils.capitalize(columnDetail.getFieldName());
                            String javaType = columnDetail.getJavaTypeName();
                            fieldDetailList.add(new FieldDetail(field, javaType));
                            fields.append("/**\n")
                                    .append("\t * ")
                                    .append(columnDetail.getComment())
                                    .append("\n")
                                    .append("\t */\n")
                                    .append("\tprivate ")
                                    .append(javaType)
                                    .append(" ")
                                    .append(field)
                                    .append(";\n\t");
                        }
                    }
                }
            }
        }
        return fileContent.replace(TemplateConstants.FIELDS, fields.toString());
    }

    /**
     * 生成有参构造方法的所有参数
     * @param fileContent 文件内容
     * @return 添加了有参构造方法的参数的文件内容
     */
    private static String generatorConstructorParams(String fileContent) {
        StringBuilder constructorParams = new StringBuilder("");
        for (FieldDetail fieldDetail : fieldDetailList) {
            constructorParams.append(", ")
                    .append(fieldDetail.getJavaType())
                    .append(" ")
                    .append(fieldDetail.getName());
        }
        return fileContent.replace(TemplateConstants.CONSTRUCTOR_PARAMS, constructorParams.toString().replaceFirst(", ", ""));
    }

    private static String generatorJoinConstructorParams(String fileContent) {
        StringBuilder constructorParams = new StringBuilder("");
        for (FieldDetail fieldDetail : fieldDetailList) {
            constructorParams.append(", ")
                    .append(fieldDetail.getJavaType())
                    .append(" ")
                    .append(fieldDetail.getName());

        }
        return fileContent.replace(TemplateConstants.CONSTRUCTOR_PARAMS, constructorParams.toString().replaceFirst(", ", ""));
    }

    /**
     * 生成有参构造方法体
     * @param fileContent 文件内容
     * @return 添加了有参构造方法体的文件内容
     */
    private static String generatorConstructor(String fileContent) {
        StringBuilder constructor = new StringBuilder("");
        for (FieldDetail fieldDetail : fieldDetailList) {
            String field = fieldDetail.getName();
            constructor.append("this.")
                    .append(field)
                    .append(" = ").append(field).append(";\n\t\t");
        }
        return fileContent.replace(TemplateConstants.CONSTRUCTOR, constructor.toString());
    }

    private static String generatorJoinConstructor(String fileContent) {
        StringBuilder constructor = new StringBuilder("");
        for (FieldDetail fieldDetail : fieldDetailList) {
            String field = fieldDetail.getName();
            constructor.append("this.")
                    .append(field)
                    .append(" = ").append(field).append(";\n\t\t");
        }

        return fileContent.replace(TemplateConstants.CONSTRUCTOR, constructor.toString());
    }

    /**
     * 生成所有属性的getter/setter方法
     * @param fileContent 文件内容
     * @return 添加了所有属性的getter/setter方法的文件内容
     */
    private static String generateGetterSetters(String fileContent) {
        StringBuilder getterSetters = new StringBuilder("");
        for (FieldDetail fieldDetail : fieldDetailList) {
            String field = fieldDetail.getName();
            String javaType = fieldDetail.getJavaType();
            // getter
            getterSetters.append("public ")
                    .append(javaType)
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
                    .append(javaType)
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

    private static String generateJoinGetterSetters(String fileContent) {
        StringBuilder getterSetters = new StringBuilder("");
        for (FieldDetail fieldDetail : fieldDetailList) {
            String field = fieldDetail.getName();
            String javaType = fieldDetail.getJavaType();
            // getter
            getterSetters.append("public ")
                    .append(javaType)
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
                    .append(javaType)
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
     * @return 添加了toString方法的文件内容
     */
    private static String generateToString(String fileContent) {
        StringBuilder toString = new StringBuilder("");
        for (FieldDetail fieldDetail : fieldDetailList) {
            String field = fieldDetail.getName();
            toString.append("\", ").append(field).append(" = \" + ").append(field).append(" + ").append("\n\t\t\t\t");
        }
        return fileContent.replace(TemplateConstants.TO_STRING, toString.toString().replaceFirst(", ", ""));
    }

    private static String generateJoinToString(String fileContent) {
        StringBuilder toString = new StringBuilder("");
        for (FieldDetail fieldDetail : fieldDetailList) {
            String field = fieldDetail.getName();
            toString.append("\", ").append(field).append(" = \" + ").append(field).append(" + ").append("\n\t\t\t\t");
        }
        return fileContent.replace(TemplateConstants.TO_STRING, toString.toString().replaceFirst(", ", ""));
    }

}

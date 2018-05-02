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

import java.sql.DatabaseMetaData;
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

    private static final String DO_BEAN = "do";
    private static final String DTO_BEAN = "dto";
    private static final String VO_BEAN = "vo";
    private static final String QUERY_BEAN = "query";

    /**
     * 用于存储表字段对应的属性信息
     */
    private static List<FieldDetail> fieldDetailList = new ArrayList<>();

    /**
     * 生成表对应的DO实体类
     * @param generator Generator实例
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
        fileContent = generateFields(fileContent, tableColumns, DO_BEAN);
        fileContent = generatorConstructorParams(fileContent);
        fileContent = generatorConstructor(fileContent);
        fileContent = generateGetterSetters(fileContent);
        fileContent = generateToString(fileContent);
        GeneratorUtils.writeFile(fileContent, packagePath, beanName + generator.getDoSuffix());
    }

    /**
     * 生成关联表DO
     * @param beanName bean名称
     * @param generator Generator实例
     * @param primaryTable 主表名称
     * @param columns 所选的字段
     * @param tableColumnsList 所有表字段信息列表
     */
    public static void generateJoinDO(String beanName, Generator generator, String primaryTable, String[] columns, List<TableColumns> tableColumnsList) {
        String packagePath = GeneratorUtils.createPackage(generator, generator.getDoPackage());
        String fileContent = GeneratorUtils.readTemplate(generator, TemplateConstants.DO_TEMPLATE);
        fileContent = fileContent.replace(TemplateConstants.CREATE_DATE,
                DateFormatUtils.format(Calendar.getInstance(), DatePatternEnum.DATE.getValue()))
                .replace(TemplateConstants.AUTHOR, generator.getAuthor())
                .replace(TemplateConstants.BEAN_NAME, beanName)
                .replace(TemplateConstants.SERIAL_VERSION_ID, GeneratorUtils.generateSerialVersionId() + "");
        fileContent = generateJoinFields(generator, fileContent, primaryTable, columns, tableColumnsList, DO_BEAN);
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
        fileContent = generateFields(fileContent, tableColumns, DTO_BEAN);
        fileContent = generatorConstructorParams(fileContent);
        fileContent = generatorConstructor(fileContent);
        fileContent = generateGetterSetters(fileContent);
        fileContent = generateToString(fileContent);
        GeneratorUtils.writeFile(fileContent, packagePath, beanName + generator.getDtoSuffix());
    }

    /**
     * 生成关联表DTO对象
     * @param beanName bean名称
     * @param generator Generator实例
     * @param primaryTable 主表名称
     * @param columns 所选的字段
     * @param tableColumnsList 所有表的字段信息列表
     */
    public static void generateJoinDTO(String beanName, Generator generator, String primaryTable, String[] columns, List<TableColumns> tableColumnsList) {
        String packagePath = GeneratorUtils.createPackage(generator, generator.getDtoPackage());
        String fileContent = GeneratorUtils.readTemplate(generator, TemplateConstants.DTO_TEMPLATE);
        fileContent = fileContent.replace(TemplateConstants.CREATE_DATE,
                DateFormatUtils.format(Calendar.getInstance(), DatePatternEnum.DATE.getValue()))
                .replace(TemplateConstants.AUTHOR, generator.getAuthor())
                .replace(TemplateConstants.BEAN_NAME, beanName)
                .replace(TemplateConstants.SERIAL_VERSION_ID, GeneratorUtils.generateSerialVersionId() + "");
        fileContent = generateJoinFields(generator, fileContent, primaryTable, columns, tableColumnsList, DTO_BEAN);
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
        fileContent = generateFields(fileContent, tableColumns, VO_BEAN);
        fileContent = generatorConstructorParams(fileContent);
        fileContent = generatorConstructor(fileContent);
        fileContent = generateGetterSetters(fileContent);
        fileContent = generateToString(fileContent);
        GeneratorUtils.writeFile(fileContent, packagePath, beanName + generator.getVoSuffix());
    }

    /**
     * 生成关联表的VO对象
     * @param beanName bean名称
     * @param generator Generator实例
     * @param primaryTable 主表名称
     * @param columns 所选的字段
     * @param tableColumnsList 所有表字段信息列表
     */
    public static void generateJoinVO(String beanName, Generator generator, String primaryTable, String[] columns, List<TableColumns> tableColumnsList) {
        String packagePath = GeneratorUtils.createPackage(generator, generator.getVoPackage());
        String fileContent = GeneratorUtils.readTemplate(generator, TemplateConstants.VO_TEMPLATE);
        fileContent = fileContent.replace(TemplateConstants.CREATE_DATE,
                DateFormatUtils.format(Calendar.getInstance(), DatePatternEnum.DATE.getValue()))
                .replace(TemplateConstants.AUTHOR, generator.getAuthor())
                .replace(TemplateConstants.BEAN_NAME, beanName)
                .replace(TemplateConstants.SERIAL_VERSION_ID, GeneratorUtils.generateSerialVersionId() + "");
        fileContent = generateJoinFields(generator, fileContent, primaryTable, columns, tableColumnsList, VO_BEAN);
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
        fileContent = generateFields(fileContent, tableColumns, QUERY_BEAN);
        fileContent = generatorConstructorParams(fileContent);
        fileContent = generatorConstructor(fileContent);
        fileContent = generateGetterSetters(fileContent);
        // fileContent = generateToString(fileContent, tableColumns);
        fileContent = fileContent.replace(TemplateConstants.TO_STRING, "");
        GeneratorUtils.writeFile(fileContent, packagePath, beanName + generator.getQuerySuffix());
    }

    /**
     * 生成关联表的Query对象
     * @param beanName bean名称
     * @param generator Generator实例
     * @param primaryTable 主表名称
     * @param columns 所选的字段
     * @param tableColumnsList 所有表字段信息的列表
     */
    public static void generateJoinQuery(String beanName, Generator generator, String primaryTable, String[] columns, List<TableColumns> tableColumnsList) {
        String packagePath = GeneratorUtils.createPackage(generator, generator.getQueryPackage());
        String fileContent = GeneratorUtils.readTemplate(generator, TemplateConstants.QUERY_TEMPLATE);
        fileContent = fileContent.replace(TemplateConstants.CREATE_DATE,
                DateFormatUtils.format(Calendar.getInstance(), DatePatternEnum.DATE.getValue()))
                .replace(TemplateConstants.AUTHOR, generator.getAuthor())
                .replace(TemplateConstants.BEAN_NAME, beanName)
                .replace(TemplateConstants.SERIAL_VERSION_ID, GeneratorUtils.generateSerialVersionId() + "");
        fileContent = generateJoinFields(generator, fileContent, primaryTable, columns, tableColumnsList, QUERY_BEAN);
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
     * @param beanType bean类型
     * @return 添加了所有属性的文件内容
     */
    private static String generateFields(String fileContent, TableColumns tableColumns, String beanType) {
        fieldDetailList.clear();
        List<ColumnDetail> columnDetailList = tableColumns.getColumns();
        StringBuilder fields = new StringBuilder("");
        for (ColumnDetail columnDetail : columnDetailList) {
            String field = columnDetail.getFieldName();
            String javaType = columnDetail.getJavaTypeName();
            String comment = columnDetail.getComment();
            fieldDetailList.add(new FieldDetail(field, javaType, comment));
            fields.append(field("id", comment, javaType, field, columnDetail.getNullable(), columnDetail.getColumnSize(), beanType.equals(VO_BEAN)));
            if (beanType.equals(QUERY_BEAN) && javaType.equals("Date")) {
                fields.append(dateField(comment, field, javaType));

            }
        }
        return fileContent.replace(TemplateConstants.FIELDS, fields.toString());
    }

    /**
     * 生成关联表的所有属性
     * @param generator Generator实例
     * @param fileContent 文件内容
     * @param primaryTable 主表名称
     * @param columns 所选的字段
     * @param tableColumnsList 所有表字段信息的列表
     * @param beanType bean类型
     * @return
     */
    private static String generateJoinFields(Generator generator, String fileContent, String primaryTable, String[] columns, List<TableColumns> tableColumnsList, String beanType) {
        fieldDetailList.clear();
        StringBuilder fields = new StringBuilder("");
        String id = StringUtils.uncapitalize(GeneratorUtils.tableNameToClassName(primaryTable,
                generator.getTablePrefix())) + StringUtils.capitalize(PropertyUtils.columnToProperty("id"));
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
                            String comment = columnDetail.getComment();
                            fieldDetailList.add(new FieldDetail(field, javaType, comment));
                            fields.append(field(id, comment, javaType, field, columnDetail.getNullable(), columnDetail.getColumnSize(),
                                    beanType.equals(VO_BEAN) && tableName.equals(primaryTable)));
                            if (beanType.equals(QUERY_BEAN) && javaType.equals("Date")) {
                                fields.append(dateField(comment, field, javaType));
                            }
                        }
                    }
                }
            }
        }
        return fileContent.replace(TemplateConstants.FIELDS, fields.toString());
    }

    private static String field(String idField, String title, String javaType, String fieldName, int nullable, int columnSize, boolean needValidate) {
        StringBuilder field = new StringBuilder();
        if (!fieldName.equals(idField) && needValidate) {
            field.append("/**\n")
                    .append("\t * ")
                    .append(title)
                    .append("\n")
                    .append("\t */\n");
            if (javaType.equals("String") && nullable == DatabaseMetaData.columnNoNulls) {
                field.append("\t@NotBlank(message = \"此项是必须项\")\n");
            } else if (javaType.equals("String") && nullable == DatabaseMetaData.columnNoNulls) {
                field.append("\t@Size(min = 1, max = ")
                        .append(columnSize)
                        .append(", message = \"必须是1-")
                        .append(columnSize)
                        .append("个字符\")\n");
            } else if (javaType.equals("String") && nullable == DatabaseMetaData.columnNullable) {
                field.append("\t@Size(min = 0, max = ")
                        .append(columnSize)
                        .append(", message = \"必须小于")
                        .append(columnSize)
                        .append("个字符\")\n");
            } else if (nullable == DatabaseMetaData.columnNoNulls) {
                field.append("\t@NotNull(message = \"此项是必须项\")\n");
            }
            field.append("\tprivate ")
                    .append(javaType)
                    .append(" ")
                    .append(fieldName)
                    .append(";\n\t");
        } else {
            field.append("/**\n")
                    .append("\t * ")
                    .append(title)
                    .append("\n")
                    .append("\t */\n")
                    .append("\tprivate ")
                    .append(javaType)
                    .append(" ")
                    .append(fieldName)
                    .append(";\n\t");
        }
        return field.toString();
    }

    private static String dateField(String title, String field, String javaType) {
        StringBuilder dateField = new StringBuilder();
        fieldDetailList.add(new FieldDetail(field + "Start", javaType, title));
        fieldDetailList.add(new FieldDetail(field + "End", javaType, title));
        dateField.append(field("", title + "(开始)", javaType, field + "Start", 0, 0, false));
        dateField.append(field("", title + "(结束)", javaType, field + "End", 0, 0, false));
        return dateField.toString();
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

    /**
     * 生成关联表的构造方法的所有参数
     * @param fileContent 文件内容
     * @return
     */
    private static String generatorJoinConstructorParams(String fileContent) {
        return generatorConstructorParams(fileContent);
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

    /**
     * 生成关联表对象的构造方法
     * @param fileContent 文件内容
     * @return
     */
    private static String generatorJoinConstructor(String fileContent) {
        return generatorConstructor(fileContent);
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

    /**
     * 生成关联表对象的getter/setter
     * @param fileContent 文件内容
     * @return
     */
    private static String generateJoinGetterSetters(String fileContent) {
        return generateGetterSetters(fileContent);
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

    /**
     * 生成关联表对象的toString方法
     * @param fileContent 文件内容
     * @return
     */
    private static String generateJoinToString(String fileContent) {
        return generateToString(fileContent);
    }

}

package top.zywork.generator.generator;

import org.apache.commons.lang3.StringUtils;
import top.zywork.generator.bean.ColumnDetail;
import top.zywork.generator.bean.Generator;
import top.zywork.generator.bean.TableColumns;
import top.zywork.generator.common.GeneratorUtils;
import top.zywork.generator.common.PropertyUtils;
import top.zywork.generator.constant.TemplateConstants;

import java.util.List;

/**
 * Mapper映射自动生成代码封装类<br/>
 *
 * 创建于2018-03-12<br/>
 *
 * @author 王振宇
 * @version 1.0
 */
public class MapperGenerator {

    /**
     * 生成Mapper映射xml文件
     * @param generator
     * @param tableColumns 表数据
     */
    public static void generateMapper(Generator generator, TableColumns tableColumns) {
        String beanName = GeneratorUtils.tableNameToClassName(tableColumns.getTableName(), generator.getTablePrefix());
        String resDir = GeneratorUtils.createResDir(generator, generator.getMapperDir());
        String fileContent = GeneratorUtils.readTemplate(generator, TemplateConstants.MAPPER_TEMPLATE);
        fileContent = fileContent.replace(TemplateConstants.BEAN_NAME, beanName)
                .replace(TemplateConstants.BEAN_NAME_LOWER_CASE, StringUtils.uncapitalize(beanName))
                .replace(TemplateConstants.TABLE_NAME, tableColumns.getTableName());
        fileContent = generateInsertColumns(fileContent, tableColumns);
        fileContent = generateInsertValues(fileContent, tableColumns);
        fileContent = generateSetClause(fileContent, tableColumns);
        fileContent = generateSelectColumns(fileContent, tableColumns);
        fileContent = generateQueryWhereClause(fileContent, tableColumns);
        GeneratorUtils.writeFile(fileContent, resDir, beanName + generator.getMapperSuffix());
    }

    /**
     * 生成关联表的Mapper映射xml文件
     * @param beanName bean名称
     * @param generator Generator实例
     * @param primaryTable 主表名称
     * @param columns 所选的字段
     * @param joinWhereClause 关联条件
     */
    public static void generateJoinMapper(String beanName, Generator generator, String primaryTable, String[] columns, String joinWhereClause) {
        StringBuilder tableName = new StringBuilder();
        for (String column : columns) {
            String table = column.split("-")[0];
            if (!tableName.toString().contains("[" + table + "]")) {
                tableName.append(", [").append(table).append("]");
            }
        }
        String resDir = GeneratorUtils.createResDir(generator, generator.getMapperDir());
        String fileContent = GeneratorUtils.readTemplate(generator, TemplateConstants.JOIN_MAPPER_TEMPLATE);
        fileContent = fileContent.replace(TemplateConstants.BEAN_NAME, beanName)
                .replace(TemplateConstants.BEAN_NAME_LOWER_CASE, StringUtils.uncapitalize(beanName))
                .replace(TemplateConstants.TABLE_NAME, tableName.toString().substring(2).replace("[", "").replace("]", ""))
                .replace(TemplateConstants.PRIMARY_TABLE, primaryTable)
                .replace(TemplateConstants.PRIMARY_ID, StringUtils.uncapitalize(GeneratorUtils.tableNameToClassName(primaryTable,
                        generator.getTablePrefix())) + StringUtils.capitalize(PropertyUtils.columnToProperty("id")));
        fileContent = generateJoinInsertColumns(fileContent, generator, primaryTable, columns);
        fileContent = generateJoinInsertValues(fileContent, generator, primaryTable, columns);
        fileContent = generateJoinSetClause(fileContent, generator, primaryTable, columns);
        fileContent = generateJoinSelectColumns(fileContent, columns);
        fileContent = generateJoinQueryWhereClause(generator, fileContent, columns);
        fileContent = generateJoinWhereClause(fileContent, joinWhereClause);
        GeneratorUtils.writeFile(fileContent, resDir, beanName + generator.getMapperSuffix());
    }

    /**
     * 生成所有Mapper映射文件
     * @param generator
     * @param tableColumnsList 所有表数据
     */
    public static void generateMappers(Generator generator, List<TableColumns> tableColumnsList) {
        for (TableColumns tableColumns : tableColumnsList) {
            generateMapper(generator, tableColumns);
        }
    }

    /**
     * 生成Mapper映射文件insert语句中的column部分
     * @param fileContent 文件内容
     * @param tableColumns 表数据
     * @return 添加了insert语句的文件内容
     */
    private static String generateInsertColumns(String fileContent, TableColumns tableColumns) {
        List<ColumnDetail> columnDetails = tableColumns.getColumns();
        StringBuilder insertColumns = new StringBuilder("");
        for (ColumnDetail columnDetail : columnDetails) {
            if (!columnDetail.getName().equals("id")) {
                insertColumns.append("<if test=\"")
                        .append(columnDetail.getFieldName())
                        .append(" != null\">\n\t\t\t\t")
                        .append(columnDetail.getName())
                        .append(",\n\t\t\t</if>\n\t\t\t");
            }
        }
        return fileContent.replace(TemplateConstants.INSERT_COLUMNS, insertColumns.toString());
    }

    /**
     * 生成多表Mapper映射文件中主表的insert语句中的column部分
     * @param fileContent 文件内容
     * @param generator Generator实例
     * @param primaryTable 主表名称
     * @param columns 所选的表字段
     * @return 添加了insert语句的文件内容
     */
    private static String generateJoinInsertColumns(String fileContent, Generator generator, String primaryTable, String[] columns) {
        StringBuilder insertColumns = new StringBuilder("");
        String id = StringUtils.uncapitalize(GeneratorUtils.tableNameToClassName(primaryTable,
                generator.getTablePrefix())) + StringUtils.capitalize(PropertyUtils.columnToProperty("id"));
        for (String column : columns) {
            String[] tableNameAndColumn = column.split("-");
            String tableName = tableNameAndColumn[0];
            String columnName = tableNameAndColumn[1];
            if (tableName.equals(primaryTable)) {
                String field = StringUtils.uncapitalize(GeneratorUtils.tableNameToClassName(primaryTable, generator.getTablePrefix()))
                        + StringUtils.capitalize(PropertyUtils.columnToProperty(columnName));
                if (!field.equals(id)) {
                    insertColumns.append("<if test=\"")
                            .append(field)
                            .append(" != null\">\n\t\t\t\t")
                            .append(columnName)
                            .append(",\n\t\t\t</if>\n\t\t\t");
                }
            }
        }
        return fileContent.replace(TemplateConstants.INSERT_COLUMNS, insertColumns.toString());
    }

    /**
     * 生成Mapper映射文件insert语句中的值部分
     * @param fileContent 文件内容
     * @param tableColumns 表数据
     * @return 添加了insert语句的文件内容
     */
    private static String generateInsertValues(String fileContent, TableColumns tableColumns){
        List<ColumnDetail> columnDetails = tableColumns.getColumns();
        StringBuilder insertValues = new StringBuilder("");
        for (ColumnDetail columnDetail : columnDetails) {
            if (!columnDetail.getName().equals("id")) {
                String field = columnDetail.getFieldName();
                insertValues.append("<if test=\"")
                        .append(field)
                        .append(" != null\">\n\t\t\t\t")
                        .append("#{")
                        .append(field)
                        .append("}")
                        .append(",\n\t\t\t</if>\n\t\t\t");
            }
        }
        return fileContent.replace(TemplateConstants.INSERT_VALUES, insertValues.toString());
    }

    /**
     * 生成多表Mapper映射文件中主表的insert语句中的值部分
     * @param fileContent 文件内容
     * @param generator Generator实例
     * @param primaryTable 主表名称
     * @param columns 所选的表字段
     * @return 添加了insert语句的文件内容
     */
    private static String generateJoinInsertValues(String fileContent, Generator generator, String primaryTable, String[] columns){
        StringBuilder insertValues = new StringBuilder("");
        String id = StringUtils.uncapitalize(GeneratorUtils.tableNameToClassName(primaryTable,
                generator.getTablePrefix())) + StringUtils.capitalize(PropertyUtils.columnToProperty("id"));
        for (String column : columns) {
            String[] tableNameAndColumn = column.split("-");
            String tableName = tableNameAndColumn[0];
            String columnName = tableNameAndColumn[1];
            if (tableName.equals(primaryTable)) {
                String field = StringUtils.uncapitalize(GeneratorUtils.tableNameToClassName(primaryTable, generator.getTablePrefix()))
                        + StringUtils.capitalize(PropertyUtils.columnToProperty(columnName));
                if (!field.equals(id)) {
                    insertValues.append("<if test=\"")
                            .append(field)
                            .append(" != null\">\n\t\t\t\t")
                            .append("#{")
                            .append(field)
                            .append("}")
                            .append(",\n\t\t\t</if>\n\t\t\t");
                }
            }
        }
        return fileContent.replace(TemplateConstants.INSERT_VALUES, insertValues.toString());
    }

    /**
     * 生成Mapper映射文件update语句的set部分
     * @param fileContent 文件内容
     * @param tableColumns 表数据
     * @return 添加了update语句的文件内容
     */
    private static String generateSetClause(String fileContent, TableColumns tableColumns){
        List<ColumnDetail> columnDetails = tableColumns.getColumns();
        StringBuilder setClause = new StringBuilder("");
        for (ColumnDetail columnDetail : columnDetails) {
            if (!columnDetail.getName().equals("id")) {
                String field = columnDetail.getFieldName();
                setClause.append("<if test=\"")
                        .append(field)
                        .append(" != null\">\n\t\t\t\t")
                        .append(columnDetail.getName())
                        .append(" = ")
                        .append("#{")
                        .append(field)
                        .append("}")
                        .append(",\n\t\t\t</if>\n\t\t\t");
            }
        }
        return fileContent.replace(TemplateConstants.SET_CLAUSE, setClause.toString());
    }

    /**
     * 生成多表Mapper映射文件中主表的update语句的set部分
     * @param fileContent 文件内容
     * @param generator Generator实例
     * @param primaryTable 主表名称
     * @param columns 所选的表字段
     * @return 添加了update语句的文件内容
     */
    private static String generateJoinSetClause(String fileContent, Generator generator, String primaryTable, String[] columns){
        StringBuilder setClause = new StringBuilder("");
        String id = StringUtils.uncapitalize(GeneratorUtils.tableNameToClassName(primaryTable,
                generator.getTablePrefix())) + StringUtils.capitalize(PropertyUtils.columnToProperty("id"));
        for (String column : columns) {
            String[] tableNameAndColumn = column.split("-");
            String tableName = tableNameAndColumn[0];
            String columnName = tableNameAndColumn[1];
            if (tableName.equals(primaryTable)) {
                String field = StringUtils.uncapitalize(GeneratorUtils.tableNameToClassName(primaryTable, generator.getTablePrefix()))
                        + StringUtils.capitalize(PropertyUtils.columnToProperty(columnName));
                if (!field.equals(id)) {
                    setClause.append("<if test=\"")
                            .append(field)
                            .append(" != null\">\n\t\t\t\t")
                            .append(columnName)
                            .append(" = ")
                            .append("#{")
                            .append(field)
                            .append("}")
                            .append(",\n\t\t\t</if>\n\t\t\t");
                }
            }
        }
        return fileContent.replace(TemplateConstants.SET_CLAUSE, setClause.toString());
    }

    /**
     * 生成Mapper映射文件select字段部分
     * @param fileContent 文件内容
     * @param tableColumns 表数据
     * @return 添加了select通用字段部分的文件内容
     */
    private static String generateSelectColumns(String fileContent, TableColumns tableColumns){
        List<ColumnDetail> columnDetails = tableColumns.getColumns();
        StringBuilder selectColumns = new StringBuilder("");
        for (ColumnDetail columnDetail : columnDetails) {
            selectColumns.append(", ")
                    .append(columnDetail.getName());
        }
        return fileContent.replace(TemplateConstants.SELECT_COLUMNS, selectColumns.toString().replaceFirst(", ", ""));
    }

    /**
     * 生成关联表查询的select字段部分
     * @param fileContent 文件内容
     * @param columns 所选的字段
     * @return
     */
    private static String generateJoinSelectColumns(String fileContent, String[] columns){
        StringBuilder selectColumns = new StringBuilder("");
        for (String column : columns) {
            String[] tableNameAndColumn = column.split("-");
            String tableName = tableNameAndColumn[0];
            String columnName = tableNameAndColumn[1];
            selectColumns.append(", ")
                    .append(tableName + "." + columnName);
        }
        return fileContent.replace(TemplateConstants.SELECT_COLUMNS, selectColumns.toString().replaceFirst(", ", ""));
    }

    /**
     * 生成Mapper映射文件查询语句where部分的内容
     * @param fileContent 文件内容
     * @param tableColumns 表数据
     * @return 添加了查询语句where部分内容的文件内容
     */
    private static String generateQueryWhereClause(String fileContent, TableColumns tableColumns){
        List<ColumnDetail> columnDetails = tableColumns.getColumns();
        StringBuilder whereClause = new StringBuilder("");
        for (int i = 0, size = columnDetails.size(); i < size; i++) {
            ColumnDetail columnDetail = columnDetails.get(i);
            String field = columnDetail.getFieldName();
            boolean isString = columnDetail.getJavaTypeName().equals("String");
            whereClause.append("<if test=\"query != null and query.")
                    .append(field);
            if (isString) {
                whereClause.append(" != null and query.").append(field).append(" != ''\">\n\t\t\t");
            } else {
                whereClause.append(" != null\">\n\t\t\t");
            }
            if (i != 0) {
                whereClause.append("and ");
            }
            if (isString) {
                whereClause.append(columnDetail.getName())
                        .append(" like concat('%', ")
                        .append("#{")
                        .append(field)
                        .append("}, '%')");
            } else {
                whereClause.append(columnDetail.getName())
                        .append(" = ")
                        .append("#{")
                        .append(field)
                        .append("}");
            }
            whereClause.append("\n\t\t</if>\n\t\t");
        }
        return fileContent.replace(TemplateConstants.QUERY_WHERE_CLAUSE, whereClause.toString());
    }

    /**
     * 生成关联表的查询条件部分
     * @param generator Generator实例
     * @param fileContent 文件内容
     * @param columns 所选的字段
     * @return
     */
    private static String generateJoinQueryWhereClause(Generator generator, String fileContent, String[] columns){
        StringBuilder whereClause = new StringBuilder("");
        for (int i = 0, len = columns.length; i < len; i++) {
            String[] tableNameAndColumn = columns[i].split("-");
            String tableName = tableNameAndColumn[0];
            String columnName = tableNameAndColumn[1];
            String javaType = tableNameAndColumn[2];
            String fullColumnName = tableName + "." + columnName;
            String field = StringUtils.uncapitalize(GeneratorUtils.tableNameToClassName(tableName, generator.getTablePrefix()))
                    + StringUtils.capitalize(PropertyUtils.columnToProperty(columnName));
            boolean isString = javaType.equals("String");
            whereClause.append("<if test=\"query != null and query.")
                    .append(field);
            if (isString) {
                whereClause.append(" != null and query.").append(field).append(" != ''\">\n\t\t\t");
            } else {
                whereClause.append(" != null\">\n\t\t\t");
            }
            if (i != 0) {
                whereClause.append("and ");
            }
            if (isString) {
                whereClause.append(fullColumnName)
                        .append(" like concat('%', ")
                        .append("#{")
                        .append(field)
                        .append("}, '%')");
            } else {
                whereClause.append(fullColumnName)
                        .append(" = ")
                        .append("#{")
                        .append(field)
                        .append("}");
            }
            whereClause.append("\n\t\t</if>\n\t\t");
        }
        return fileContent.replace(TemplateConstants.QUERY_WHERE_CLAUSE, whereClause.toString());
    }

    /**
     * 生成关联表的关联条件部分
     * @param fileContent 文件内容
     * @param joinWhereClause 关联条件
     * @return
     */
    private static String generateJoinWhereClause(String fileContent, String joinWhereClause){
        return fileContent.replace(TemplateConstants.JOIN_WHERE_CLAUSE, joinWhereClause);
    }

}

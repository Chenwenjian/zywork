package top.zywork.generator.bean;

import java.util.List;

/**
 * 数据表与数据表所有字段的封装类<br/>
 *
 * 创建于2018-03-12<br/>
 *
 * @author 王振宇
 * @version 1.0
 */
public class TableColumns {

    private String tableName;
    private List<ColumnDetail> columns;

    public TableColumns() {}

    public TableColumns(String tableName, List<ColumnDetail> columns) {
        this.tableName = tableName;
        this.columns = columns;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<ColumnDetail> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnDetail> columns) {
        this.columns = columns;
    }
}

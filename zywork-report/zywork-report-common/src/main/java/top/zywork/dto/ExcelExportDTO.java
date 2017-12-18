package top.zywork.dto;

import java.util.List;

/**
 * 用于封装Excel导出的基本信息，此基本信息可由json配置文件配置。
 * rows属性可由外部提供相应的数据源<br />
 * 创建于2017-12-18<br />
 *
 * @author 王振宇
 * @version 1.0
 */
public class ExcelExportDTO {

    private String fileType;
    private String title;
    private String[] columnNames;
    private String[] properties;
    private List<Object> rows;

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
    }

    public String[] getProperties() {
        return properties;
    }

    public void setProperties(String[] properties) {
        this.properties = properties;
    }

    public List<Object> getRows() {
        return rows;
    }

    public void setRows(List<Object> rows) {
        this.rows = rows;
    }
}

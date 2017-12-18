package top.zywork.dto;

import java.util.List;

/**
 * 用于封装Excel导入的基本信息，此基本信息可由json配置文件配置。
 * rows属性为需要导入的数据<br />
 * 创建于2017-12-18<br />
 *
 * @author 王振宇
 * @version 1.0
 */
public class ExcelImportDTO {

    private String fileType;
    private Integer beginRow;
    private String[] properties;
    private String destinationClass;

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Integer getBeginRow() {
        return beginRow;
    }

    public void setBeginRow(Integer beginRow) {
        this.beginRow = beginRow;
    }

    public String[] getProperties() {
        return properties;
    }

    public void setProperties(String[] properties) {
        this.properties = properties;
    }

    public String getDestinationClass() {
        return destinationClass;
    }

    public void setDestinationClass(String destinationClass) {
        this.destinationClass = destinationClass;
    }
}

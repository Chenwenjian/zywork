package top.zywork.service;

import org.apache.poi.ss.usermodel.Workbook;
import top.zywork.dto.ExcelExportDTO;

/**
 * Excel导出服务类。导出的标题，列可由json配置文件指定，json配置文件的内容可被读取到ExcelExportDTO类中<br />
 * 创建于2017-12-18<br />
 *
 * @author 王振宇
 * @version 1.0
 */
public interface ExcelExportService {

    /**
     * 根据JSON配置文件的信息生成excel对应的Workbook对象
     * @param excelExportDTO JSON文件对应的JavaBean对象，rows属性为数据源
     * @return Workbook类的实例
     */
    Workbook export(ExcelExportDTO excelExportDTO);

}

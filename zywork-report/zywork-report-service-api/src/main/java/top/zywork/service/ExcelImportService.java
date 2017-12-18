package top.zywork.service;

import org.apache.poi.ss.usermodel.Workbook;
import top.zywork.dto.ExcelImportDTO;

import java.util.List;

/**
 * Excel导入服务类。导入的基本信息由json文件配置，json配置文件的内容可被读取到ExcelImportDTO类中<br />
 * 创建于2017-12-18<br />
 *
 * @author 王振宇
 * @version 1.0
 */
public interface ExcelImportService {

    /**
     * 根据指定的Workbook把数据导入到指定的List集合
     * @param workbook Workbook工作簿对象
     * @param excelImportDTO ExcelImportDTO封装了导入相关的信息
     * @return 需要导入的对象组成的集合
     */
    List<Object> imports(Workbook workbook, ExcelImportDTO excelImportDTO);

}

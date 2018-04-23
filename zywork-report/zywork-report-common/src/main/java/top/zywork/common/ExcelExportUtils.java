package top.zywork.common;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.zywork.dto.ExcelExportDTO;
import top.zywork.dto.ExcelImportDTO;

import java.io.IOException;
import java.io.InputStream;

/**
 * Excel导出工具类<br />
 * 创建于2017-12-18<br />
 *
 * @author 王振宇
 * @version 1.0
 */
public class ExcelExportUtils {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionUtils.class);

    /**
     * 根据JSON文件的输入流创建ExcelExportDTO对象
     * @param jsonInput json文件输入流
     * @return json文件对应的ExcelExportDTO
     */
    public static ExcelExportDTO buildExportDTO(InputStream jsonInput) {
        try {
            return JSON.parseObject(jsonInput, ExcelExportDTO.class);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 根据JSON文本创建ExcelExportDTO对象
     * @param jsonText json文本
     * @return json文本对应的ExcelExportDTO
     */
    public static ExcelExportDTO buildExportDTO(String jsonText) {
        return JSON.parseObject(jsonText, ExcelExportDTO.class);
    }

    /**
     * 根据JSON文件的输入流创建ExcelImportDTO对象
     * @param jsonInput json文件输入流
     * @return json文件对应的ExcelImportDTO
     */
    public static ExcelImportDTO buildImportDTO(InputStream jsonInput) {
        try {
            return JSON.parseObject(jsonInput, ExcelImportDTO.class);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 根据JSON文本创建ExcelImportDTO对象
     * @param jsonText json文本
     * @return json文本对应的ExcelExportDTO
     */
    public static ExcelImportDTO buildImportDTO(String jsonText) {
        return JSON.parseObject(jsonText, ExcelImportDTO.class);
    }

}

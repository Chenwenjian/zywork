package top.zywork.service.impl;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import top.zywork.common.ExcelUtils;
import top.zywork.common.ReflectUtils;
import top.zywork.dto.ExcelExportDTO;
import top.zywork.enums.MIMETypeEnum;
import top.zywork.service.ExcelExportService;

import java.util.List;

/**
 * ExcelExportService服务实现类<br />
 * 创建于2017-12-18<br />
 *
 * @author 王振宇
 * @version 1.0
 */
@Component
public class ExcelExportServiceImpl implements ExcelExportService {

    private ExcelUtils excelUtils;

    @Override
    public Workbook export(ExcelExportDTO excelExportDTO) {
        excelUtils = new ExcelUtils();
        String fileType = excelExportDTO.getFileType();
        if (fileType == null || "".equals(fileType.trim())) {
            excelExportDTO.setFileType(MIMETypeEnum.XLSX.getExt());
        }
        Workbook workbook = excelUtils.newExcel(excelExportDTO.getFileType());
        Sheet sheet = workbook.createSheet();
        String[] columnNames = excelExportDTO.getColumnNames();
        createTitleRow(sheet, columnNames.length, excelExportDTO.getTitle());
        createHeadRow(sheet, columnNames);
        createContentRows(sheet, excelExportDTO.getProperties(), excelExportDTO.getRows());
        return workbook;
    }

    private void createTitleRow(Sheet sheet, int totalColumns, String title) {
        Cell cell = excelUtils.createCell(sheet, 0, 0);
        excelUtils.mergeCells(sheet, 0, 0, 0, totalColumns - 1);
        excelUtils.boldCenterStyle(cell);
        cell.setCellValue(title);
    }

    private void createHeadRow(Sheet sheet, String[] columnNames) {
        for (int colNo = 0, len = columnNames.length; colNo <  len; colNo++) {
            Cell cell = excelUtils.createCell(sheet, 1, colNo);
            cell.setCellValue(columnNames[colNo]);
            excelUtils.boldStyle(cell);
        }
    }

    private void createContentRows(Sheet sheet, String[] properties, List<Object> rows) {
        for (int rowNo = 0, totalRows = rows.size(); rowNo < totalRows; rowNo++) {
            Object rowObj = rows.get(rowNo);
            for (int colNo = 0, totalCols = properties.length; colNo < totalCols; colNo++) {
                excelUtils.setCellValue(excelUtils.createCell(sheet, rowNo + 2, colNo),
                        ReflectUtils.invokeGetter(rowObj, properties[colNo]));
            }
        }
    }
}

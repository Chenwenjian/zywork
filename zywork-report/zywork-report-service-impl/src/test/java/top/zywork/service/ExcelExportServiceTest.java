package top.zywork.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.zywork.common.ExcelExportUtils;
import top.zywork.common.ExcelUtils;
import top.zywork.dto.ExcelExportDTO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/config/spring-context.xml"})
public class ExcelExportServiceTest {

    @Autowired
    private ExcelExportService excelExportService;

    @Test
    public void testExport() {
        try {
            ExcelExportDTO exportDTO = ExcelExportUtils.buildExportDTO(new FileInputStream(new File("src/test/resources/user-export.json")));
            List<Object> rows = new ArrayList<>();
            rows.add(new UserExportDTO(1, "abc", "18888888888"));
            rows.add(new UserExportDTO(2, "bcd", "18888888888"));
            rows.add(new UserExportDTO(3, "cde", "18888888888"));
            exportDTO.setRows(rows);
            ExcelUtils.writeExcel(excelExportService.export(exportDTO), "src/test/resources/user.xlsx");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}

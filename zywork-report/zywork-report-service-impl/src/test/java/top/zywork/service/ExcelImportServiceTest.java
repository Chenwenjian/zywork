package top.zywork.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.zywork.common.ExcelExportUtils;
import top.zywork.common.ExcelUtils;
import top.zywork.dto.ExcelImportDTO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/config/spring-context.xml"})
public class ExcelImportServiceTest {

    @Autowired
    private ExcelImportService excelImportService;

    @Test
    public void testImport() {
        try {
            ExcelUtils excelUtils = new ExcelUtils();
            ExcelImportDTO importDTO = ExcelExportUtils.buildImportDTO(new FileInputStream(new File("src/test/resources/user-import.json")));
            List<Object> rows = excelImportService.imports(excelUtils.readExcel("src/test/resources/user.xlsx"), importDTO);
            System.out.println("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}

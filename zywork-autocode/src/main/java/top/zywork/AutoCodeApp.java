package top.zywork;

import top.zywork.common.CodeGenerator;
import top.zywork.common.JDBCUtils;

/**
 * 自动代码生成运行类<br/>
 *
 * 创建于2018-03-12<br/>
 *
 * @author 王振宇
 * @version 1.0
 */
public class AutoCodeApp {

    public static void main(String[] args) {
        CodeGenerator.generateCodes(JDBCUtils.MYSQL_DRIVER, "jdbc:mysql://localhost:3306/zywork", "root", "root");
    }
}

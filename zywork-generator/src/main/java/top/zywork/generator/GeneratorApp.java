package top.zywork.generator;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.FileUtils;
import top.zywork.generator.bean.Generator;
import top.zywork.generator.bean.JDBC;
import top.zywork.generator.generator.CodeGenerator;

import java.io.File;
import java.io.IOException;

/**
 * 自动代码生成运行类<br/>
 *
 * 创建于2018-03-12<br/>
 *
 * @author 王振宇
 * @version 1.0
 */
public class GeneratorApp {

    public static void main(String[] args) {
        try {
            JDBC jdbc = JSON.parseObject(
                    FileUtils.readFileToString(
                            new File("zywork-generator/src/main/resources/config/jdbc.json"),
                            "utf-8"),
                    JDBC.class);
            Generator generator = JSON.parseObject(
                    FileUtils.readFileToString(
                            new File("zywork-generator/src/main/resources/config/generator.json"),
                            "utf-8"),
                    Generator.class);
            // CodeGenerator.generateCodes(jdbc, generator);

            CodeGenerator.generateJoinCodes("Test", "test", jdbc, generator, "t_user",
                    new String[]{"t_user-id-Long", "t_user-phone-String", "t_user_role-user_id-Long"}, "t_user.id = t_user_role.user_id");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

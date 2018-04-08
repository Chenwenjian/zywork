package top.zywork.generator;

import com.alibaba.fastjson.JSON;
import top.zywork.common.FileUtils;
import top.zywork.common.StringUtils;
import top.zywork.generator.bean.GeneratorReserved;

import java.io.File;
import java.io.IOException;

/**
 * 用于删除自动生成的代码<br/>
 *
 * 创建于2018-04-08<br/>
 *
 * @author 王振宇
 * @version 1.0
 */
public class GeneratorReservedApp {

    public static void main(String[] args) {
        try {
            GeneratorReserved generatorReserved = JSON.parseObject(org.apache.commons.io.FileUtils.readFileToString(
                    new File(FileUtils.getResourcePath("classpath:/config/generator-reserved.json")), "utf-8"), GeneratorReserved.class);

            deleteFiles(generatorReserved.getBaseDir() + generatorReserved.getBaseJavaDir(), generatorReserved.getReservedJavaDir());
            deleteFiles(generatorReserved.getBaseDir() + generatorReserved.getBaseResDir(), generatorReserved.getReservedResDir());
            deleteFiles(generatorReserved.getBaseDir() + generatorReserved.getBaseCssDir(), generatorReserved.getReservedCssDir());
            deleteFiles(generatorReserved.getBaseDir() + generatorReserved.getBaseJsDir(), generatorReserved.getReservedJsDir());
            deleteFiles(generatorReserved.getBaseDir() + generatorReserved.getBaseViewDir(), generatorReserved.getReservedViewDir());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void deleteFiles(String baseDir, String reservedDir) {
        System.out.println("删除" + baseDir + "中除" + reservedDir + "的目录");
        File file = new File(baseDir);
        File[] files = file.listFiles();
        int count = 0;
        for (File f : files) {
            if (f.isDirectory() && StringUtils.isInArray(reservedDir.split(","), f.getName())) {
                FileUtils.deleteFiles(f.getAbsolutePath());
                count++;
            }
        }
        System.out.println("共删除" + count + "个目录……");
    }

}

package top.zywork.generator;

import com.alibaba.fastjson.JSON;
import top.zywork.common.FileUtils;
import top.zywork.generator.bean.Generator;

import java.io.File;
import java.io.IOException;

/**
 * 用于删除自动生成的代码<br/>
 * <p>
 * 创建于2018-04-08<br/>
 *
 * @author 王振宇
 * @version 1.0
 */
public class GeneratorRemoveApp {

    public static void main(String[] args) {
        try {
            Generator generator = JSON.parseObject(org.apache.commons.io.FileUtils.readFileToString(
                    new File(FileUtils.getResourcePath("classpath:/config/generator.json")), "utf-8"), Generator.class);

            String javaCodeDir = generator.getSaveBaseDir() + generator.getJavaSrcDir() + generator.getBasePackage().replace(".", File.separator);
            String mapperDir = generator.getSaveBaseDir() + generator.getResourceDir() + generator.getMapperDir();
            String viewDir = generator.getSaveBaseDir() + generator.getResourceDir() + generator.getViewFileDir();
            String jsDir = generator.getSaveBaseDir() + generator.getResourceDir() + generator.getJsFileDir();
            String cssDir = generator.getSaveBaseDir() + generator.getResourceDir() + generator.getCssFileDir();
            FileUtils.deleteFiles(javaCodeDir);
            FileUtils.deleteFiles(mapperDir);
            FileUtils.deleteFiles(viewDir);
            FileUtils.deleteFiles(jsDir);
            FileUtils.deleteFiles(cssDir);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

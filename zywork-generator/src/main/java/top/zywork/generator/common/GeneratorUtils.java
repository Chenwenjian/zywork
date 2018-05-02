package top.zywork.generator.common;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.zywork.generator.bean.Generator;

import java.io.*;
import java.util.Random;

/**
 * 代码自动生成工具类<br/>
 *
 * 创建于2018-03-12<br/>
 *
 * @author 王振宇
 * @version 1.0
 */
public class GeneratorUtils {

    private static final Logger logger = LoggerFactory.getLogger(GeneratorUtils.class);

    /**
     * 读取代码模板文件
     * @param templateFile 模板文件路径
     * @return 模板文件的字符串内容
     */
    public static String readTemplate(Generator generator, String templateFile) {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(generator.getSaveBaseDir() + generator.getTemplateDir() + templateFile), generator.getCharset()));
            StringBuilder sb = new StringBuilder("");
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line).append("\r\n");
            }

            return sb.toString();
        } catch (IOException e) {
            logger.error(e.getMessage());
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
        }
        return null;
    }

    /**
     * 在src/main/java目录中创建Java代码的基础package
     */
    public static File createBasePackage(Generator generator) {
        String packagePath = generator.getSaveBaseDir() + generator.getJavaSrcDir() + generator.getBasePackage();
        File file = new File(packagePath.replace(".", File.separator));
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    /**
     * 在基础package中创建指定的包
     * @param packageName 包名称，可以是.分割的包名称
     * @return 创建好的包对应的目录的绝对路径
     */
    public static String createPackage(Generator generator, String packageName) {
        String packagePath = "";
        packagePath = packageName.replace(".", File.separator);
        File file1 = new File(createBasePackage(generator), packagePath);
        if (!file1.exists()) {
            file1.mkdirs();
        }
        return file1.getAbsolutePath();
    }

    /**
     * 在src/main/resources目录中创建资源目录
     * @param resDirName 资源目录名称
     * @return 创建的资源目录的绝对路径
     */
    public static String createResDir(Generator generator, String resDirName) {
        File file = new File(generator.getSaveBaseDir() + generator.getResourceDir() + File.separator + resDirName);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }

    /**
     * 在src/main/webapp/static目录下创建css和js目录
     * @param generator Generator实例
     * @param resDir 资源目录名
     * @return
     */
    public static String createViewResDir(Generator generator, String resDir) {
        File file = new File(generator.getSaveBaseDir() + resDir);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }

    /**
     * 在src/main/webapp/WEB-INF目录中创建views目录
     * @param generator Generator实例
     * @param viewDir 需要创建的视图目录名
     * @return
     */
    public static String createViewDir(Generator generator, String viewDir) {
        File file = new File(generator.getSaveBaseDir() + generator.getViewFileDir() + viewDir);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }

    /**
     * 写出文件
     * @param fileContent 文件内容，字符串
     * @param path 文件路径
     * @param fileName 文件名
     */
    public static void writeFile(String fileContent, String path, String fileName) {
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(path + File.separator + fileName), "UTF-8"));
            bufferedWriter.write(fileContent);

        } catch (IOException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
    }

    /**
     * 通过表名称获取与表对应的DO实体类的类名称
     * @param tableName 表名称
     * @param prefix 表前缀
     * @return 表对应的实体类的类名称
     */
    public static String tableNameToClassName(String tableName, String prefix) {
        StringBuilder className = new StringBuilder("");
        String[] strArray = tableName.split("_");
        int startIndex = prefix == null ? 0 : 1;
        for (int i = startIndex, len = strArray.length; i < len; i++) {
            className.append(StringUtils.capitalize(strArray[i]));
        }
        return className.toString();
    }

    /**
     * 产生serialVersionId
     * @return serialVersionId
     */
    public static Long generateSerialVersionId() {
        return Long.MIN_VALUE + new Random().nextInt(Integer.MAX_VALUE);
    }

    /**
     * 通过表名称获取Controller中的模块名，如user_social_type对应于user-social-type
     * @param tableName 表名称
     * @param prefix 表前缀
     * @return 表名称对应的Controller模块名
     */
    public static String getModuleName(String tableName, String prefix) {
        return tableName.replace(prefix + "_", "").replace("_", "-");
    }

}

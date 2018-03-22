# zywork-generator

*作者：王振宇*

zywork-generator是根据zywork项目规范和架构编写的代码自动生成器。

可自动生成指定数据库下的所有数据表对应的DO实体类，DTO传输对象类，VO值对象类，Query查询对象类，DAO接口及DAO接口对应的MyBatis Mapper映射文件，Service接口及Service实现类和Controller控制器。

所有DAO接口和Service接口严格继承自BaseDAO接口和BaseService接口，MyBatis Mapper映射文件实现了BaseDAO中定义的所有方法的映射，Service实现类中实现了BaseService接口中定义的所有方法。

zywork-generator提供了Java main方法的运行方式，在top.zywork.generator包中找到GeneratorApp类，并执行main方法即可（使用前请修改jdbc.json文件中的数据库连接参数）：

```
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
            CodeGenerator.generateCodes(jdbc, generator);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
```

zywork-generator还提供了可视化的网页自动生成单表及关联表的代码，可在zywork-generator项目的dist目录中下载```zywork-generator.war```包部署运行。详细操作可直接参考网页提示，网页图示如下：

![](https://github.com/GZWgssmart/zywork/blob/master/zywork-generator/image/jdbc.png)

![](https://github.com/GZWgssmart/zywork/blob/master/zywork-generator/image/generator.png)

![](https://github.com/GZWgssmart/zywork/blob/master/zywork-generator/image/single.png)

![](https://github.com/GZWgssmart/zywork/blob/master/zywork-generator/image/join.png)
# zywork-autocode

*作者：王振宇*

zywork-autocode是根据zywork项目规范和架构编写的代码自动生成器。

可自动生成指定数据库下的所有数据表对应的DO实体类，DTO传输对象类，VO值对象类，Query查询对象类，DAO接口及DAO接口对应的MyBatis Mapper映射文件，Service接口及Service实现类和Controller控制器。

所有DAO接口和Service接口严格继承自BaseDAO接口和BaseService接口，MyBatis Mapper映射文件实现了BaseDAO中定义的所有方法的映射，Service实现类中实现了BaseService接口中定义的所有方法。

目前zywork-autocode只提供了Java main方法的运行方式，找到AutoCodeApp类，并执行main方法即可（使用前请修改数据库连接参数）：

```
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
```
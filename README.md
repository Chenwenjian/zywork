# zywork

*作者：王振宇*

zywork项目是基于SSM框架的多个子系统的集合，使用分布式服务架构，为开发者提供高效快捷的开发体验。开发团队不需要再配置SSM框架便可使用众多集成的功能，甚至是可用的系统！zywork项目遵循阿里巴巴的Java开发规范，并补充自己团队内部的一些Java开发规范。

zywork项目包含的功能有：

1. 通用工具类
2. 用户注册、登录，使用QQ，微信，微博等第三方登录的**用户中心**
3. 基于Apache Shiro的**权限管理**
4. 基于Activiti的**业务流程管理**
5. 基于POI和JasperReport的**Excel处理和PDF报表导出**
6. 基于HighCharts的**HTML5 WEB报表**
7. 基于Redis的**数据缓存**
8. 基于Spring Session的**分布式会话管理**
9. 基于Apache ZooKeeper和Dubbo的**分布式服务架构**
10. 基于Logback或log4j的**日志记录**
11. 基于Spring Task或QuartZ的**作业调度**
12. 基于JavaMail和阿里云短信API的**消息发送**
13. 基于微信支付，支付宝支付的**支付中心**
14. 基于Bootstrap的前端及后台**UI视图**

#### 系统基本架构 
```SpringMVC + Spring + MyBatis```，但是同时也提供了Hibernate相关的工具类，尽管此项目并没有使用Hibernate。

此系统为分布式系统，包含有多个独立可运行的子系统，分布式协调服务基于Apache ZooKeeper，分布式服务基于阿里巴巴的Dubbo，使用Nginx提供Tomcat集群的负载均衡。

在zywork项目中，提供了一个```documents```目录，用于存储本项目相关的所有文档，其中```zywork.sql```文件是整个项目的数据库脚本文件，包含建立数据表及初始化数据的所有脚本。

#### 项目子系统划分
<table>
	<tbody>
		<tr>
			<th>名称</th>
			<th>说明</th>
		</tr>
		<tr>
			<td>zywork-common</td>
			<td>通用模块，包含有常用的工具类</td>
		</tr>
		<tr>
			<td>zywork-config</td>
			<td>通用配置文件中心，为子系统提供通用的配置文件</td>
		</tr>
		<tr>
			<td>zywork-ucenter</td>
			<td>用户中心系统，包含有用户注册，登录，第三方登录</td>
		</tr>
		<tr>
			<td>zywork-upms</td>
			<td>权限管理系统，细粒度的权限控制。包含模块，角色，权限等管理</td>
		</tr>
		<tr>
			<td>zywork-cms</td>
			<td>内容管理系统，包含有文章类别，文章管理，系统通知，友情链接等</td>
		</tr>
		<tr>
			<td>zywork-bpms</td>
			<td>
			业务流程系统，包含有业务流程的上传，手动部署业务流程，业务流程的执行等			</td>
		</tr>
		<tr>
			<td>zywork-report</td>
			<td>
			报表系统，包含有Excel的处理，PDF报表的导出，模板的导入与下载
			</td>
		</tr>
		<tr>
			<td>zywork-message</td>
			<td>消息通知系统，包含有邮件，短信。消息模板的添加与修改</td>
		</tr>
		<tr>
			<td>zywork-pay</td>
			<td>支付系统，包含有微信支付，支付宝支付。支付订单的管理</td>
		</tr>
		<tr>
			<td>zywork-log</td>
			<td>日志系统，操作日志的记录，查询等管理</td>
		</tr>
		<tr>
			<td>zywork-schedule</td>
			<td>作业调度系统，作业查询，修改，启动，停止，暂停与重启</td>
		</tr>
	</tbody>
</table>

#### 使用的技术

**后台部分：**

<table>
	<tbody>
		<tr>
			<th>技术</th>
			<th>说明</th>
		</tr>
		<tr>
			<td>Apache Maven</td>
			<td>项目构建管理</td>
		</tr>
		<tr>
			<td>Shell Script</td>
			<td>Bash Shell脚本</td>
		</tr>
		<tr>
			<td>SpringMVC</td>
			<td>WEB控制器</td>
		</tr>
		<tr>
			<td>Spring</td>
			<td>IoC和AOP</td>
		</tr>
		<tr>
			<td>MyBatis</td>
			<td>数据库访问</td>
		</tr>
		<tr>
			<td>MySQL</td>
			<td>数据库</td>
		</tr>
		<tr>
			<td>Druid</td>
			<td>数据源及连接池</td>
		</tr>
		<tr>
			<td>Apache Shiro</td>
			<td>权限认证</td>
		</tr>
		<tr>
			<td>Redis</td>
			<td>分布式缓存数据库</td>
		</tr>
		<tr>
			<td>Spring Session</td>
			<td>分布式Session会话管理</td>
		</tr>
		<tr>
			<td>Activiti</td>
			<td>业务流程引擎</td>
		</tr>
		<tr>
			<td>QuzrtZ</td>
			<td>作业调度</td>
		</tr>
		<tr>
			<td>Apache POI</td>
			<td>Excel处理</td>
		</tr>
		<tr>
			<td>JasperReport</td>
			<td>PDF报表</td>
		</tr>
		<tr>
			<td>JavaMail</td>
			<td>邮件发送</td>
		</tr>
		<tr>
			<td>阿里云短信API</td>
			<td>短信接口</td>
		</tr>
		<tr>
			<td>slf4j & Logback</td>
			<td>日志记录</td>
		</tr>
		<tr>
			<td>Apache ZooKeeper</td>
			<td>分布式协调服务</td>
		</tr>
		<tr>
			<td>Dubbo</td>
			<td>分布式服务框架</td>
		</tr>
		<tr>
			<td>Apache Kafka</td>
			<td>分布式消息队列</td>
		</tr>
		<tr>
			<td>FastDFS</td>
			<td>分布式文件系统</td>
		</tr>
		<tr>
			<td>阿里云OSS</td>
			<td>阿里云对象存储</td>
		</tr>
	</tbody>
</table>

**前台部分：**

<table>
	<tbody>
		<tr>
			<th>技术</th>
			<th>说明</th>
		</tr>
		<tr>
			<td>HTML5</td>
			<td>HTML5</td>
		</tr>
		<tr>
			<td>CSS3</td>
			<td>CSS3</td>
		</tr>
		<tr>
			<td>Vue.js</td>
			<td>用户界面构建</td>
		</tr>
		<tr>
			<td>Element UI</td>
			<td>UI框架</td>
		</tr>
		<tr>
			<td>axios</td>
			<td>Vue AJAX请求</td>
		</tr>
		<tr>
			<td>HighCharts</td>
			<td>HTML5 WEB报表</td>
		</tr>
	</tbody>
</table>

**第三方登录：**

QQ登录，微信登录，微博登录

**在线支付：**

微信支付，支付宝支付

**后台服务：**

Ngnix, Tomcat, ZooKeeper, Redis, MySQL

**开发及测试环境：**

MacOS, IntellijIDEA, Google Chrome, Postman, JDK1.8, JavaEE7.0, Nginx, Tomcat8.5, ZooKeeper, Redis, MySQL5.7

**Spring与Dubbo整合注意事项：**

Dubbo提供了Java Configuration API，Properties，XML和注解的配置形式。此项目中推荐使用XML配置文件的形式，可支持事务管理的服务。如果使用注解的形式，则不支持事务管理的服务，并会出现Dubbo注解```@Reference```引用为```null```的问题。

Dubbo的服务超时时间设置在Provider中，不需要在Consumer中设置超时时间，根据服务性能确定超时时间。

Provider中定义服务实现类时，使用```@Service(value = "userService")```的注解，不需要在```spring-dubbo-provider.xml```文件中定义bean组件。

在Consumer中使用Provider服务时，使用```@Resource```注解或```@Autowired(required = false)```注解引用服务。

**Dubbo服务打包成JAR包**

此项目中，Dubbo服务通过Maven打包成JAR包，使用Dubbo框架提供的```com.alibaba.dubbo.container.Main```类来运行，以支持Dubbo的优雅停机```（kill PID）```。运行JAR包，需要把JAR文件和相关的依赖库的lib目录放在同一个目录，Dubbo默认从```classes```目录下的```META-INF/spring```目录去读取Spring配置文件，而此项目是把配置文件放在```classes```目录下的```config```目录中，所以运行时需要重新指定Spring的配置文件。运行命令如下：

```java -Ddubbo.spring.config=classpath*:/config/*.xml -jar zywork-ucenter-service.jar```


如果不想使用java命令来启动服务，每个子系统都提供了独立的Shell脚本来启动，停止和重启服务。如```zywork-ucenter-service-impl```中提供了```zywork-ucenter-service.sh```脚本，在把Dubbo服务打包成JAR包时，会把Shell脚本自动打包到与JAR文件同级的目录中。在使用脚本前，需要设置```JAVA_HOME```环境变量，并给脚本加上可执行权限。脚本使用方法如下：

```
启动服务：./zywork-ucenter-service.sh start

停止服务：./zywork-ucenter-service.sh stop

重启服务：./zywork-ucenter-service.sh restart

```

#### LICENSE许可协议

**[MIT](https://github.com/GZWgssmart/zywork/blob/master/LICENSE)**

Copyright &copy; 王振宇 [http://zywork.top](http://zywork.top)
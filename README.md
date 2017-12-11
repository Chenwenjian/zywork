# zywork

*作者：王振宇*

zywork项目是基于SSM框架的多个子系统的集合，为开发者提供最便捷快速的开发体验。开发团队不需要再配置SSM框架便可使用众多集成的功能，甚至是可用的系统！zywork项目遵循阿里巴巴的Java开发规范，并补充自己团队内部的一些Java开发规范。

zywork项目包含的功能有：

1. 通用工具类
2. 使用QQ，微信，微博等第三方登录的**用户中心**
3. 基于Apache Shiro的**权限验证**
4. 基于Activiti的**业务流程管理**
5. 基于POI和JasperReport的**Excel处理和PDF报表导出**
6. 基于HighCharts的**WEB报表**
7. 基于Redis的**数据缓存**
8. 基于Logback或log4j的**日志记录**
9. 基于Spring Task或QuartZ的**作业调度**
10. 基于JavaMail和阿里云短信API的**消息发送**
11. 基于微信支付，支付宝支付的**支付中心**
12. 基于Bootstrap的前端及后台**UI视图**

#### 系统基本架构 
SpringMVC + Spring + MyBatis，但是同时也提供了Hibernate相关的工具类，尽管此项目并没有使用Hibernate。

此系统为分布式系统，包含有多个独立可运行的子系统，分布式协调服务基于Apache Zookeeper，分布式服务基于阿里巴巴的Dubbo，使用Nginx提供Tomcat集群的负载均衡。

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
			<td>jQuery</td>
			<td>jQuery</td>
		</tr>
		<tr>
			<td>Bootstrap</td>
			<td>前端UI框架</td>
		</tr>
		<tr>
			<td>Vue.js</td>
			<td>用户界面构建</td>
		</tr>
		<tr>
			<td>Element UI</td>
			<td>前端UI加框</td>
		</tr>
		<tr>
			<td>HighCharts</td>
			<td>HTML5 WEB报表</td>
		</tr>
	</tbody>
</table>

**其他：**

第三方登录：QQ登录，微信登录，微博登录

在线支付：微信支付，支付宝支付

**后台服务：**

Ngnix, Tomcat, MySQL, ZooKeeper, Redis

**开发及测试环境：**

MacOS, JDK1.8, JavaEE7.0, MySQL5.7, Nginx, Tomcat8.5, ZooKeeper, Redis, IntellijIDEA, Google Chrome


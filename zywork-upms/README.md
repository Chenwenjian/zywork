# zywork-upms

*作者：王振宇*

zywork-upms为用户权限管理系统，实现了多个子系统间的用户单点登录，统一认证。并支持对模块，权限，资源，角色的管理。

#### zywork-upms-common

通用模块，如Java Bean等

#### zywork-upms-dao

DAO模块，数据访问层，包括DAO接口和Mapper映射文件

#### zywork-upms-service-api

服务接口模块，定义服务接口

#### zywork-upms-service-impl

服务接口实现模块，服务接口的实现类，Dubbo服务相关的Spring配置

#### zywork-upms-shiro-web

基于Apache Shiro与Spring Session的权限认证与分布式会话模块

#### zywork-upms-web

Web层模块，控制器层代码和视图

#### 关于Spring Session

**1、使用场景**

Spring Session在Tomcat分布式集群中使用。以一个独立的系统来看，此系统可以部署到Tomcat集群中，每一个Tomcat集群节点都是同一个系统，客户端的访问请求可能会被分配到Tomcat集群中的任意一个节点上，每一个节点都需要共享相同的Session数据，此时就可以使用Spring Session。

对于多个子系统的情况，每个子系统都部署在唯一的一台Tomcat服务器中，由多个Tomcat服务器组成一个完整的系统（也即由多个子系统组成一个完整的系统），此时并不是Tomcat集群的使用场景，因此也不需要使用Spring Session。但是单独拿出一个子系统，对此子系统来说也可以配置Tomcat集群，此时可使用Spring Session。

**2、zywork-upms子系统中的Spring Session**

在zywork-upms子系统的WEB层，使用了Spring Session，意味着zywork-upms子系统可以独立地进行Tomcat集群部署。

但是如果开发Native App，Session会话的保持需要换用Token思路。

#### Session还是Token？

整个zywork系统使用```Vue.js```和```Element UI```实现前台端完全分离，后台只返回```JSON```数据，并且为了支持手机App，用户会话的保持弃用Session，而改用Token来实现。用户Token的保存使用Redis数据库，并可设置相应的失效时间。当用户访问各种资源时，需要在请求参数中加上用户Token，只有用户Token匹配并有效时才可正常访问。（模拟```Open API Token```的使用场景）

1. 当用户登录时，登录成功，则生成用户Token，并返回到客户端
2. 客户端保存用户Token
3. 客户端访问系统其他资源时，在请求参数中附带用户Token
4. 后台系统判断用户Token是否有效，如果有效，则请求合法

```
注意：本系统中使用了Apache Shiro，不管在WEB端还是手机APP端，单纯使用Token来进行用户认证完全没有问题，但Shiro的角色与权限相关的逻辑在不使用Cookie的情况下将不起作用，因为Shiro的角色与权限的获取依赖于Session会话和JSESSIONID这个Cookie，所以并不会完全弃用Session，并且开发手机APP时，也需要带上Cookie请求头信息
```

#### 使用Token如何实现单点登录及登录后子系统的动态切换

确切地说，zywork系统使用的是```单一登录```，即只要在指定的登录界面进行登录操作，进入到主系统后，就可以在指定的有效时间内，不需要再次登录的情况下动态切换想要访问的子系统。

**1、登录系统及切换系统的流程如下：**

1. 用户访问登录界面
2. 登录界面显示前，从客户端获取```user_token```值并附带```user_token```参数提交到后台
3. 后台从Redis缓存中判断```user_token```是否有效，并返回结果到客户端
4. 客户端判断返回结果，如果有效，则显示主系统（或目标系统）界面，进入到第 8 步；如果无效，则显示登录界面，进入到第 5 步
5. 用户填写用户名和密码，提交到后台
6. 后台校验用户名和密码，如果成功，则生成```user_token```值并返回到客户端
7. 客户端重定向到主系统界面，并保存由后台返回的```user_token```值
8. 当切换子系统时，从客户端获取```user_token```值并附带```user_token```参数提交到后台。进入到第 3 步

**2、退出登录的流程如下：**

1. 用户在任意一个子系统下点击安全退出
2. 客户端发送删除```user_token```的请求到服务器，附带```user_token```参数
3. 后台从Redis缓存中判断```user_token```是否有效，如果有效则删除，并返回删除结果到客户端
4. 客户端根据删除结果，如果删除成功，则客户端删除保存的```user_token```，重定向到登录界面

#### Token的安全性（请求的安全性问题）
每一个API接口都至少需要以下3个参数（如果是开放的API接口，还需要appId和appKey，可限制API调用次数等，推荐使用OAuth2.0）：

<table>
	<tbody>
		<tr>
			<th>参数名</th>
			<th>是否必须</th>
			<th>参数说明</th>
		</tr>
		<tr>
			<td>appId</td>
			<td>否</td>
			<td>应用的唯一编号</td>
		</tr>
		<tr>
			<td>appKey</td>
			<td>否</td>
			<td>应用的Key</td>
		</tr>
		<tr>
			<td>timestamp</td>
			<td>是</td>
			<td>访问请求时的时间戳</td>
		</tr>
		<tr>
			<td>userToken</td>
			<td>是</td>
			<td>客户端存储的user_token值</td>
		</tr>
		<tr>
			<td>sign</td>
			<td>是</td>
			<td>使用url签名算法得出</td>
		</tr>
	</tbody>
</table>

请求处理流程如下：

1. 客户端对url进行签名，得出```sign```签名值
2. 客户端把包括```sign```参数在内的所有请求参数发送到后台
3. 后台从Redis中判断```user_token```是否有效，如果有效，则进入第 4 步，如果无效，则返回结果到客户端，客户端重定向到登录界面
4. 后台对```timestamp```参数加以判断，如果后台时间与```timestamp```参数值的差值大于指定的数值，则认为请求过时，为非法请求，后台直接返回非法请求消息，否则为合法请求，进入到第 5 步
5. 后台接收到请求参数后，同样进行url签名算法得出```sign```值
6. 后台得出的```sign```值与客户端传递过来的```sign```值进行比较，如果一致，则为合法请求，否则为非法请求
7. 如果为合法请求，则后台执行相应的操作并返回数据，如果为非法请求，则后台直接返回非法请求消息

url签名算法如下：

1. 除```sign```之外的所有请求参数全部按照字母顺序排列
2. 除```sign```之外的所有请求参数的参数名和参数值拼接成一个字符串
3. 对拼接成的字符串进行```MD5```加密后转成全大写字符串，生成```sign```签名值
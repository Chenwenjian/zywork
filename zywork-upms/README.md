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
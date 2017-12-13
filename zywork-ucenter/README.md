# zywork-ucenter

*作者：王振宇*

zywork-ucenter为用户中心系统，实现了用户的注册，登录，基于QQ、微信、微博的第三方登录和用户信息的管理。

#### zywork-ucenter-common

通用模块，如Java Bean等

#### zywork-ucenter-dao

DAO模块，数据访问层代码

#### zywork-ucenter-service-api

服务接口模块，定义服务接口

#### zywork-ucenter-service-impl

服务接口实现模块，服务接口的实现类，Dubbo服务相关的Spring配置

#### zywork-ucenter-web

Web层模块，控制器层代码和视图

#### 关联的数据表

1. ```t_user```
2. ```t_user_detail```
3. ```t_user_social```
4. ```t_social_type```
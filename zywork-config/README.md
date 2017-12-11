# zywork-config

*作者：王振宇*

zywork-config为配置文件中心，为各个子系统提供统一通用的配置文件，包含四个模块，第一个模块为zywork-config-common，包含通用的配置文件；第二个模块为zywork-config-dao，包含DAO相关的配置文件；第三个模块为zywork-config-service，包含Service相关的配置文件；第四个模块为zywork-config-web，包含Web层相关的模块。

#### zywork-config-common
1. ```logback.xml```
2. ```spring-dozer.xml```

#### zywork-config-dao
1. ```jdbc.properties```
2. ```mybatis.xml```
3. ```spring-datasource.xml```
4. ```spring-mybatis.xml```

#### zywork-config-service
1. ```spring-context.xml```
2. ```redis.properties```
3. ```spring-redis-single.xml```
4. ```spring-redis-cluster.xml```

#### zywork-config-web
1. ```springmvc.xml```

对于一些特殊的子系统，如zywork-bpms，需要用到Activiti，则额外提供```spring-activiti.xml```配置文件；如zywork-upms，需要用到Apache Shiro，则额外提供```ehcache-shiro.xml```和```spring-shiro.xml```配置文件；如需要使用QuartZ，则可提供```spring-quartz.xml```配置文件。

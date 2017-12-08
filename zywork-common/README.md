# zywork-common

*作者：王振宇*

zywork-common为通用工具模块，集合了常用的工具类和BaseDAO、BaseService接口，AbstractBaseDAO、AbstractBaseService抽象实现类。

zywork-common模块提供了基础的DAO接口和Service接口，名称分别为```BaseDAO```和```BaseService```，包含有一个基础的抽象实现。详细方法请参考如下类：

```top.zywork.dao.BaseDAO```，
```top.zywork.dao.AbstractBaseDAO```

和

```top.zywork.service.BaseService```， ```top.zywork.service.AbstractBaseService```

```top.zywork.dao.AbstractBaseDAO```抽象实现类可适用于Hibernate，MyBatis不需要提供实现类。


# zywork-log

*作者：王振宇*

zywork-log为日志记录子系统，提供了自定义的日志记录注解，支持方法级别的日志记录。

#### @SysLog注解

此系统自定义了```@SysLog```注解，在```Service```或```Controller```方法前加上此注解后，系统便可记录日志到数据库中。此注解提供了一个```description```属性，用于日志简要描述，使用方法如下：

```
@PostMapping("user/login")
@ResponseBody
@SysLog(description = "用户登录")
public ControllerStatusVO login(UserLoginVO userLoginVO) {
	ControllerStatusVO statusVO = null;
	try {
		userService.getByPhonePwd(userLoginVO);
		statusVO = ControllerStatusVO.status(ControllerStatusEnum.USER_LOGIN_SUCCESS);
	} catch (Exception e) {
		statusVO = ControllerStatusVO.status(ControllerStatusEnum.USER_LOGIN_FAIL);
	}
	return statusVO;
}
```

#### 其他子系统如何使用@SysLog注解？
其他模块想要使用日志服务，并不是使用```Dubbo```的分布式服务，而是直接依赖于```zywork-log-service-impl```模块便可使用```@SysLog```注解完成系统日志记录。


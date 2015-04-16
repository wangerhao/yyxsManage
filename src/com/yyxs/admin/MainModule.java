package com.yyxs.admin;


import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.ioc.provider.AnnotationIocProvider;


/**
 * 主模块
 * @author power
 *
 */
@Modules(scanPackage=true)
@IocBy(type=AnnotationIocProvider.class, args={"com.yyxs.admin"})
public class MainModule {}

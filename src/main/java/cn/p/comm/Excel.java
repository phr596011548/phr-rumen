package cn.p.comm;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)//在属性上 包含枚举的常量
@Retention(RetentionPolicy.RUNTIME)//jvm加载 在类上面的文件 在运行时获取
public @interface Excel {
    //default 默认值 列表头
    String name()default "";

}

package cn.p.comm;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * 定义excel描述注解
 * @author YZQ
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {

    /**
     * 列索引
     * @return
     */
    public int columnIndex() default 0;

    /**
     * 列名
     * @return
     */
    public String columnName() default "";

}

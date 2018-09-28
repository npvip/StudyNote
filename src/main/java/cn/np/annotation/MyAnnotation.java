package cn.np.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
        import java.lang.annotation.Target;

/**
 * @author np
 * @date 2018/9/27
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {

    int id() default 0;

    String msg();
}

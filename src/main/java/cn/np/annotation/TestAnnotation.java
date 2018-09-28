package cn.np.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author np
 * @date 2018/9/28
 */
@MyAnnotation(msg = "hello annotation", id = 12)
public class TestAnnotation {

    @Check(value = "jack")
    private String name;

    @Perform(value = "AAA")
    private void testMethod(){

    }

    public static void main(String[] args) {
        // 通过class对象的isAnnotationPresent()方法判断它是否应用了某个注解
       boolean hasAnnotation = TestAnnotation.class.isAnnotationPresent(MyAnnotation.class);

       if (hasAnnotation) {
           MyAnnotation myAnnotation = TestAnnotation.class.getAnnotation(MyAnnotation.class);

           System.out.println("myAnnotation.id: " + myAnnotation.id());
           System.out.println("myAnnotation.msg: " + myAnnotation.msg());
       }

       // 成员变量上的注解取值
        try {
            Field field = TestAnnotation.class.getDeclaredField("name");
            field.setAccessible(true);
            Check check = field.getAnnotation(Check.class);
            if (check != null) {
                System.out.println("check.value: " + check.value());
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        // 成员方法上的注解取值
        try {
            Method method = TestAnnotation.class.getDeclaredMethod("testMethod");

            if (method != null) {
                Annotation[] ans = method.getAnnotations();
                if (ans != null) {
                    for (Annotation an: ans) {
                        System.out.println("method annotation : " + an.annotationType().getSimpleName());
                        if (an instanceof Perform) {
                            System.out.println("perform value: " + ((Perform) an).value());
                        }
                    }
                }
            }

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }


}

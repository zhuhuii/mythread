package org.jdk5.annotation.use;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 注解的使用
 *  1、@注解名（属性="值" , 属性=值 , ...）
 *  2、如果一个注解内仅仅只有一个名字为 value 的属性时，应用这个注解时可以直接接属性值填写到括号内。(springmvc中的 @requsetMapper)
 *  3、注解没有任何属性，那么在应用这个注解的时候，括号都可以省略。（junit中的 @Test）
 */
class AuthorTest {
    public static void main(String[] args) throws NoSuchMethodException {
        // 1、Class 对象的 isAnnotationPresent() 方法判断它是否应用了某个注解
        // 2、getAnnotation() 方法来获取 Annotation 对象。
        // 3、getAnnotations() 方法。获取元素上的所有注解。
        run();
    }

    @Deprecated
    @Author(username = "zhuhui")
    public void getUser() {
    }

    public static void run() throws NoSuchMethodException {
        // 1、通过反射获取info方法类
        Method method = AuthorTest.class.getMethod("getUser");
        // 2、判断该方法上是否存在@Login注释
        boolean flag = method.isAnnotationPresent(Author.class);
        if (flag) {
            System.out.println(method.getName() + "方法上存在@Auther注解");
        } else {
            System.out.println(method.getName() + "方法上 -> 不存在@Auther注解");
        }
        // 3、获取方法上的所有注释
        Annotation[] annotations = method.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation != null && annotation instanceof Author) {
                //如果是@Login注释，则强制转化，并调用username方法，和password方法。
                String username = ((Author) annotation).username();
                String password = ((Author) annotation).password();
                String description = ((Author) annotation).description();
                System.out.println(username);
                System.out.println(password);
                System.out.println(description);

                String simpleName = annotation.annotationType().getSimpleName();
                System.out.println(simpleName);
                System.out.println(annotation.toString());
            }
        }
    }
}

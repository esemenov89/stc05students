package main.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by STC-05 Team [Aleksei Lysov] on 26.04.2017.
 */
public class BenchmarkStudentServiceBeanPostProcessor implements BeanPostProcessor {

    @Autowired
    private ApplicationContext appContext;

    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        Class classType = o.getClass();
        if (classType.isAnnotationPresent(BenchmarkStudentService.class)){
            Object proxy = Proxy.newProxyInstance(classType.getClassLoader(), classType.getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    long start = System.nanoTime();
                    Object result = method.invoke(o, args);
                    String msg = method.getName() + ": " + (System.nanoTime() - start);
                    System.out.println(msg);

                    StudentServiceUtil studentServiceUtil = (StudentServiceUtil) appContext.getBean("studentServiceUtil");
                    studentServiceUtil.add(msg);

                    return result;
                }
            });
            return proxy;
        }

        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        return o;
    }
}

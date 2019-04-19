package com.yinhai.intercept;

import net.bytebuddy.implementation.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public class SimPackageMethodIntercetInstance {

    @RuntimeType
    public static Object intercept(@This Object obj,
                            @AllArguments Object[] allArguments,
                            @SuperCall Callable<?> zuper,
                            @Origin Method method) throws Throwable {
        System.out.println("---[BEGIN] SimplePackageInstanceMethodInterceptor");
        Object ret = null;
        try {
            System.out.println("    class name = " + obj.getClass().getName());
            System.out.println("    method name = " + method.getName());
            System.out.println(allArguments.toString());
            ret = zuper.call();
        } catch (Throwable t) {
            throw t;
        } finally {
            System.out.println("---[END] SimplePackageInstanceMethodInterceptor");
        }
        return ret;
    }
}

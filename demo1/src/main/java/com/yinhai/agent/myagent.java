package com.yinhai.agent;

import com.yinhai.intercept.SimPackageMethodIntercetInstance;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

import java.lang.instrument.Instrumentation;

public class myagent {
    public static void premain(String args, Instrumentation instrumentation){
        System.out.println("this is byte buddy sample");

        AgentBuilder.Transformer transformer = new AgentBuilder.Transformer() {
            @Override
            public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule) {
                String className = typeDescription.getCanonicalName();
                System.out.println(className);
                System.out.println("ComponentType():: "+typeDescription.getComponentType());

                return builder.method(ElementMatchers.any())  //匹配任意的方法
                        .intercept(MethodDelegation
                                .to(SimPackageMethodIntercetInstance.class));

            }
        };

        AgentBuilder.Listener listener = new AgentBuilder.Listener() {
            @Override
            public void onDiscovery(String s, ClassLoader classLoader, JavaModule javaModule, boolean b) {

            }

            @Override
            public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean b, DynamicType dynamicType) {

            }

            @Override
            public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean b) {

            }

            @Override
            public void onError(String s, ClassLoader classLoader, JavaModule javaModule, boolean b, Throwable throwable) {

            }

            @Override
            public void onComplete(String s, ClassLoader classLoader, JavaModule javaModule, boolean b) {

            }
        };
        new AgentBuilder.Default()
                .type(ElementMatchers.nameStartsWith("com.yinhai"))
                .transform(transformer)
                .with(listener)
                .installOn(instrumentation);

    }
}

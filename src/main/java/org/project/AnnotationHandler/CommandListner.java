package org.project.AnnotationHandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.project.Annotations.*;
import org.project.Util.Prop;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CommandListner implements ICommandListner {

    private String packagetoScan;
    private final Logger log;

    public CommandListner() {
        log = LogManager.getLogger(CommandListner.class);
    }

    @Override
    public void setPackagetoScan(String packagetoScan) {
        this.packagetoScan = packagetoScan;
    }

    @Override
    public void inject(Object a) {
        try {
            Method[] methods = a.getClass().getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(InjectProperty.class)) {
                    method.invoke(a);
                    log.info("Проперти закунфигурировано");
                }
            }
        } catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initializationValues(Object a) {
        try {
            for (Field field : a.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(AddArrayList.class)) {
                    field.setAccessible(true);
                    field.set(a, new ArrayList<>());
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void configure(Object a, ApplicationContext context) {
        for (Field field : a.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(InjectByType.class)) {
                field.setAccessible(true);
                Class aClas = field.getType();
                Object object = context.getObject(getImplClass(aClas));
                try {
                    field.set(a, object);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }

    }


    @Override
    public List<Class<?>> getAllClassesFrom(String packageName) {
        return new Reflections(packageName, new SubTypesScanner(false))
                .getAllTypes()
                .stream()
                .map(name -> {
                    try {
                        return Class.forName(name);
                    } catch (ClassNotFoundException e) {
                        return null;
                    }
                })
                .filter(aClass -> aClass.isAnnotationPresent(Singleton.class))
                .collect(Collectors.toList());

    }

    @Override
    public void initDependency(String packageName, ApplicationContext context) {
        List<Class<?>> classes = getAllClassesFrom(packageName);
        for (Class cls : classes) {
            configure(context.getObject(cls), context);
        }
    }

    @Override
    public void createDao(String packageName, ApplicationContext context) {
        List<Class<?>> classes = getAllClassesFrom(packageName);
        for (Class cls : classes) {
            initializationValues(context.getObject(cls));
        }
    }

    @Override
    public <T> Class<? extends T> getImplClass(Class<T> ifc) {
        Set<Class<? extends T>> classes = new Reflections(packagetoScan).getSubTypesOf(ifc);
        if (classes.size() != 1) {
            log.error(ifc + " has 0 or more than one impl CLass");
        }
        Class aclass = classes.iterator().next();
        return aclass;
    }

    @Override
    public void setValue(Object a) {
        for (Field field : a.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(ConfigProperty.class)) {
                field.setAccessible(true);
                ConfigProperty configProperty = field.getAnnotation(ConfigProperty.class);
                int value = Prop.getProperties().getInteger(configProperty.propertyName(),1);
                try {
                    field.set(a,value);
                    log.info("Значение установлено");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}





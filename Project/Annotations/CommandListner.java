package Annotations;

import Util.Prop;
import org.apache.log4j.BasicConfigurator;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CommandListner {


    public void inject(Object a) {
        try {
            Method[] methods = a.getClass().getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(ConfigProperty.class)) {
                    method.invoke(a);
                    BasicConfigurator.configure();
//                    System.out.println("Удачная инекция");
                }
            }
        } catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void injectPropertyAnnotation(Object a) {
        try {
            for (Field field : a.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(InjectProperty.class)) {
                    field.setAccessible(true);
                    field.set(a, Prop.getProperties().get("Path"));
//                    System.out.println("Удачно");
                }
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

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


    public void configure(Object a, ApplicationContext context) {
        for (Field field : a.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(InjectByType.class)) {
                field.setAccessible(true);
                Object object = context.getObject(field.getType());
                try {
                    field.set(a, object);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }

    }


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

    public void initDependency(String packageName, ApplicationContext context) {
        List<Class<?>> classes = getAllClassesFrom(packageName);
        for (Class cls : classes) {
            configure(context.getObject(cls), context);
        }


    }

    public void initDao(String packageName, ApplicationContext context) {
        List<Class<?>> classes = getAllClassesFrom(packageName);
        for (Class cls : classes) {
            initializationValues(context.getObject(cls));
        }
    }

}




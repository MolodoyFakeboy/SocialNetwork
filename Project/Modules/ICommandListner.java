package Modules;

import java.util.List;

public interface ICommandListner {

    void inject(Object a);
    void injectPropertyAnnotation(Object a);
    void initializationValues(Object a);
    void configure(Object a, ApplicationContext context);
    List<Class<?>> getAllClassesFrom(String packageName);
    void initDependency(String packageName, ApplicationContext context);
    void initDao(String packageName, ApplicationContext context);

}

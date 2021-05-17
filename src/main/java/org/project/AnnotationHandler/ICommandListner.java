package org.project.AnnotationHandler;

import java.util.List;

public interface ICommandListner {
    void setPackagetoScan(String packagetoScan);
    void inject(Object a);
    void initializationValues(Object a);
    void configure(Object a, ApplicationContext context);
    List<Class<?>> getAllClassesFrom(String packageName);
    void initDependency(String packageName, ApplicationContext context);
    void createDao(String packageName, ApplicationContext context);
    <T> Class<? extends T> getImplClass(Class<T> ifc);
    void setValue(Object a);
}

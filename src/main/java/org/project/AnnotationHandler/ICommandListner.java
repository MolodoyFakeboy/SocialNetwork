package org.project.AnnotationHandler;

import java.util.List;

public interface ICommandListner {
    void setPackagetoScan(String packagetoScan);

    void inject(Object a);

    void configure(Object a, ApplicationContext context);

    void injectDaoToService(Object a, Object b);

    List<Class<?>> getAllClassesFrom(String packageName);

    void initDependencyContoller(String packageName, ApplicationContext context);

    void createSingeltonClasses(String packageName, ApplicationContext context);

    <T> Class<? extends T> getImplClass(Class<T> ifc);

    void setValue(Object a);
}

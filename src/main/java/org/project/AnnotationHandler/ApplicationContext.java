package AnnotationHandler;



import Annotations.Singleton;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {

    private ObjectFactory factory;
    ICommandListner commandListner;
    private Map<Class, Object> cache = new ConcurrentHashMap<>();


    public <T> T getObject(Class<T> type) {
        if (cache.containsKey(type)) {
            return (T) cache.get(type);
        }

        T t = factory.createObject(type);

        if (type.isAnnotationPresent(Singleton.class)) {
            cache.put(type, t);
        }

        return t;
    }
    public Object update (Object a) {
        if (cache.containsKey(a.getClass())) {
            cache.replace(a.getClass(),a);
        }
        return a;
    }


    public void setFactory(ObjectFactory factory) {
        this.factory = factory;
    }

    public void setCommandListner(ICommandListner commandListner) {
        this.commandListner = commandListner;
    }
}

package Annotations;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME)
public @interface ConfigProperty {
  String configName() default "config.properties";
  String propertyName();
  String type();
}

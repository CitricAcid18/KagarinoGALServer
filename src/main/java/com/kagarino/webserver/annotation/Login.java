package com.kagarino.webserver.annotation;


import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Login {
    boolean required() default true;
}

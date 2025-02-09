package com.testek.annotations;

import io.qameta.allure.LinkAnnotation;

import java.lang.annotation.*;

/**
 * Class for linking the bug management system
 * @author vincent
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@LinkAnnotation(type = "tms")
public @interface TFSLink {
    String value();
}
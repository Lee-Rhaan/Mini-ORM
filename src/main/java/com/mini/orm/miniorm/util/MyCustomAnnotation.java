package com.mini.orm.miniorm.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom Marker Annotation
 *
 * A Class Annotated with this Custom Marker Annotation agrees to being mapped to a database table.
 * Simpler Words: Turns this class into a database table.
 *
 * Use of below class level annotations:
 * ElementType.Type Target = Indicates this is a Class or Interface level annotation.
 * Runtime Retention Policy = Means "Do not discard". The annotation should be available for reflection at runtime.
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyCustomAnnotation {
}

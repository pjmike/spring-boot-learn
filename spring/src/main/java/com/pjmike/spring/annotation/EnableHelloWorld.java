package com.pjmike.spring.annotation;

import com.pjmike.spring.selector.HelloWorldSelector;
import org.springframework.boot.autoconfigure.AutoConfigurationImportSelector;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@AutoConfigurationPackage
@Import(HelloWorldSelector.class)
public @interface EnableHelloWorld {

}

package com.pjmike.autowire;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @description:
 * @author: 13572
 * @create: 2019/04/27 11:16
 */
@Configuration
@Import(WebConfiguration.class)
public class WebAutoConfiguration {
}

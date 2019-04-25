package com.pjmike.spring;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/02/17 09:09
 */
public class TestClassLoaderGetRerouse {
    public static void main(String[] args) throws IOException {
        String name = "java/sql/Array.class";

        Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(name);
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            System.out.println(url.toString());
        }
    }
}

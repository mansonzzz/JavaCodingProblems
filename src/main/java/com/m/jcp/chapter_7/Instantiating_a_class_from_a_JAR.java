package com.m.jcp.chapter_7;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.Objects;

/**
 * @author zhangtian1
 * idea 相对路径从 src 开始
 */
public class Instantiating_a_class_from_a_JAR {
    public static void main(String[] args) throws Exception {
        URL[] classLoaderUrls = {new URL("file://src/main/java/com/m/jcp/chapter_7/jar/guava-16.0.1.jar")};
        URLClassLoader urlClassLoader = new URLClassLoader(classLoaderUrls);

        Class<?> cisClazz = urlClassLoader.loadClass("com.google.common.io.CountingInputStream");
        Constructor<?> constructor = cisClazz.getConstructor(InputStream.class);
        Object instance = constructor.newInstance(new FileInputStream(Path.of("src/main/java/com/m/jcp/chapter_7/test.txt").toFile()));

        Method readMethod = cisClazz.getMethod("read");
        Method countMethod = cisClazz.getMethod("getCount");
        Object readResult = readMethod.invoke(instance);
        Object countResult = countMethod.invoke(instance);

        assert Objects.equals(countResult, 1);
    }
}
